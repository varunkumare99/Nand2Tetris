package CompilationEngine;

public class Constants
{
    public static final String TERM_START_TAG = "<term>";
    public static final String TERM_END_TAG = "</term>";
    public static final String EXPRESSION_START_TAG = "<expression>";
    public static final String EXPRESSION_END_TAG = "</expression>";
    public static final String EXPRESSION_LIST_START_TAG = "<expressionList>";
    public static final String EXPRESSION_LIST_END_TAG = "</expressionList>";
    public static final String IDENTIFIER_START_TAG = "<identifier>";
    public static final String IDENTIFIER_END_TAG = "</identifier>";
    public static final String SYMBOL_START_TAG = "<symbol>";
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
    public static final String NEG_UNARY_SYMBOL = "neg";
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
    public static final String VAR_DECLARATION = "declaration";
    public static final String VAR_USAGE = "usage";

    public static final String CONSTANT_SEGMENT = "constant";
    public static final String POINTER_SEGMENT = "pointer";
    public static final String ARGUMENT_SEGMENT = "argument";
    public static final String THAT_SEGMENT = "that";
    public static final String TEMP_SEGMENT = "temp";
    public static final String VOID_TYPE= "void";
    public static final String IF_TRUE = "IF_TRUE";
    public static final String IF_FALSE = "IF_FALSE";
    public static final String WHILE_EXP = "WHILE_EXP";
    public static final String WHILE_END = "WHILE_END";

    public static final String KEYWORD_CONS_TRUE = "true";
    public static final String KEYWORD_CONS_FALSE = "false";
    public static final String KEYWORD_CONS_NULL = "null";
    public static final String KEYWORD_CONS_THIS = "this";

    public static final String STRING_NEW = "String.new";
    public static final String STRING_APPEND_CHAR = "String.appendChar";
    public static final String EMPTY_STRING = "";
    public static final String MEMORY_ALLOC = "Memory.alloc";

    private Constants()
    {
    }

}
