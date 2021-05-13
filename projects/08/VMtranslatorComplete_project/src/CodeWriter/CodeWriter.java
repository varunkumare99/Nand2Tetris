package CodeWriter;

import Driver.Constants;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CodeWriter
{
    PrintWriter printWriter;
    String outputFileName;
    StringBuilder outputStringBuilder;
    String exactFileNameWithoutExtension;
    String currentfunctionName;
    String currentFileName;
    private static Integer labelCounter = 0;
    private static Integer returnLabelCounter = 0;

    public CodeWriter(String outputFileName)
    {
        try
        {
            this.outputFileName = outputFileName;
            printWriter = new PrintWriter(new FileWriter(outputFileName));
            outputStringBuilder = new StringBuilder("");
            setExactFileNameWithoutExtension();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setCurrentFileName(String fileName)
    {
        currentFileName = fileName;
    }

    public void writeArithmetic(String command)
    {
        switch (command)
        {
            case Constants.ARITHMETIC_ADD:
                binaryArithmeticOperations("+", false, "");
                break;
            case Constants.ARITHMETIC_SUB:
                binaryArithmeticOperations("-", false, "");
                break;
            case Constants.ARITHMETIC_AND:
                binaryArithmeticOperations("&", false, "");
                break;
            case Constants.ARITHMETIC_OR:
                binaryArithmeticOperations("|", false, "");
                break;
            case Constants.ARITHMETIC_GT:
                binaryArithmeticOperations("-", true, "JGT");
                break;
            case Constants.ARITHMETIC_LT:
                binaryArithmeticOperations("-", true, "JLT");
                break;
            case Constants.ARITHMETIC_EQ:
                binaryArithmeticOperations("-", true, "JEQ");
                break;
            case Constants.ARITHMETIC_NEG:
                UnaryArithmeticOperations("-");
                break;
            case Constants.ARITHMETIC_NOT:
                UnaryArithmeticOperations("!");
                break;
        }
        pushResult();
        writeToFile();
    }

    private void binaryArithmeticOperations(String operation, Boolean isLogical, String logicalOperation)
    {
        popTopTwoValues();
        outputStringBuilder.append(" D=M" + operation + "D  //D=val1 " + operation + "val2 \n");

        if (isLogical)
        {
            outputStringBuilder.append(" @SET_TRUE_" + labelCounter + " \n");
            outputStringBuilder.append(" D;" + logicalOperation + " \n");
            outputStringBuilder.append(" @SET_FALSE_" + labelCounter + " \n");
            outputStringBuilder.append(" 0;JMP \n");
            outputStringBuilder.append(" (SET_TRUE_" + labelCounter + ") \n");
            outputStringBuilder.append(" D=-1 \n");  //true = -1, false = 0
            outputStringBuilder.append(" @AFTER_FALSE_" + labelCounter + " \n");
            outputStringBuilder.append(" 0;JMP \n");
            outputStringBuilder.append(" (SET_FALSE_" + labelCounter + ") \n");
            outputStringBuilder.append(" D=0 \n");
            outputStringBuilder.append(" (AFTER_FALSE_" + labelCounter + ") \n");
            labelCounter++;
        }

    }

    private void UnaryArithmeticOperations(String operation)
    {
        popTopValue();
        outputStringBuilder.append(" D=" + operation + "M   //D=" + operation + "val1 \n");
    }

    private void popTopTwoValues()
    {
        outputStringBuilder.append(" @SP    //POP val2 \n");
        outputStringBuilder.append(" M=M-1 \n");
        outputStringBuilder.append(" @SP \n");
        outputStringBuilder.append(" A=M \n");
        outputStringBuilder.append(" D=M \n");
        outputStringBuilder.append(" @SP    //POP val1 \n");
        outputStringBuilder.append(" M=M-1 \n");
        outputStringBuilder.append(" @SP \n");
        outputStringBuilder.append(" A=M \n");
    }

    private void pushResult()
    {
        outputStringBuilder.append(" @SP    //Push value \n");
        outputStringBuilder.append(" A=M \n");
        outputStringBuilder.append(" M=D \n");
        outputStringBuilder.append(" @SP    //SP++\n");
        outputStringBuilder.append(" M=M+1 \n");
    }

    private void popTopValue()
    {
        outputStringBuilder.append(" @SP    //POP val2 \n");
        outputStringBuilder.append(" M=M-1 \n");
        outputStringBuilder.append(" @SP \n");
        outputStringBuilder.append(" A=M \n");
    }


    public void writePushPop(Integer stackOperation, String segment, Integer index)
    {
        if (stackOperation == Constants.C_PUSH)
        {
            pushSegment(segment, index);
        } else if (stackOperation == Constants.C_POP)
        {
            popSegment(segment, index);
        }
        writeToFile();
    }

    private void pushSegment(String segment, Integer index)
    {
        switch (segment)
        {
            case Constants.LOCAL_SEGMENT:
                writePushFiveMethodSegments("LCL", index);
                break;
            case Constants.ARGUMENT_SEGMENT:
                writePushFiveMethodSegments("ARG", index);
                break;
            case Constants.THIS_SEGMENT:
                writePushFiveMethodSegments("THIS", index);
                break;
            case Constants.THAT_SEGMENT:
                writePushFiveMethodSegments("THAT", index);
                break;
            case Constants.TEMP_SEGMENT:
                writePushFiveMethodSegments("5", index);
                break;
            case Constants.CONSTANT_SEGMENT:
                writeConstant(index);
                break;
            case Constants.STATIC_SEGMENT:
                writePushStatic(index);
                break;
            case Constants.POINTER_SEGMENT:
                writePushPopPointer(0, index);
                break;
        }
    }

    private void popSegment(String segment, Integer index)
    {
        switch (segment)
        {
            case Constants.LOCAL_SEGMENT:
                writePopFiveMethodSegments("LCL", index);
                break;
            case Constants.ARGUMENT_SEGMENT:
                writePopFiveMethodSegments("ARG", index);
                break;
            case Constants.THIS_SEGMENT:
                writePopFiveMethodSegments("THIS", index);
                break;
            case Constants.THAT_SEGMENT:
                writePopFiveMethodSegments("THAT", index);
                break;
            case Constants.TEMP_SEGMENT:
                writePopFiveMethodSegments("5", index);
                break;
            case Constants.STATIC_SEGMENT:
                writePopStatic(index);
                break;
            case Constants.POINTER_SEGMENT:
                writePushPopPointer(1, index);
                break;
        }
    }

    private void writePushFiveMethodSegments(String segment, Integer index)
    {
        //LCL OR THIS OR ARG OR THAT OR TEMP
        outputStringBuilder.append(" @" + index + "\n");
        outputStringBuilder.append(" D=A\n");
        outputStringBuilder.append(" @" + segment + "\n");
        if (segment.equals("5"))
            outputStringBuilder.append(" A=D+A  //D = segment + index\n");
        else
            outputStringBuilder.append(" A=D+M  //D = segment + index\n");
        outputStringBuilder.append(" D=M    //D = *addr\n");
        pushResult();
    }

    private void writePopFiveMethodSegments(String segment, Integer index)
    {
        //LCL OR THIS OR ARG OR THAT OR TEMP
        outputStringBuilder.append(" @SP    //SP-- \n");
        outputStringBuilder.append(" M=M-1 \n");
        outputStringBuilder.append(" @" + index + "\n");
        outputStringBuilder.append(" D=A\n");
        outputStringBuilder.append(" @" + segment + "\n");
        if (segment.equals("5"))
            outputStringBuilder.append(" A=D+A  //D = segment + index\n");
        else
            outputStringBuilder.append(" A=D+M  //D = segment + index\n");
        outputStringBuilder.append(" D=A  \n");
        outputStringBuilder.append(" @R13 \n");
        outputStringBuilder.append(" M=D    //Ram[13] = segment + index\n");
        outputStringBuilder.append(" @SP \n");
        outputStringBuilder.append(" A=M \n");
        outputStringBuilder.append(" D=M    //D=*SP\n");
        outputStringBuilder.append(" @R13 \n");
        outputStringBuilder.append(" A=M\n");
        outputStringBuilder.append(" M=D    //*addr = *sp\n");

    }

    private void writeConstant(Integer index)
    {
        outputStringBuilder.append(" @" + index + "\n");
        outputStringBuilder.append(" D=A\n");
        outputStringBuilder.append(" @SP\n");
        outputStringBuilder.append(" A=M\n");
        outputStringBuilder.append(" M=D    //*SP = *i\n");
        outputStringBuilder.append(" @SP \n");
        outputStringBuilder.append(" M=M+1  //SP++\n");
    }

    private void writePushStatic(Integer index)
    {
        outputStringBuilder.append(" @" + currentFileName + "." + index + " \n");
        outputStringBuilder.append(" D=M\n");
        outputStringBuilder.append(" @SP\n");
        outputStringBuilder.append(" A=M\n");
        outputStringBuilder.append(" M=D    //*SP = static.index\n");
        outputStringBuilder.append(" @SP \n");
        outputStringBuilder.append(" M=M+1  //SP++\n");
    }

    private void writePopStatic(Integer index)
    {
        outputStringBuilder.append(" @SP    //POP val \n");
        outputStringBuilder.append(" M=M-1 \n");
        outputStringBuilder.append(" @SP \n");
        outputStringBuilder.append(" A=M \n");
        outputStringBuilder.append(" D=M    //D=*SP\n");
        outputStringBuilder.append(" @" + currentFileName + "." + index + " \n");
        outputStringBuilder.append(" M=D    //static.i = *sp\n");
    }

    private void writePushPopPointer(Integer operation, Integer index)
    {
        if (operation == 0)
        {
            //push
            if (index == 0)
            {
                pushPointer("THIS");
            } else
            {
                pushPointer("THAT");
            }
        } else
        {
            //pop
            if (index == 0)
            {
                popPointer("THIS");
            } else
            {
                popPointer("THAT");
            }
        }
    }

    private void writePushPointer(Integer index)
    {
        if (index == 0)
        {
            pushPointer("THIS");
        } else
        {
            pushPointer("THAT");
        }
    }

    private void pushPointer(String thisThat)
    {
        outputStringBuilder.append(" @" + thisThat + " \n");
        outputStringBuilder.append(" D=M\n");
        outputStringBuilder.append(" @SP\n");
        outputStringBuilder.append(" A=M\n");
        outputStringBuilder.append(" M=D    //*SP = THIS\n");
        outputStringBuilder.append(" @SP \n");
        outputStringBuilder.append(" M=M+1  //SP++\n");
    }

    private void popPointer(String thisThat)
    {
        outputStringBuilder.append(" @SP \n");
        outputStringBuilder.append(" M=M-1  //SP--\n");
        outputStringBuilder.append(" @SP\n");
        outputStringBuilder.append(" A=M\n");
        outputStringBuilder.append(" D=M    //D=*SP\n");
        outputStringBuilder.append(" @" + thisThat + " \n");
        outputStringBuilder.append(" M=D    //thisThat = *sp\n");
    }

    public void writeLabel(String label)
    {
        outputStringBuilder.append(" (" + exactFileNameWithoutExtension + currentfunctionName + "$" + label + ") \n");
        writeToFile();
    }

    public void writeGoto(String label)
    {
        outputStringBuilder.append(" @" + exactFileNameWithoutExtension + currentfunctionName + "$" + label + "\n");
        outputStringBuilder.append(" 0;JMP \n");
        writeToFile();
    }

    public void writeIf(String label)
    {
        outputStringBuilder.append(" @SP\n");
        outputStringBuilder.append(" M=M-1\n");
        outputStringBuilder.append(" @SP\n");
        outputStringBuilder.append(" A=M\n");
        outputStringBuilder.append(" D=M\n");
        outputStringBuilder.append(" @" + exactFileNameWithoutExtension + currentfunctionName + "$" + label + "\n");
        outputStringBuilder.append(" D;JNE \n");
        writeToFile();

    }

    public void writeFunction(String functionName, int numVars)
    {
        currentfunctionName = functionName;
        returnLabelCounter = 0;
        outputStringBuilder.append(" (" + exactFileNameWithoutExtension + functionName + ") \n");
        generateLocalnVars(numVars);
        writeToFile();
    }

    private void generateLocalnVars(int numVars)
    {
        for (int i = 1; i <= numVars; ++i)
        {
            outputStringBuilder.append(" @SP    //LOCAL VAR_+" + i + " = 0\n");
            outputStringBuilder.append(" A=M\n");
            outputStringBuilder.append(" M=0\n");
            outputStringBuilder.append(" @SP    //SP++\n");
            outputStringBuilder.append(" M=M+1\n");
        }
    }

    public void writeCall(String functionName, int numArgs)
    {
        outputStringBuilder.append(" @" + exactFileNameWithoutExtension + currentfunctionName
                + "$ret." + returnLabelCounter + "  //PUSH RETURN_ADDRESS\n");
        outputStringBuilder.append(" D=A \n");
        outputStringBuilder.append(" @SP \n");
        outputStringBuilder.append(" A=M \n");
        outputStringBuilder.append(" M=D \n");
        outputStringBuilder.append(" @SP \n");
        outputStringBuilder.append(" M=M+1 \n");

        outputStringBuilder.append(" @LCL  //PUSH LCL\n");
        outputStringBuilder.append(" D=M \n");
        outputStringBuilder.append(" @SP \n");
        outputStringBuilder.append(" A=M \n");
        outputStringBuilder.append(" M=D \n");
        outputStringBuilder.append(" @SP \n");
        outputStringBuilder.append(" M=M+1 \n");

        outputStringBuilder.append(" @ARG  //PUSH ARG\n");
        outputStringBuilder.append(" D=M \n");
        outputStringBuilder.append(" @SP \n");
        outputStringBuilder.append(" A=M \n");
        outputStringBuilder.append(" M=D \n");
        outputStringBuilder.append(" @SP \n");
        outputStringBuilder.append(" M=M+1 \n");

        outputStringBuilder.append(" @THIS  //PUSH THIS\n");
        outputStringBuilder.append(" D=M \n");
        outputStringBuilder.append(" @SP \n");
        outputStringBuilder.append(" A=M \n");
        outputStringBuilder.append(" M=D \n");
        outputStringBuilder.append(" @SP \n");
        outputStringBuilder.append(" M=M+1 \n");

        outputStringBuilder.append(" @THAT  //PUSH THAT\n");
        outputStringBuilder.append(" D=M \n");
        outputStringBuilder.append(" @SP \n");
        outputStringBuilder.append(" A=M \n");
        outputStringBuilder.append(" M=D \n");
        outputStringBuilder.append(" @SP \n");
        outputStringBuilder.append(" M=M+1 \n");

        outputStringBuilder.append(" @SP    //ARG = SP-5-numArgs\n");
        outputStringBuilder.append(" D=M \n");
        outputStringBuilder.append(" @5 \n");
        outputStringBuilder.append(" D=D-A \n");
        outputStringBuilder.append(" @" + numArgs + " \n");
        outputStringBuilder.append(" D=D-A \n");
        outputStringBuilder.append(" @ARG \n");
        outputStringBuilder.append(" M=D \n");

        outputStringBuilder.append(" @SP    //LCL=SP\n");
        outputStringBuilder.append(" D=M \n");
        outputStringBuilder.append(" @LCL \n");
        outputStringBuilder.append(" M=D \n");

        outputStringBuilder.append(" @" + exactFileNameWithoutExtension + functionName + "  //GOTO FUNCTION_NAME\n");
        outputStringBuilder.append("0;JMP \n");

        outputStringBuilder.append(" (" + exactFileNameWithoutExtension + currentfunctionName + "$ret."
                + returnLabelCounter + ")   //(RETURN ADDRESS)\n");
        returnLabelCounter++;
        writeToFile();
    }

    public void writeReturn()
    {
        outputStringBuilder.append(" @LCL   //END_FRAME(R13) = LCL\n");
        outputStringBuilder.append(" D=M \n");
        outputStringBuilder.append(" @R13 \n");
        outputStringBuilder.append(" M=D \n");

        outputStringBuilder.append(" @R13   //RETADDR(R14) = *(END_FRAME(R13)-5) \n");
        outputStringBuilder.append(" D=M \n");
        outputStringBuilder.append(" @5 \n");
        outputStringBuilder.append(" A=D-A \n");
        outputStringBuilder.append(" D=M \n");
        outputStringBuilder.append(" @R14 \n");
        outputStringBuilder.append(" M=D\n");

        outputStringBuilder.append(" @SP    //*ARG = POP()\n");
        outputStringBuilder.append(" M=M-1 \n");
        outputStringBuilder.append(" @SP \n");
        outputStringBuilder.append(" A=M \n");
        outputStringBuilder.append(" D=M \n");
        outputStringBuilder.append(" @ARG \n");
        outputStringBuilder.append(" A=M \n");
        outputStringBuilder.append(" M=D \n");

        outputStringBuilder.append(" @ARG   //SP = ARG + 1 \n");
        outputStringBuilder.append(" D=M \n");
        outputStringBuilder.append(" @1 \n");
        outputStringBuilder.append(" D=D+A \n");
        outputStringBuilder.append(" @SP \n");
        outputStringBuilder.append(" M=D \n");

        outputStringBuilder.append(" @R13   //THAT = *(END_FRAME(R13)-1) \n");
        outputStringBuilder.append(" D=M \n");
        outputStringBuilder.append(" @1 \n");
        outputStringBuilder.append(" A=D-A \n");
        outputStringBuilder.append(" D=M \n");
        outputStringBuilder.append(" @THAT \n");
        outputStringBuilder.append(" M=D \n");

        outputStringBuilder.append(" @R13   //THIS = *(END_FRAME(R13)-2) \n");
        outputStringBuilder.append(" D=M \n");
        outputStringBuilder.append(" @2 \n");
        outputStringBuilder.append(" A=D-A \n");
        outputStringBuilder.append(" D=M \n");
        outputStringBuilder.append(" @THIS \n");
        outputStringBuilder.append(" M=D \n");

        outputStringBuilder.append(" @R13   //ARG = *(END_FRAME(R13)-3) \n");
        outputStringBuilder.append(" D=M \n");
        outputStringBuilder.append(" @3 \n");
        outputStringBuilder.append(" A=D-A \n");
        outputStringBuilder.append(" D=M \n");
        outputStringBuilder.append(" @ARG \n");
        outputStringBuilder.append(" M=D \n");

        outputStringBuilder.append(" @R13   //LCL = *(END_FRAME(R13)-4) \n");
        outputStringBuilder.append(" D=M \n");
        outputStringBuilder.append(" @4 \n");
        outputStringBuilder.append(" A=D-A \n");
        outputStringBuilder.append(" D=M \n");
        outputStringBuilder.append(" @LCL \n");
        outputStringBuilder.append(" M=D \n");

        outputStringBuilder.append(" @R14   //GOTO RETADDR \n");
        outputStringBuilder.append(" A=M \n");
        outputStringBuilder.append(" 0;JMP \n");
        writeToFile();
    }

    public void writeInit()
    {
        outputStringBuilder.append(" @256 //SP = 256\n");
        outputStringBuilder.append(" D=A \n");
        outputStringBuilder.append(" @SP \n");
        outputStringBuilder.append(" M=D \n");
        writeToFile();
        writeCall("Sys.init", 0);
    }

    public void close()
    {
        printWriter.close();
    }

    private void writeToFile()
    {
        printWriter.write(outputStringBuilder.toString() + "\n\n");
        outputStringBuilder.setLength(0);
    }

    public void writeString(String input)
    {
        outputStringBuilder.append(input);
    }

    private void setExactFileNameWithoutExtension()
    {
        // /c/d/f1.txt (exactFileNameWithoutExtension = f1. )
        int tempIndex;
        if ((tempIndex = outputFileName.lastIndexOf("/")) != -1)
        {
            exactFileNameWithoutExtension = outputFileName.substring(tempIndex + 1, outputFileName.indexOf(".") + 1);
        } else
        {
            exactFileNameWithoutExtension = outputFileName.substring(0, outputFileName.indexOf(".") + 1);
        }
    }
}
