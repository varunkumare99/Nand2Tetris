package Driver;

public class Constants
{
    public static final int C_ARITHMETIC = 0;
    public static final int C_PUSH = 1;
    public static final int C_POP = 2;
    public static final int C_LABEL = 3;
    public static final int C_GOTO = 4;
    public static final int C_IF = 5;
    public static final int C_FUNCTION = 6;
    public static final int C_RETURN = 7;
    public static final int C_CALL = 8;

    public static final String ARITHMETIC_ADD = "add";
    public static final String ARITHMETIC_SUB = "sub";
    public static final String ARITHMETIC_NEG = "neg";
    public static final String ARITHMETIC_EQ = "eq";
    public static final String ARITHMETIC_GT = "gt";
    public static final String ARITHMETIC_LT = "lt";
    public static final String ARITHMETIC_AND = "and";
    public static final String ARITHMETIC_OR = "or";
    public static final String ARITHMETIC_NOT = "not";

    public static final String STACK_PUSH = "push";
    public static final String STACK_POP = "pop";
    public static final String BRANCH_LABEL = "label";
    public static final String BRANCH_GOTO = "goto";
    public static final String BRANCH_IF_GOTO = "if-goto";
    public static final String FUNCTION_DEFINITION = "function";
    public static final String FUNCTION_CALL = "call";
    public static final String FUNCTION_RETURN = "return";

    public static final String LOCAL_SEGMENT = "local";
    public static final String ARGUMENT_SEGMENT = "argument";
    public static final String THIS_SEGMENT = "this";
    public static final String THAT_SEGMENT = "that";
    public static final String TEMP_SEGMENT = "temp";
    public static final String STATIC_SEGMENT = "static";
    public static final String POINTER_SEGMENT = "pointer";
    public static final String CONSTANT_SEGMENT = "constant";

    public static final String Comment = "//";
    public static final String WhiteSpace = " ";

    private Constants()
    {
    }

}
