package CompilationEngine;

import SymbolTable.SymbolTable;
import VMWriter.VMWriter;

import java.io.*;

import static CompilationEngine.Constants.*;
import static CompilationEngine.XMLHandler.*;
import static SymbolTable.Constants.*;

public class CompilationEngine
{
    BufferedReader bufferedReader;
    VMWriter vmWriter;
    StringBuilder outputStringBuilder;
    String inputFileName;
    String outputFileName;
    int ifLabelCount = 0;
    int whileLabelCount = 0;

    StringBuilder serializedTokens;
    SymbolTable symbolTable;
    String variableName;
    String variableCategory;
    String variableState;
    String variableType;
    String className;
    String functionReturnType;
    String subroutineName;


    public CompilationEngine(String inputFileName, String outputFileName, StringBuilder serializedTokens)
    {
        try
        {
            this.inputFileName = inputFileName;
            this.bufferedReader = new BufferedReader(new FileReader(this.inputFileName));
            this.outputFileName = outputFileName;
            this.outputStringBuilder = new StringBuilder();
            this.serializedTokens = serializedTokens;
            this.symbolTable = new SymbolTable();
            this.vmWriter = new VMWriter(outputFileName);
            this.ifLabelCount = 0;
            this.whileLabelCount = 0;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void compileClass()
    { // class className { classVarDec* subroutineDec* }
        getNextToken(); //consume <token> tag
        getNextToken(); //consume <class> tag

        this.variableCategory = CLASSNAME_VAR;
        this.variableState = VAR_DECLARATION;

        compileClassName();
        className = this.variableName;

        getNextToken(); //consume { tag
        compileClassVarDecs();
        compileSubroutineDecs();
        getNextToken(); //consume } tag
    }

    private void compileClassVarDecs()
    {
        while (isThereClassVarDecs())
        {
            compileClassVarDec();
        }
    }

    private boolean isThereClassVarDecs()
    {
        String nextTokenValue = lookAheadTokenValue(LOOK_AHEAD_ONCE);
        return (nextTokenValue.equals(STATIC_FIELD) || nextTokenValue.equals(PLAIN_FIELD));
    }

    public void compileClassVarDec()
    {// (static|field)type varName (',' varName)* ;
        this.variableCategory = lookAheadTokenValue(LOOK_AHEAD_ONCE);
        this.variableState = VAR_DECLARATION;

        getNextToken(); // consume static/field tag

        compileType();
        compileClassVarName();
        addNewClassVariable();
        compileCommaSeparatedClassVarNames();
        getNextToken(); // consume ; tag
    }

    private void compileSubroutineDecs()
    {
        while (isThereSubroutineDecs())
        {
            compileSubroutineDec();
        }
    }

    private boolean isThereSubroutineDecs()
    {
        String nextTokenValue = lookAheadTokenValue(LOOK_AHEAD_ONCE);
        return (nextTokenValue.equals(CONSTRUCTOR_PROCEDURE) ||
                nextTokenValue.equals(FUNCTION_PROCEDURE) ||
                nextTokenValue.equals(METHOD_PROCEDURE));
    }

    public void compileSubroutineDec()
    {// (constructor | function | method) ('void|type) subroutineName (parameterList) subroutineBody
        String procedureType;

        symbolTable.startSubroutine();
        procedureType = lookAheadTokenValue(LOOK_AHEAD_ONCE);
        getNextToken(); //consume (constructor | function | method) tag
        this.functionReturnType = lookAheadTokenValue(LOOK_AHEAD_ONCE);
        getNextToken(); // consume (void/type) tag

        this.variableCategory = SUBROUTINENAME_VAR;
        this.variableState = VAR_DECLARATION;

        compileSubroutineName();
        String subRoutineName = this.variableName;
        getNextToken(); //consume { tag
        addCurrentObjForMethods(procedureType);
        compileParameterList();
        getNextToken(); //consume } tag
        compileSubroutineBody(procedureType, subRoutineName);
    }

    private void handleProcedureCode(String procedureType)
    {
        switch (procedureType)
        {
            case CONSTRUCTOR_PROCEDURE:
                compileConstructor();
                break;
            case METHOD_PROCEDURE:
                compileMethod();
                break;
            case FUNCTION_PROCEDURE:
                break;
        }
    }

    private void addCurrentObjForMethods(String procedureType)
    {
        if (procedureType.equals(METHOD_PROCEDURE))
            symbolTable.define(KEYWORD_CONS_THIS, className, ARGUMENT_VAR);
    }

    private void compileConstructor()
    {
        int sizeOfClassObj = symbolTable.getNumberOfFieldVariables();
        vmWriter.writePush(CONSTANT_SEGMENT, sizeOfClassObj);
        vmWriter.writeCall(MEMORY_ALLOC, 1);
        vmWriter.writePop(POINTER_SEGMENT, 0);
    }

    private void compileMethod()
    {
        vmWriter.writePush(ARGUMENT_SEGMENT, 0);
        vmWriter.writePop(POINTER_SEGMENT, 0);
    }

    public void compileParameterList()
    {// ((type varName) (, type varName)*)?
        if (isThereParameter())
        {
            this.variableCategory = ARGUMENT_VAR;
            compileType();
            compileVarName();
            addNewLocalVariable();
            while (isThereCommaSeparated())
            {
                compileComma();
                compileType();
                compileVarName();
                addNewLocalVariable();
            }
        }
    }

    private boolean isThereParameter()
    {
        String nextTokenValue = lookAheadTokenValue(LOOK_AHEAD_ONCE);
        String nextTokenType = lookAheadTokenType(LOOK_AHEAD_ONCE);
        return (isType(nextTokenValue, nextTokenType));
    }

    private boolean isType(String nextTokenValue, String nextTokenType)
    {
        return (nextTokenValue.equals(INT_TYPE)
                || nextTokenValue.equals(CHAR_TYPE)
                || nextTokenValue.equals(BOOLEAN_TYPE)
                || nextTokenType.equals(IDENTIFIER));
    }

    public void compileSubroutineBody(String procedureType, String subroutineName)
    { // { varDec* statements}

        getNextToken(); // consume { tag
        compileVarDecs();
        int nLocals = symbolTable.varCount(LOCAL_VAR);
        vmWriter.writeFunction(className + DOT_SYMBOL + subroutineName, nLocals);
        handleProcedureCode(procedureType);
        compileStatements();
        getNextToken(); // consume } tag
    }

    public void compileVarDecs()
    {
        while (isThereVarDecs())
        {
            this.variableCategory = LOCAL_VAR;
            compileVarDec();
        }
    }

    private boolean isThereVarDecs()
    {
        String nextTokenValue = lookAheadTokenValue(LOOK_AHEAD_ONCE);
        return (nextTokenValue.equals(VARIABLE));
    }

    public void compileVarDec()
    { // var type varName (, varName)* ;

        getNextToken(); // consume var tag
        compileType();
        compileVarName();
        addNewLocalVariable();
        compileCommaSeparatedVarNames();
        getNextToken(); //consume ; tag
    }

    public void compileStatements()
    { // statement*
        while (isThereStatements())
        {
            compileStatement();
        }
    }

    private boolean isThereStatements()
    {
        String nextTokenValue = lookAheadTokenValue(LOOK_AHEAD_ONCE);
        return (nextTokenValue.equals(LET_STATEMENT)
                || nextTokenValue.equals(IF_STATEMENT)
                || nextTokenValue.equals(WHILE_STATEMENT)
                || nextTokenValue.equals(RETURN_STATEMENT)
                || nextTokenValue.equals(DO_STATEMENT));
    }

    private void compileStatement()
    {
        String nextTokenValue = lookAheadTokenValue(LOOK_AHEAD_ONCE);

        switch (nextTokenValue)
        {
            case LET_STATEMENT:
                compileLet();
                break;
            case IF_STATEMENT:
                compileIf();
                break;
            case WHILE_STATEMENT:
                compileWhile();
                break;
            case RETURN_STATEMENT:
                returnForVoidFunctions();
                compileReturn();
                break;
            case DO_STATEMENT:
                compileDo();
                break;
        }
    }

    public void compileLet()
    { // let varName ([expression])? = expression;
        String varName;
        getNextToken(); //consume let tag
        this.variableState = VAR_USAGE;

        compileVarName();
        addExistingLocalVariable();
        varName = this.variableName;
        int indexOfVar = symbolTable.indexOf(varName);
        String segmentOfVar = symbolTable.kindOf(varName);

        if (isArrayBeingAccessed())
        {
            handleArraysInVm(indexOfVar, segmentOfVar);
        } else
        {
            getNextToken(); // consume = tag
            clearOutputStringBuilder();
            compileExpression();
            codeWriteExp(outputStringBuilder.toString());
            vmWriter.writePop(segmentOfVar, indexOfVar);
            getNextToken(); //consume ; tag
        }
    }

    private void handleArraysInVm(int indexOfVar, String segmentOfVar)
    {
        vmWriter.writePush(segmentOfVar, indexOfVar);
        clearOutputStringBuilder();
        compileSquareExpression();
        removeSquareBrackets();
        codeWriteExp(outputStringBuilder.toString());
        vmWriter.writeArithmetics(PLUS_SYMBOL);
        getNextToken(); // consume = tag
        clearOutputStringBuilder();
        compileExpression();
        codeWriteExp(outputStringBuilder.toString());
        vmWriter.writePop(TEMP_SEGMENT, 0);     //pop temp 0
        vmWriter.writePop(POINTER_SEGMENT, 1);  //pop pointer 1
        vmWriter.writePush(TEMP_SEGMENT, 0);    //push temp 0
        vmWriter.writePop(THAT_SEGMENT, 0); //pop that 0
        getNextToken(); //consume ; tag
    }


    private boolean isArrayBeingAccessed()
    {
        String firstTokenValue = lookAheadTokenValue(LOOK_AHEAD_ONCE);
        return firstTokenValue.equals(OPEN_SQUARE_BRACE_SYMBOL);
    }

    private void compileSquareExpression()
    {
        String openSquareBraceToken;
        String closeSquareBraceToken;
        if (isArrayBeingAccessed())
        {
            openSquareBraceToken = getNextToken();
            outputStringBuilder.append(SPACE).append(openSquareBraceToken).append(NEWLINE);
            compileExpression();
            closeSquareBraceToken = getNextToken();
            outputStringBuilder.append(SPACE).append(closeSquareBraceToken).append(NEWLINE);
        }

    }

    public void compileIf()
    {// if (expression) {statements} (else {statements})?
        int ifLabelCount = this.ifLabelCount;
        ++this.ifLabelCount;

        getNextToken(); //consume if tag
        getNextToken(); //consume ( tag

        clearOutputStringBuilder();
        compileExpression();
        codeWriteExp(outputStringBuilder.toString());

        vmWriter.writeArithmetics(NOT_SYMBOL);
        vmWriter.writeIf(IF_FALSE + ifLabelCount);

        getNextToken(); //consume ) tag
        getNextToken(); //consume { tag
        compileStatements();

        vmWriter.writeGoto(IF_TRUE + ifLabelCount);
        vmWriter.writeLabel(IF_FALSE + ifLabelCount);
        getNextToken(); //consume } tag
        compileElseStatement();
        vmWriter.writeLabel(IF_TRUE + ifLabelCount);
    }

    private void compileElseStatement()
    {
        String nextTokenValue = lookAheadTokenValue(LOOK_AHEAD_ONCE);
        if (nextTokenValue.equals(ELSE_KEYWORD))
        {
            getNextToken(); //consume else tag
            getNextToken(); //consume { tag
            compileStatements();
            getNextToken(); //consume } tag
        }
    }

    public void compileWhile()
    {// while (expression) { statements}

        int whileLabelCount = this.whileLabelCount;
        ++this.whileLabelCount;

        getNextToken(); //consume while tag
        getNextToken(); //consume ( tag

        vmWriter.writeLabel(WHILE_EXP + whileLabelCount);
        clearOutputStringBuilder();
        compileExpression();
        codeWriteExp(outputStringBuilder.toString());
        vmWriter.writeArithmetics(NOT_SYMBOL);

        getNextToken(); //consume ) tag
        getNextToken(); //consume { tag

        vmWriter.writeIf(WHILE_END + whileLabelCount);
        compileStatements();
        vmWriter.writeGoto(WHILE_EXP + whileLabelCount);
        getNextToken(); //consume } tag
        vmWriter.writeLabel(WHILE_END + whileLabelCount);
    }

    public void compileDo()
    {// do subroutineCall;
        int nArgs = 0;
        getNextToken(); // consume do tag

        handleSubroutineCallInDoStatement(nArgs);
    }

    private void handleSubroutineCallInDoStatement(int nArgs)
    {
        clearOutputStringBuilder();
        compileSubroutineCall();
        String functionCallName = getFunctionName();
        if (isMethodCall(functionCallName))
            nArgs = 1; //pass current object as argument
        String finalFunctionCallName = handleProcedureCall(functionCallName);
        if (outputStringBuilder.length() != 0)
            nArgs = nArgs + codeWriteExpList(outputStringBuilder.toString());
        clearOutputStringBuilder();

        vmWriter.writeCall(finalFunctionCallName, nArgs);
        getNextToken(); //consume ; tag
        consumeJunkValueofVoidFunctions();
    }

    private String handleProcedureCall(String functionCallName)
    {
        int indexOfDot = functionCallName.indexOf(DOT_SYMBOL);
        if (indexOfDot != -1)
        {
            //className.functionName
            String classOrVarName = functionCallName.substring(0, indexOfDot);
            if (!symbolTable.isVariableDefinedAnyWhereInClass(classOrVarName))
            {
                //do nothing since static function
                return functionCallName;
            } else
            {//varName.methodName
                return varNameDotMethodName(functionCallName, indexOfDot, classOrVarName);
            }
        } else
        {
            vmWriter.writePush(POINTER_SEGMENT, 0);
            return className + DOT_SYMBOL + functionCallName;
        }
    }

    private String varNameDotMethodName(String functionCallName, int indexOfDot, String classOrVarName)
    {
        String methodName = functionCallName.substring(indexOfDot + 1);
        String segmentValue = symbolTable.kindOf(classOrVarName);
        int index = symbolTable.indexOf(classOrVarName);
        String varClassName = symbolTable.typeOf(classOrVarName);
        vmWriter.writePush(segmentValue, index);
        return varClassName + DOT_SYMBOL + methodName;
    }

    private boolean isMethodCall(String functionCallName)
    {
        int indexOfDot = functionCallName.indexOf(DOT_SYMBOL);
        if (indexOfDot != -1)
        {
            //f1.f2
            String classOrVarName = functionCallName.substring(0, indexOfDot);
            //static method call
            //method call;
            return symbolTable.isVariableDefinedAnyWhereInClass(classOrVarName);
        } else
        {//method call;
            return true;
        }
    }

    public void compileReturn()
    { // return expression? ;
        getNextToken(); //consume return tag
        if (isThereExpression())
        {
            clearOutputStringBuilder();
            compileExpression();
            codeWriteExp(outputStringBuilder.toString());
        }
        vmWriter.writeReturn();
        getNextToken(); //consume ; tag
    }

    private void compileClassName()
    {
        this.variableName = lookAheadTokenValue(LOOK_AHEAD_ONCE);
        getNextToken(); //consume className token
        addClassNameDetails();
    }

    private void addClassNameDetails()
    {

        outputStringBuilder.append(SPACE).append(IDENTIFIER_START_TAG)
                .append(this.variableName)
                .append(COMMA_SYMBOL)
                .append(COMMA_SYMBOL).append(this.variableCategory)
                .append(COMMA_SYMBOL).append(-1)
                .append(COMMA_SYMBOL).append(this.variableState)
                .append(IDENTIFIER_END_TAG).append(NEWLINE);

    }

    private void compileSubroutineName()
    {
        this.variableName = lookAheadTokenValue(LOOK_AHEAD_ONCE);
        getNextToken(); //consume subroutineName tag
        addSubroutineNameDetails();
    }

    private void addSubroutineNameDetails()
    {

        outputStringBuilder.append(SPACE).append(IDENTIFIER_START_TAG)
                .append(this.variableName)
                .append(COMMA_SYMBOL)
                .append(COMMA_SYMBOL).append(this.variableCategory)
                .append(COMMA_SYMBOL).append(-1)
                .append(COMMA_SYMBOL).append(this.variableState)
                .append(IDENTIFIER_END_TAG).append(NEWLINE);

    }

    private void compileCommaSeparatedVarNames()
    {
        while (isThereCommaSeparated())
        {
            this.variableState = VAR_DECLARATION;
            compileComma();
            compileVarName();
            addNewLocalVariable();
        }
    }

    private void compileCommaSeparatedClassVarNames()
    {
        while (isThereCommaSeparated())
        {
            this.variableState = VAR_DECLARATION;
            compileComma();
            compileClassVarName();
            addNewClassVariable();
        }
    }

    private void compileVarName()
    {
        this.variableName = lookAheadTokenValue(LOOK_AHEAD_ONCE);
        getNextToken(); //consume varName
    }

    private void compileClassVarName()
    {
        this.variableName = lookAheadTokenValue(LOOK_AHEAD_ONCE);
        getNextToken(); //consume varName
    }

    private void addNewClassVariable()
    {
        symbolTable.define(this.variableName, this.variableType, this.variableCategory);
        outputStringBuilder.append(SPACE).append(IDENTIFIER_START_TAG)
                .append(this.variableName)
                .append(COMMA_SYMBOL).append(this.variableType)
                .append(COMMA_SYMBOL).append(this.variableCategory)
                .append(COMMA_SYMBOL).append(symbolTable.varCount(symbolTable.kindOf(this.variableName)) - 1)
                .append(COMMA_SYMBOL).append(this.variableState)
                .append(IDENTIFIER_END_TAG).append(NEWLINE);
    }

    private void addExistingClassVariable()
    {
        outputStringBuilder.append(SPACE).append(IDENTIFIER_START_TAG)
                .append(this.variableName)
                .append(COMMA_SYMBOL).append(symbolTable.typeOf(this.variableName))
                .append(COMMA_SYMBOL).append(symbolTable.kindOf(this.variableName))
                .append(COMMA_SYMBOL).append(symbolTable.indexOf(this.variableName))
                .append(COMMA_SYMBOL).append(this.variableState)
                .append(IDENTIFIER_END_TAG).append(NEWLINE);
    }

    private void addNewLocalVariable()
    {
        symbolTable.define(this.variableName, this.variableType, this.variableCategory);
        outputStringBuilder.append(SPACE).append(IDENTIFIER_START_TAG)
                .append(this.variableName)
                .append(COMMA_SYMBOL).append(this.variableType)
                .append(COMMA_SYMBOL).append(this.variableCategory)
                .append(COMMA_SYMBOL).append(symbolTable.varCount(symbolTable.kindOf(this.variableName)) - 1)
                .append(COMMA_SYMBOL).append(this.variableState)
                .append(IDENTIFIER_END_TAG).append(NEWLINE);
    }

    private void addExistingLocalVariable()
    {
        outputStringBuilder.append(SPACE).append(IDENTIFIER_START_TAG)
                .append(this.variableName)
                .append(COMMA_SYMBOL).append(symbolTable.typeOf(this.variableName))
                .append(COMMA_SYMBOL).append(symbolTable.kindOf(this.variableName))
                .append(COMMA_SYMBOL).append(symbolTable.indexOf(this.variableName))
                .append(COMMA_SYMBOL).append(this.variableState)
                .append(IDENTIFIER_END_TAG).append(NEWLINE);
    }

    private void compileType()
    {
        String typeToken, varType;
        varType = lookAheadTokenValue(LOOK_AHEAD_ONCE);
        typeToken = getNextToken();
        this.variableType = varType;
        outputStringBuilder.append(SPACE).append(typeToken).append(NEWLINE);
    }

    private void compileComma()
    {
        String commaToken;
        commaToken = getNextToken();
        outputStringBuilder.append(SPACE).append(commaToken).append(NEWLINE);
    }

    private void compileOp()
    {
        String OpToken;
        OpToken = getNextToken();
        outputStringBuilder.append(SPACE).append(OpToken).append(NEWLINE);
    }

    private void compileUnaryOp()
    {
        String unaryOpToken;
        unaryOpToken = getNextToken();
        outputStringBuilder.append(SPACE).append(unaryOpToken).append(NEWLINE);
    }

    public void compileExpression()
    {// term (op term)*
        outputStringBuilder.append(EXPRESSION_START_TAG).append(NEWLINE);
        compileTerm();
        while (isThereOpTerms())
        {
            compileOpAndTerm();
        }
        outputStringBuilder.append(EXPRESSION_END_TAG).append(NEWLINE);
    }

    private boolean isThereOpTerms()
    {
        String nextTokenValue = lookAheadTokenValue(LOOK_AHEAD_ONCE);
        switch (nextTokenValue)
        {
            case PLUS_SYMBOL:
            case MINUS_SYMBOL:
            case MULTIPLICATION_SYMBOL:
            case DIVISION_SYMBOL:
            case AND_SYMBOL:
            case OR_SYMBOL:
            case GREATER_THAN_SYMBOL:
            case LESS_THAN_SYMBOL:
            case EQUALS_SYMBOL:
                return true;
            default:
                return false;
        }
    }

    private void compileOpAndTerm()
    {
        compileOp();
        compileTerm();
    }

    public void compileTerm()
    {// integerConstant | stringConstant | keywordConstant | varName | varName[expression] |subroutineCall | (expression) | unaryOp term
        String firstTokenType = lookAheadTokenType(LOOK_AHEAD_ONCE);
        String firstTokenValue = lookAheadTokenValue(LOOK_AHEAD_ONCE);
        String secondTokenValue = lookAheadTokenValue(LOOK_AHEAD_TWICE);

        outputStringBuilder.append(TERM_START_TAG).append(NEWLINE);
        if (firstTokenType.equals(INTEGER_CONSTANT) || firstTokenType.equals(STRING_CONSTANT) || isTokenKeywordConstant(firstTokenValue))
        {
            String token = getNextToken();
            outputStringBuilder.append(SPACE).append(token).append(NEWLINE);
        } else if (isUnaryOp(firstTokenValue))
        {
            compileUnaryOp();
            compileTerm();
        } else if (firstTokenValue.equals(OPEN_BRACE_SYMBOL))  //(expression)
        {
            String openBraceToken = getNextToken();
            String closeBraceToken;

            outputStringBuilder.append(SPACE).append(openBraceToken).append(NEWLINE);
            compileExpression();
            closeBraceToken = getNextToken();
            outputStringBuilder.append(SPACE).append(closeBraceToken).append(NEWLINE);
        } else if (isSubroutineCall(firstTokenType, secondTokenValue))
        {
            compileSubroutineCall();
        } else if (firstTokenType.equals(IDENTIFIER) && secondTokenValue.equals(OPEN_SQUARE_BRACE_SYMBOL))
        {
            String openSquareBraceToken;
            String closeSquareBraceToken;
            this.variableState = VAR_USAGE;
            compileVarName();
            addExistingLocalVariable();
            openSquareBraceToken = getNextToken();
            outputStringBuilder.append(SPACE).append(openSquareBraceToken).append(NEWLINE);
            compileExpression();
            closeSquareBraceToken = getNextToken();
            outputStringBuilder.append(SPACE).append(closeSquareBraceToken).append(NEWLINE);
        } else if (firstTokenType.equals(IDENTIFIER))
        {
            this.variableState = VAR_USAGE;
            compileVarName();
            addExistingLocalVariable();
        }
        outputStringBuilder.append(TERM_END_TAG).append(NEWLINE);
    }

    private int codeWriteExpList(String expressionList)
    {
        int length = 0;
        String expression;
        if (isExpListNotEmpty(expressionList))
        {
            expression = getNextExpFromList(expressionList);
            expressionList = removeNextExpFromList(expressionList);
            codeWriteExp(expression);
            length++;
            while (isExpListNotEmpty(expressionList) && isThereCommaSeparatedInExpList(expressionList))
            {
                expressionList = removeNextExpFromList(expressionList); //consume comma symbol
                expression = getNextExpFromList(expressionList);
                expressionList = removeNextExpFromList(expressionList);
                codeWriteExp(expression);
                length++;
            }
        }
        return length;
    }

    private void codeWriteExp(String expression)
    {

        if (isExpInBraces(expression)) //(expression)
        {
            codeWriteExp(getExpWithoutBraces(expression));
        } else if (isNumber(expression))
        {
            String number = getNumberFromExp(expression);
            vmWriter.writePush(CONSTANT_SEGMENT, Integer.parseInt(number));
        } else if (isString(expression))
        {
            String stringConstant = getStringFromExp(expression);
            handleStringsInVM(stringConstant);
        } else if (isVariable(expression))
        {
            String variable = getVariableFromExp(expression);
            int indexOfVar = symbolTable.indexOf(variable);
            String segmentOfVar = symbolTable.kindOf(variable);
            vmWriter.writePush(segmentOfVar, indexOfVar);
        } else if (isKeywordConstant(expression))
        {
            String keyWordConst = getKeywordConst(expression);
            handleVMkeyWordCons(keyWordConst);
        } else if (isExpOpExp(expression))
        {
            String exp1 = getNextElementFromExp(expression);
            expression = removeNextElementFromExp(expression);
            String op = getSymbolFromExp(getNextElementFromExp(expression));
            expression = removeNextElementFromExp(expression);
            String exp2 = getNextElementFromExp(expression);
            codeWriteExp(exp1);
            codeWriteExp(exp2);

            vmWriter.writeArithmetics(op);
        } else if (isOpExp(expression))
        {
            String op = getSymbolFromExp(getNextElementFromUnaryExp(expression));
            expression = removeNextElementFromUnaryExp(expression);
            String exp = getNextElementFromUnaryExp(expression);
            codeWriteExp(exp);
            handlevmUnaryOp(op);
        } else if (isFunctionCall(expression))
        {
            int nArgs = 0;
            String tempfunctionName = getFuncNameFromFuncCallInExp(expression);
            if (isMethodCall(tempfunctionName))
                nArgs = 1;
            String finalFunctionCallName = handleProcedureCall(tempfunctionName);
            String expList = getExpListFromFuncCall(expression);

            nArgs = nArgs + codeWriteExpList(expList);
            vmWriter.writeCall(finalFunctionCallName, nArgs);
        } else if (isArrayExp(expression))
        {
            String arrayName = getArrayNameFromExp(expression);
            int indexOfVar = symbolTable.indexOf(arrayName);
            String segmentOfVar = symbolTable.kindOf(arrayName);
            vmWriter.writePush(segmentOfVar, indexOfVar);
            String exp = getExpFromArray(expression);
            codeWriteExp(exp);
            vmWriter.writeArithmetics(PLUS_SYMBOL);
            vmWriter.writePop(POINTER_SEGMENT, 1);
            vmWriter.writePush(THAT_SEGMENT, 0);
        }
    }


    private boolean isSubroutineCall(String firstTokenType, String secondTokenValue)
    {
        return ((firstTokenType.equals(IDENTIFIER) && secondTokenValue.equals(OPEN_BRACE_SYMBOL))
                || (firstTokenType.equals(IDENTIFIER) && secondTokenValue.equals(DOT_SYMBOL)));
    }

    private void compileSubroutineCall()
    {// subroutineName(expressionList) | (className|varName).subroutineName(expressionList)

        String firstTokenType = lookAheadTokenType(LOOK_AHEAD_ONCE);
        String secondTokenValue = lookAheadTokenValue(LOOK_AHEAD_TWICE);
        String openBraceToken;
        String closeBraceToken;
        String dotToken;
        String nameToken;

        if ((firstTokenType.equals(IDENTIFIER) && secondTokenValue.equals(OPEN_BRACE_SYMBOL)))
        {//f1(exp)
            this.variableCategory = SUBROUTINENAME_VAR;
            this.variableState = VAR_USAGE;
            compileSubroutineName();
            String f1 = this.variableName;
            openBraceToken = getNextToken();
            outputStringBuilder.append(SPACE).append(openBraceToken).append(NEWLINE);
            compileExpressionList();
            closeBraceToken = getNextToken();
            this.subroutineName = f1;
            outputStringBuilder.append(SPACE).append(closeBraceToken).append(NEWLINE);
        } else  //f1.f2(exp)
        {
            nameToken = lookAheadTokenValue(LOOK_AHEAD_ONCE);
            this.variableState = VAR_USAGE;
            if (!symbolTable.isVariableDefinedAnyWhereInClass(nameToken))
            {
                this.variableCategory = CLASSNAME_VAR;
                compileClassName();

            } else
            {
                this.variableCategory = symbolTable.kindOf(nameToken);

                compileVarName();
                addExistingClassVariable();
            }
            String f1 = this.variableName;
            dotToken = getNextToken();
            outputStringBuilder.append(SPACE).append(dotToken).append(NEWLINE);
            this.variableCategory = SUBROUTINENAME_VAR;
            this.variableState = VAR_USAGE;
            compileSubroutineName();
            String f2 = this.variableName;

            openBraceToken = getNextToken();
            outputStringBuilder.append(SPACE).append(openBraceToken).append(NEWLINE);

            compileExpressionList();
            closeBraceToken = getNextToken();
            outputStringBuilder.append(SPACE).append(closeBraceToken).append(NEWLINE);
            this.subroutineName = f1 + DOT_SYMBOL + f2;

        }

    }

    public void compileExpressionList()
    {// (expression (, expression)*)?
        outputStringBuilder.append(EXPRESSION_LIST_START_TAG).append(NEWLINE);
        if (isThereExpression())
        {
            compileExpression();
            while (isThereCommaSeparated())
            {
                compileComma();
                compileExpression();
            }
        }
        outputStringBuilder.append(EXPRESSION_LIST_END_TAG).append(NEWLINE);
    }

    private boolean isThereExpression()
    {
        return isThereTerm();
    }

    private boolean isThereCommaSeparated()
    {
        String nextTokenValue = lookAheadTokenValue(LOOK_AHEAD_ONCE);
        return (nextTokenValue.equals(COMMA_SYMBOL));
    }

    private boolean isThereTerm()
    {
        String firstTokenType = lookAheadTokenType(LOOK_AHEAD_ONCE);
        String firstTokenValue = lookAheadTokenValue(LOOK_AHEAD_ONCE);

        return (firstTokenType.equals(INTEGER_CONSTANT)
                || firstTokenType.equals(STRING_CONSTANT)
                || firstTokenType.equals(KEYWORD_TYPE)
                || firstTokenType.equals(IDENTIFIER)
                || firstTokenValue.equals(OPEN_BRACE_SYMBOL)
                || isUnaryOp(firstTokenValue));
    }

    private boolean isUnaryOp(String tokenValue)
    {
        return (tokenValue.equals(NEGATIVE_SYMBOL)
                || tokenValue.equals(NOT_SYMBOL));
    }


    private String getNextToken()
    {
        int newLinePos = serializedTokens.indexOf(NEWLINE);
        String nextToken = serializedTokens.substring(0, newLinePos);
        serializedTokens.replace(0, newLinePos + 1, EMPTY_STRING);
        return nextToken;
    }

    private String lookAheadTokenValue(int count)
    {
        return lookAheadToken(count, TOKEN_VALUE);
    }

    private String lookAheadTokenType(int count)
    {
        return lookAheadToken(count, TOKEN_TYPE);
    }

    private String lookAheadToken(int count, int option)
    {
        StringBuilder tempSerializedTokens = new StringBuilder(serializedTokens);
        int newLinePos;
        String nextToken = "";
        int greaterThanPos;
        int lessThanPos;

        for (int i = 1; i <= count; ++i)
        {
            newLinePos = tempSerializedTokens.indexOf(NEWLINE);
            nextToken = tempSerializedTokens.substring(0, newLinePos);
            tempSerializedTokens.replace(0, newLinePos + 1, EMPTY_STRING);
        }

        if (option == TOKEN_TYPE)
        {
            greaterThanPos = nextToken.indexOf("<");
            lessThanPos = nextToken.indexOf(">");
        } else //token value
        {
            greaterThanPos = nextToken.indexOf(">");
            lessThanPos = nextToken.lastIndexOf("<");
        }
        return nextToken.substring(greaterThanPos + 1, lessThanPos).trim();
    }

    public void writeToFile()
    {
        vmWriter.writeToFile();
        vmWriter.close();
    }

    private void clearOutputStringBuilder()
    {
        outputStringBuilder.setLength(0);
    }

    public void close()
    {
        try
        {
            bufferedReader.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void returnForVoidFunctions()
    {
        if (this.functionReturnType.equals(VOID_TYPE))
        {
            vmWriter.writePush(CONSTANT_SEGMENT, 0);
        }

    }

    private void consumeJunkValueofVoidFunctions()
    {
        vmWriter.writePop(TEMP_SEGMENT, 0);
    }

    private String getFunctionName()
    {
        int subNamePos = outputStringBuilder.lastIndexOf(NEWLINE, outputStringBuilder.indexOf(OPEN_BRACE_SYMBOL));
        String subName = outputStringBuilder.substring(0, subNamePos);
        int endPos = outputStringBuilder.indexOf(NEWLINE, outputStringBuilder.indexOf(OPEN_BRACE_SYMBOL));

        outputStringBuilder.replace(0, endPos + 1, EMPTY_STRING);
        int lastSymbolPos = outputStringBuilder.lastIndexOf(SYMBOL_START_TAG);
        outputStringBuilder.replace(lastSymbolPos, outputStringBuilder.length(), EMPTY_STRING);
        subName = EXPRESSION_START_TAG + subName + EXPRESSION_END_TAG;
        return getFuncNameFromFuncCall(subName);

    }

    private void handlevmUnaryOp(String op)
    {
        if (op.equals(NOT_SYMBOL))
            vmWriter.writeArithmetics(op);
        else
            vmWriter.writeArithmetics(NEG_UNARY_SYMBOL);
    }

    private boolean isTokenKeywordConstant(String input)
    {
        switch (input)
        {
            case KEYWORD_CONS_TRUE:
            case KEYWORD_CONS_FALSE:
            case KEYWORD_CONS_NULL:
            case KEYWORD_CONS_THIS:
                return true;
        }
        return false;
    }

    private void handleVMkeyWordCons(String keywordConstant)
    {
        switch (keywordConstant)
        {
            case KEYWORD_CONS_TRUE:
                vmWriter.writePush(CONSTANT_SEGMENT, 1);
                vmWriter.writeArithmetics(NEG_UNARY_SYMBOL);
                break;
            case KEYWORD_CONS_FALSE:
            case KEYWORD_CONS_NULL:
                vmWriter.writePush(CONSTANT_SEGMENT, 0);
                break;
            case KEYWORD_CONS_THIS:
                vmWriter.writePush(POINTER_SEGMENT, 0);
                break;
        }
    }

    private void removeSquareBrackets()
    {
        int indexOfFirstNewline = outputStringBuilder.indexOf(NEWLINE);
        outputStringBuilder.replace(0, indexOfFirstNewline + 1, EMPTY_STRING);
        int indexOfLastLine = outputStringBuilder.lastIndexOf(SYMBOL_START_TAG);
        outputStringBuilder.replace(indexOfLastLine, outputStringBuilder.length(), EMPTY_STRING);
    }

    private void handleStringsInVM(String stringConstant)
    {
        int strLength = stringConstant.length();
        vmWriter.writePush(CONSTANT_SEGMENT, strLength);
        vmWriter.writeCall(STRING_NEW, 1);
        for (int i = 0; i < strLength; ++i)
        {
            vmWriter.writePush(CONSTANT_SEGMENT, stringConstant.charAt(i));
            vmWriter.writeCall(STRING_APPEND_CHAR, 2);
        }
    }
}