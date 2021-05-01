package Driver;

import java.util.HashMap;

public class Constants
{
    public static final String R0 = "R0";
    public static final String R1 = "R1";
    public static final String R2 = "R2";
    public static final String R3 = "R3";
    public static final String R4 = "R4";
    public static final String R5 = "R5";
    public static final String R6 = "R6";
    public static final String R7 = "R7";
    public static final String R8 = "R8";
    public static final String R9 = "R9";
    public static final String R10 = "R10";
    public static final String R11 = "R11";
    public static final String R12 = "R12";
    public static final String R13 = "R13";
    public static final String R14 = "R14";
    public static final String R15 = "R15";
    public static final String SCREEN = "SCREEN";
    public static final String KBD = "KBD";
    public static final String SP = "SP";
    public static final String LCL = "LCL";
    public static final String ARG = "ARG";
    public static final String THIS = "THIS";
    public static final String THAT = "THAT";


    //dest
    public static final String dest_NULL_DEST = "null";
    public static final String dest_M = "M";
    public static final String dest_D = "D";
    public static final String dest_MD = "MD";
    public static final String dest_A = "A";
    public static final String dest_AM = "AM";
    public static final String dest_AD = "AD";
    public static final String dest_AMD = "AMD";

    //jump
    public static final String jump_NULL_JUMP = "null";
    public static final String jump_JGT = "JGT";
    public static final String jump_JEQ = "JEQ";
    public static final String jump_JGE = "JGE";
    public static final String jump_JLT = "JLT";
    public static final String jump_JNE = "JNE";
    public static final String jump_JLE = "JLE";
    public static final String jump_JMP = "JMP";

    //comp
    public static final String comp_zero = "0";
    public static final String comp_one = "1";
    public static final String comp_minus1 = "-1";
    public static final String comp_D = "D";
    public static final String comp_A = "A";
    public static final String comp_M = "M";
    public static final String comp_notD = "!D";
    public static final String comp_notA = "!A";
    public static final String comp_notM = "!M";
    public static final String comp_minusD = "-D";
    public static final String comp_minusA = "-A";
    public static final String comp_minusM = "-M";
    public static final String comp_Dplus1 = "D+1";
    public static final String comp_Aplus1 = "A+1";
    public static final String comp_Mplus1 = "M+1";
    public static final String comp_Dminus1 = "D-1";
    public static final String comp_Aminus1 = "A-1";
    public static final String comp_Mminus1 = "M-1";
    public static final String comp_DplusA = "D+A";
    public static final String comp_DplusM = "D+M";
    public static final String comp_DminusA = "D-A";
    public static final String comp_DminusM = "D-M";
    public static final String comp_AminusD = "A-D";
    public static final String comp_MminusD = "M-D";
    public static final String comp_DandA = "D&A";
    public static final String comp_DandM = "D&M";
    public static final String comp_DorA = "D|A";
    public static final String comp_DorM = "D|M";

    public static final Integer A_COMMAND = 0;
    public static final Integer C_COMMAND = 1;
    public static final Integer L_COMMAND = 2;

    public static final Integer FirstPass = 1;
    public static final Integer SecondPass = 2;

    public static final String Comment = "//";
    public static final String WhiteSpace = " ";
    public static final String LabelOpen = "(";
    public static final String LabelClose = ")";
    public static final String Ainstruction = "@";
    public static final String semiColon = ";";
    public static final String equals = "=";
    public static final String CinstructionPrefix = "111";
    public static final Integer StartingMemoryLocation = 16;
    public static final Integer StartingNextLineNumber = 1;


    public static final HashMap<String, String> destMap = new HashMap<>();
    public static final HashMap<String, String> jumpMap = new HashMap<>();
    public static final HashMap<String, String> compMap = new HashMap<>();


    static
    {
        destMap.put(dest_NULL_DEST, "000");
        destMap.put(dest_M, "001");
        destMap.put(dest_D, "010");
        destMap.put(dest_MD, "011");
        destMap.put(dest_A, "100");
        destMap.put(dest_AM, "101");
        destMap.put(dest_AD, "110");
        destMap.put(dest_AMD, "111");

        jumpMap.put(jump_NULL_JUMP, "000");
        jumpMap.put(jump_JGT, "001");
        jumpMap.put(jump_JEQ, "010");
        jumpMap.put(jump_JGE, "011");
        jumpMap.put(jump_JLT, "100");
        jumpMap.put(jump_JNE, "101");
        jumpMap.put(jump_JLE, "110");
        jumpMap.put(jump_JMP, "111");

        compMap.put(comp_zero, "0101010");
        compMap.put(comp_one, "0111111");
        compMap.put(comp_minus1, "0111010");
        compMap.put(comp_D, "0001100");
        compMap.put(comp_A, "0110000");
        compMap.put(comp_M, "1110000");
        compMap.put(comp_notD, "0001101");
        compMap.put(comp_notA, "0110001");
        compMap.put(comp_notM, "1110001");
        compMap.put(comp_minusD, "1001111");
        compMap.put(comp_minusA, "0110011");
        compMap.put(comp_minusM, "1110011");
        compMap.put(comp_Dplus1, "0011111");
        compMap.put(comp_Aplus1, "0110111");
        compMap.put(comp_Mplus1, "1110111");
        compMap.put(comp_Dminus1, "0001110");
        compMap.put(comp_Aminus1, "0110010");
        compMap.put(comp_Mminus1, "1110010");
        compMap.put(comp_DplusA, "0000010");
        compMap.put(comp_DplusM, "1000010");
        compMap.put(comp_DminusA, "0010011");
        compMap.put(comp_DminusM, "1010011");
        compMap.put(comp_AminusD, "0000111");
        compMap.put(comp_MminusD, "1000111");
        compMap.put(comp_DandA, "0000000");
        compMap.put(comp_DandM, "1000000");
        compMap.put(comp_DorA, "0010101");
        compMap.put(comp_DorM, "1010101");
    }

}
