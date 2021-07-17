package JackTokenizer;

public class Constants
{
    public static final String KEYWORD_CLASS = "class";
    public static final String KEYWORD_FUNCTION = "function";
    public static final String KEYWORD_FIELD = "field";
    public static final String KEYWORD_VAR = "var";
    public static final String KEYWORD_LET = "let";
    public static final String KEYWORD_METHOD = "method";
    public static final String KEYWORD_CONSTRUCTOR = "constructor";
    public static final String KEYWORD_INT = "int";
    public static final String KEYWORD_BOOLEAN = "boolean";
    public static final String KEYWORD_CHAR = "char";
    public static final String KEYWORD_VOID = "void";
    public static final String KEYWORD_STATIC = "static";
    public static final String KEYWORD_DO = "do";
    public static final String KEYWORD_IF = "if";
    public static final String KEYWORD_ELSE = "else";
    public static final String KEYWORD_WHILE = "while";
    public static final String KEYWORD_RETURN = "return";
    public static final String KEYWORD_TRUE = "true";
    public static final String KEYWORD_FALSE = "false";
    public static final String KEYWORD_NULL = "null";
    public static final String KEYWORD_THIS = "this";

    public static final String TOKENS_START_TAG = "<tokens>";
    public static final String TOKENS_END_TAG  = "</tokens>";
    public static final String SYMBOL_TAG = "symbol";
    public static final String KEYWORD_TAG = "keyword";
    public static final String IDENTIFIER_TAG = "identifier";
    public static final String INTEGER_CONSTANT_TAG = "integerConstant";
    public static final String STRING_CONSTANT_TAG = "stringConstant";
    public static final String NEWLINE = "\n";
    public static final String EMPTY_STRING = "";
    public static final String XML_LESS_THAN = "&lt;";
    public static final String XML_GREATER_THAN = "&gt;";
    public static final String XML_AND = "&amp;";
    public static final char SYMBOL_OPEN_CURLY_BRACE = '{';
    public static final char SYMBOL_CLOSE_CURLY_BRACE = '}';
    public static final char SYMBOL_OPEN_BRACE = '(';
    public static final char SYMBOL_CLOSE_BRACE = ')';
    public static final char SYMBOL_OPEN_SQUARE_BRACE = '[';
    public static final char SYMBOL_CLOSE_SQUARE_BRACE = ']';
    public static final char SYMBOL_PERIOD = '.';
    public static final char SYMBOL_COMMA = ',';
    public static final char SYMBOL_SEMI_COLON = ';';
    public static final char SYMBOL_PLUS = '+';
    public static final char SYMBOL_MINUS = '-';
    public static final char SYMBOL_MULTIPLICATION = '*';
    public static final char SYMBOL_DIVISION = '/';
    public static final char SYMBOL_AND = '&';
    public static final char SYMBOL_OR = '|';
    public static final char SYMBOL_LESS_THAN = '<';
    public static final char SYMBOL_GREATER_THAN = '>';
    public static final char SYMBOL_EQUALS = '=';
    public static final char SYMBOL_NOT = '~';
    public static final String Comment = "//";
    public static final String blockCommentStart = "/*";
    public static final String blockCommentEnd = "*/";
    public static final char WhiteSpace = ' ';

    private Constants()
    {
    }

    public enum TOKENS
    {
        TOKEN_KEYWORD,
        TOKEN_SYMBOL,
        TOKEN_IDENTIFIER,
        TOKEN_INT_CONST,
        TOKEN_STRING_CONST
    }

}
