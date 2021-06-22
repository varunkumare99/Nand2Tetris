package CompilationEngine;

import java.io.*;

import static CompilationEngine.Constants.*;

public class CompilationEngine
{
    BufferedReader bufferedReader;
    PrintWriter printWriter;
    StringBuilder outputStringBuilder;
    String inputFileName;
    String outputFileName;
    int spaceCount = 0;

    StringBuilder serializedTokens;


    public CompilationEngine(String inputFileName, String outputFileName, StringBuilder serializedTokens)
    {
        try
        {
            this.inputFileName = inputFileName;
            bufferedReader = new BufferedReader(new FileReader(this.inputFileName));
            this.outputFileName = outputFileName;
            printWriter = new PrintWriter(new FileWriter(outputFileName));
            outputStringBuilder = new StringBuilder();
            this.serializedTokens = serializedTokens;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void compileClass()
    { // class className { classVarDec* subroutineDec* }

        String classToken;
        String openCurlyBraceToken;
        String closeCurlyBraceToken;
        getNextToken(); //consume <token> tag

        spaceCount = 0;
        outputStringBuilder.append(getSpace()).append(CLASS_START_TAG).append(NEWLINE);
        classToken = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(classToken).append(NEWLINE);
        compileClassName();
        openCurlyBraceToken = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(openCurlyBraceToken).append(NEWLINE);
        compileClassVarDecs();
        compileSubroutineDecs();
        closeCurlyBraceToken = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(closeCurlyBraceToken).append(NEWLINE);
        outputStringBuilder.append(getSpace()).append(CLASS_END_TAG).append(NEWLINE);
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
    {// (static|field)type varName (',' varname)* ;

        String staticOrFieldToken;
        String semiColonToken;

        ++spaceCount;
        outputStringBuilder.append(getSpace()).append(CLASS_VAR_DEC_START_TAG).append(NEWLINE);
        staticOrFieldToken = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(staticOrFieldToken).append(NEWLINE);
        compileType();
        compileVarName();
        compileCommaSeparatedVarNames();
        semiColonToken = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(semiColonToken).append(NEWLINE);
        outputStringBuilder.append(getSpace()).append(CLASS_VAR_DEC_END_TAG).append(NEWLINE);
        --spaceCount;
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

        String typeFunctionToken; //constructor or function or method
        String functionReturnTypeToken;
        String openBraceToken; // '('
        String closeBraceToken;

        ++spaceCount;
        outputStringBuilder.append(getSpace()).append(SUBROUTINE_START_TAG).append(NEWLINE);
        typeFunctionToken = getNextToken();
        functionReturnTypeToken = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(typeFunctionToken).append(NEWLINE);
        outputStringBuilder.append(getSpace()).append(SPACE).append(functionReturnTypeToken).append(NEWLINE);
        compileSubroutineName();
        openBraceToken = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(openBraceToken).append(NEWLINE);
        compileParameterList();
        closeBraceToken = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(closeBraceToken).append(NEWLINE);
        compileSubroutineBody();
        outputStringBuilder.append(getSpace()).append(SUBROUTINE_END_TAG).append(NEWLINE);
        --spaceCount;
    }

    public void compileParameterList()
    {// ((type varName) (, type varName)*)?
        ++spaceCount;
        outputStringBuilder.append(getSpace()).append(PARAMETER_LIST_START_TAG).append(NEWLINE);
        if (isThereParameter())
        {
            compileType();
            compileVarName();
            while (isThereCommaSeparated())
            {
                compileComma();
                compileType();
                compileVarName();
            }
        }
        outputStringBuilder.append(getSpace()).append(PARAMETER_LIST_END_TAG).append(NEWLINE);
        --spaceCount;
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

    public void compileSubroutineBody()
    { // { varDec* statements}

        String openCurlyBraceToken;
        String closeCurlyBraceToken;

        ++spaceCount;
        outputStringBuilder.append(getSpace()).append(SUBROUTINE_BODY_START_TAG).append(NEWLINE);
        openCurlyBraceToken = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(openCurlyBraceToken).append(NEWLINE);
        compileVarDecs();
        compileStatements();
        closeCurlyBraceToken = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(closeCurlyBraceToken).append(NEWLINE);
        outputStringBuilder.append(getSpace()).append(SUBROUTINE_BODY_END_TAG).append(NEWLINE);
        --spaceCount;
    }

    public void compileVarDecs()
    {
        while (isThereVarDecs())
        {
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

        String varToken;
        String semiColon;

        ++spaceCount;
        outputStringBuilder.append(getSpace()).append(VAR_START_TAG).append(NEWLINE);
        varToken = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(varToken).append(NEWLINE);
        compileType();
        compileVarName();
        compileCommaSeparatedVarNames();
        semiColon = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(semiColon).append(NEWLINE);
        outputStringBuilder.append(getSpace()).append(VAR_END_TAG).append(NEWLINE);
        --spaceCount;
    }

    public void compileStatements()
    { // statement*
        --spaceCount;
        outputStringBuilder.append(getSpace()).append(STATEMENTS_START_TAG).append(NEWLINE);;
        while (isThereStatements())
        {
            compileStatement();
        }
        outputStringBuilder.append(getSpace()).append(STATEMENTS_END_TAG).append(NEWLINE);;
        ++spaceCount;
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
                compileReturn();
                break;
            case DO_STATEMENT:
                compileDo();
                break;
        }
    }

    public void compileLet()
    { // let varName ([expression])? = expression;

        String letToken;
        String equalsToken;
        String semiColonToken;


        ++spaceCount;
        outputStringBuilder.append(getSpace()).append(LET_STATEMENT_START_TAG).append(NEWLINE);
        letToken = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(letToken).append(NEWLINE);
        compileVarName();
        compileSquareExpression();
        equalsToken = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(equalsToken).append(NEWLINE);
        compileExpression();
        semiColonToken = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(semiColonToken).append(NEWLINE);
        outputStringBuilder.append(getSpace()).append(LET_STATEMENT_END_TAG).append(NEWLINE);
        --spaceCount;
    }

    private void compileSquareExpression()
    {
        String firstTokenValue = lookAheadTokenValue(LOOK_AHEAD_ONCE);
        String openSquareBraceToken;
        String closeSquareBraceToken;
        if (firstTokenValue.equals(OPEN_SQUARE_BRACE_SYMBOL))
        {
            openSquareBraceToken = getNextToken();
            outputStringBuilder.append(getSpace()).append(SPACE).append(openSquareBraceToken).append(NEWLINE);
            compileExpression();
            closeSquareBraceToken = getNextToken();
            outputStringBuilder.append(getSpace()).append(SPACE).append(closeSquareBraceToken).append(NEWLINE);
        }

    }

    public void compileIf()
    {// if (expression) {statements} (else {statements})?

        String ifToken;
        String openBrace;
        String closeBrace;
        String openCurlyBrace;
        String closeCurlyBrace;

        ++spaceCount;
        outputStringBuilder.append(getSpace()).append(IF_STATEMENT_START_TAG).append(NEWLINE);
        ifToken = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(ifToken).append(NEWLINE);
        openBrace = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(openBrace).append(NEWLINE);
        compileExpression();
        closeBrace = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(closeBrace).append(NEWLINE);
        openCurlyBrace = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(openCurlyBrace).append(NEWLINE);
        compileStatements();
        closeCurlyBrace = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(closeCurlyBrace).append(NEWLINE);
        compileElseStatement();
        outputStringBuilder.append(getSpace()).append(IF_STATEMENT_END_TAG).append(NEWLINE);
        --spaceCount;
    }

    private void compileElseStatement()
    {
        String nextTokenValue = lookAheadTokenValue(LOOK_AHEAD_ONCE);
        String openCurlyBrace;
        String closeCurlyBrace;
        String elseToken;
        if (nextTokenValue.equals(ELSE_KEYWORD))
        {
            elseToken = getNextToken();
            outputStringBuilder.append(getSpace()).append(SPACE).append(elseToken).append(NEWLINE);
            openCurlyBrace = getNextToken();
            outputStringBuilder.append(getSpace()).append(SPACE).append(openCurlyBrace).append(NEWLINE);
            compileStatements();
            closeCurlyBrace = getNextToken();
            outputStringBuilder.append(getSpace()).append(SPACE).append(closeCurlyBrace).append(NEWLINE);
        }
    }

    public void compileWhile()
    {// while (expression) { statements}

        String whileToken;
        String openBrace;
        String closeBrace;
        String openCurlyBrace;
        String closeCurlyBrace;

        ++spaceCount;
        outputStringBuilder.append(getSpace()).append(WHILE_STATEMENT_START_TAG).append(NEWLINE);
        whileToken = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(whileToken).append(NEWLINE);
        openBrace = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(openBrace).append(NEWLINE);
        compileExpression();
        closeBrace = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(closeBrace).append(NEWLINE);
        openCurlyBrace = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(openCurlyBrace).append(NEWLINE);
        compileStatements();
        closeCurlyBrace = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(closeCurlyBrace).append(NEWLINE);
        outputStringBuilder.append(getSpace()).append(WHILE_STATEMENT_END_TAG).append(NEWLINE);
        --spaceCount;
    }

    public void compileDo()
    {// do subroutineCall;

        String doToken;
        String semiColon;

        ++spaceCount;
        outputStringBuilder.append(getSpace()).append(DO_STATEMENT_START_TAG).append(NEWLINE);
        doToken = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(doToken).append(NEWLINE);
        compileSubroutineCall();
        semiColon = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(semiColon).append(NEWLINE);
        outputStringBuilder.append(getSpace()).append(DO_STATEMENT_END_TAG).append(NEWLINE);
        --spaceCount;
    }

    public void compileReturn()
    { // return expression? ;

        String returnToken;
        String semiColon;

        ++spaceCount;
        outputStringBuilder.append(getSpace()).append(RETURN_STATEMENT_START_TAG).append(NEWLINE);
        returnToken = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(returnToken).append(NEWLINE);
        if (isThereExpression())
        {
            compileExpression();
        }
        semiColon = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(semiColon).append(NEWLINE);
        outputStringBuilder.append(getSpace()).append(RETURN_STATEMENT_END_TAG).append(NEWLINE);
        --spaceCount;
    }

    private void compileClassName()
    {
        String className;
        className = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(className).append(NEWLINE);
    }

    private void compileSubroutineName()
    {
        String subroutineNameToken;
        subroutineNameToken = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(subroutineNameToken).append(NEWLINE);
    }

    private void compileCommaSeparatedVarNames()
    {
        while (isThereCommaSeparated())
        {
            compileComma();
            compileVarName();
        }
    }

    private void compileVarName()
    {
        String varNameToken;
        varNameToken = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(varNameToken).append(NEWLINE);
    }

    private void compileType()
    {
        String typeToken;
        typeToken = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(typeToken).append(NEWLINE);
    }

    private void compileComma()
    {
        String commaToken;
        commaToken = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(commaToken).append(NEWLINE);
    }

    private void compileOp()
    {
        String OpToken;
        OpToken = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(OpToken).append(NEWLINE);
    }

    private void compileUnaryOp()
    {
        String unaryOpToken;
        unaryOpToken = getNextToken();
        outputStringBuilder.append(getSpace()).append(SPACE).append(unaryOpToken).append(NEWLINE);
    }

    public void compileExpression()
    {// term (op term)*
        ++spaceCount;
        outputStringBuilder.append(getSpace()).append(EXPRESSION_START_TAG).append(NEWLINE);
        compileTerm();
        while (isThereOpTerms())
        {
            compileOpAndTerm();
        }
        outputStringBuilder.append(getSpace()).append(EXPRESSION_END_TAG).append(NEWLINE);
        --spaceCount;
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

        ++spaceCount;
        outputStringBuilder.append(getSpace()).append(TERM_START_TAG).append(NEWLINE);
        if (firstTokenType.equals(INTEGER_CONSTANT) || firstTokenType.equals(STRING_CONSTANT) || firstTokenType.equals(KEYWORD_TYPE))
        {
            String token = getNextToken();
            outputStringBuilder.append(getSpace()).append(SPACE).append(token).append(NEWLINE);
        } else if (isUnaryOp(firstTokenValue))
        {
            compileUnaryOp();
            compileTerm();
        } else if (firstTokenValue.equals(OPEN_BRACE_SYMBOL))
        {
            String openBraceToken = getNextToken();
            String closeBraceToken;

            outputStringBuilder.append(getSpace()).append(SPACE).append(openBraceToken).append(NEWLINE);
            compileExpression();
            closeBraceToken = getNextToken();
            outputStringBuilder.append(getSpace()).append(SPACE).append(closeBraceToken).append(NEWLINE);
        } else if (isSubroutineCall(firstTokenType, secondTokenValue))
        {
            compileSubroutineCall();
        } else if (firstTokenType.equals(IDENTIFIER) && secondTokenValue.equals(OPEN_SQUARE_BRACE_SYMBOL))
        {
            String openSquareBraceToken;
            String closeSquareBraceToken;
            compileVarName();

            openSquareBraceToken = getNextToken();
            outputStringBuilder.append(getSpace()).append(SPACE).append(openSquareBraceToken).append(NEWLINE);
            compileExpression();
            closeSquareBraceToken = getNextToken();
            outputStringBuilder.append(getSpace()).append(SPACE).append(closeSquareBraceToken).append(NEWLINE);
        } else if (firstTokenType.equals(IDENTIFIER))
        {
            compileVarName();
        }
        outputStringBuilder.append(getSpace()).append(TERM_END_TAG).append(NEWLINE);
        --spaceCount;
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
        { //fuc(exp)
            compileSubroutineName();
            openBraceToken = getNextToken();
            outputStringBuilder.append(getSpace()).append(SPACE).append(openBraceToken).append(NEWLINE);
            compileExpressionList();
            closeBraceToken = getNextToken();
            outputStringBuilder.append(getSpace()).append(SPACE).append(closeBraceToken).append(NEWLINE);
        } else //func1.func2(exp)
        {
            nameToken = getNextToken();
            outputStringBuilder.append(getSpace()).append(SPACE).append(nameToken).append(NEWLINE);
            dotToken = getNextToken();
            outputStringBuilder.append(getSpace()).append(SPACE).append(dotToken).append(NEWLINE);
            compileSubroutineName();
            openBraceToken = getNextToken();
            outputStringBuilder.append(getSpace()).append(SPACE).append(openBraceToken).append(NEWLINE);
            compileExpressionList();
            closeBraceToken = getNextToken();
            outputStringBuilder.append(getSpace()).append(SPACE).append(closeBraceToken).append(NEWLINE);
        }

    }

    public void compileExpressionList()
    {// (expression (, expression)*)?
        outputStringBuilder.append(getSpace()).append(EXPRESSION_LIST_START_TAG).append(NEWLINE);
        if (isThereExpression())
        {
            compileExpression();
            while (isThereCommaSeparated())
            {
                compileComma();
                compileExpression();
            }
        }
        outputStringBuilder.append(getSpace()).append(EXPRESSION_LIST_END_TAG).append(NEWLINE);
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
        serializedTokens.replace(0, newLinePos + 1, "");
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
            tempSerializedTokens.replace(0, newLinePos + 1, "");
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

    private String getSpace()
    {
        StringBuilder tabs = new StringBuilder();
        for (int i = 0; i < spaceCount; ++i)
            tabs.append("  ");
        return tabs.toString();
    }

    public void writeToFile()
    {
        printWriter.write(outputStringBuilder.toString() + "\n\n");
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
        printWriter.close();
    }
}
