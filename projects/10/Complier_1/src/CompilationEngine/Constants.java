package CompilationEngine;

public class Constants
{
    public static final String CLASS_START_TAG = "<class>";
    public static final String CLASS_END_TAG = "</class>";
    public static final String CLASS_VAR_DEC_START_TAG = "<classVarDec>";
    public static final String CLASS_VAR_DEC_END_TAG = "</classVarDec>";
    public static final String SUBROUTINE_START_TAG = "<subroutineDec>";
    public static final String SUBROUTINE_END_TAG = "</subroutineDec>";
    public static final String PARAMETER_LIST_START_TAG = "<parameterList>";
    public static final String PARAMETER_LIST_END_TAG = "</parameterList>";
    public static final String SUBROUTINE_BODY_START_TAG = "<subroutineBody>";
    public static final String SUBROUTINE_BODY_END_TAG = "</subroutineBody>";
    public static final String VAR_START_TAG = "<varDec>";
    public static final String VAR_END_TAG = "</varDec>";
    public static final String STATEMENTS_START_TAG = "<statements>";
    public static final String STATEMENTS_END_TAG = "</statements>";
    public static final String LET_STATEMENT_START_TAG = "<letStatement>";
    public static final String LET_STATEMENT_END_TAG = "</letStatement>";
    public static final String IF_STATEMENT_START_TAG = "<ifStatement>";
    public static final String IF_STATEMENT_END_TAG = "</ifStatement>";
    public static final String WHILE_STATEMENT_START_TAG = "<whileStatement>";
    public static final String WHILE_STATEMENT_END_TAG = "</whileStatement>";
    public static final String DO_STATEMENT_START_TAG = "<doStatement>";
    public static final String DO_STATEMENT_END_TAG = "</doStatement>";
    public static final String RETURN_STATEMENT_START_TAG = "<returnStatement>";
    public static final String RETURN_STATEMENT_END_TAG = "</returnStatement>";
    public static final String TERM_START_TAG = "<term>";
    public static final String TERM_END_TAG = "</term>";
    public static final String EXPRESSION_START_TAG = "<expression>";
    public static final String EXPRESSION_END_TAG = "</expression>";
    public static final String EXPRESSION_LIST_START_TAG = "<expressionList>";
    public static final String EXPRESSION_LIST_END_TAG = "</expressionList>";

    public static final String SPACE = "  ";
    public static final String NEWLINE = "\n";

    public static final String STATIC_FIELD = "static";
    public static final String PLAIN_FIELD = "field";
    public static final String CONSTRUCTOR_PROCEDURE = "constructor";
    public static final String FUNCTION_PROCEDURE = "function";
    public static final String METHOD_PROCEDURE = "method";
    public static final String VARIABLE = "var";
    public static final String LET_STATEMENT = "let";
    public static final String IF_STATEMENT = "if";
    public static final String WHILE_STATEMENT = "while";
    public static final String DO_STATEMENT = "do";
    public static final String RETURN_STATEMENT = "return";

    public static final String COMMA_SYMBOL = ",";
    public static final String ELSE_KEYWORD = "else";
    public static final String INT_TYPE = "int";
    public static final String CHAR_TYPE = "char";
    public static final String BOOLEAN_TYPE = "boolean";
    public static final String IDENTIFIER = "identifier";

    public static final String PLUS_SYMBOL = "+";
    public static final String MINUS_SYMBOL = "-";
    public static final String MULTIPLICATION_SYMBOL = "*";
    public static final String DIVISION_SYMBOL = "/";
    public static final String AND_SYMBOL = "&amp;";
    public static final String OR_SYMBOL = "|";
    public static final String GREATER_THAN_SYMBOL = "&gt;";
    public static final String LESS_THAN_SYMBOL = "&lt;";
    public static final String EQUALS_SYMBOL = "=";
    public static final String NEGATIVE_SYMBOL = "-";
    public static final String NOT_SYMBOL = "~";
    public static final String OPEN_BRACE_SYMBOL = "(";
    public static final String OPEN_SQUARE_BRACE_SYMBOL = "[";
    public static final String DOT_SYMBOL = ".";

    public static final int LOOK_AHEAD_ONCE = 1;
    public static final int LOOK_AHEAD_TWICE = 2;
    public static final int TOKEN_TYPE = 1;
    public static final int TOKEN_VALUE = 2;

    public static final String INTEGER_CONSTANT = "integerConstant";
    public static final String STRING_CONSTANT = "stringConstant";
    public static final String KEYWORD_TYPE = "keyword";

}
