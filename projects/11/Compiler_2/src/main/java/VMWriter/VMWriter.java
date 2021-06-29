package VMWriter;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static VMWriter.Constants.*;

public class VMWriter
{
    PrintWriter printWriter;
    String outputFileName;
    StringBuilder outputStringBuilder;
    int labelCount;

    public VMWriter(String outputFileName)
    {
        this.outputFileName = outputFileName;
        try
        {
            printWriter = new PrintWriter(new FileWriter(outputFileName));
            outputStringBuilder = new StringBuilder();
            labelCount = 0;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void writePush(String segment, int index)
    {
        if (segment.equals(FIELD_VAR))
            segment = THIS;
        outputStringBuilder.append(PUSH_OPERATION).append(SPACE).append(segment).append(SPACE).append(index).append(NEWLINE);
    }

    public void writePop(String segment, int index)
    {
        if (segment.equals(FIELD_VAR))
            segment = THIS;
        outputStringBuilder.append(POP_OPERATION).append(SPACE).append(segment).append(SPACE).append(index).append(NEWLINE);
    }

    public void writeArithmetics(String command)
    {
        switch (command)
        {
            case ADD_SYMBOL:
                outputStringBuilder.append(ADD_OP).append(NEWLINE);
                break;
            case SUB_SYMBOL:
                outputStringBuilder.append(SUB_OP).append(NEWLINE);
                break;
            case OR_SYMBOL:
                outputStringBuilder.append(OR_OP).append(NEWLINE);
                break;
            case AND_SYMBOL:
                outputStringBuilder.append(AND_OP).append(NEWLINE);
                break;
            case EQ_SYMBOL:
                outputStringBuilder.append(EQ_OP).append(NEWLINE);
                break;
            case LT_SYMBOL:
                outputStringBuilder.append(LT_OP).append(NEWLINE);
                break;
            case GT_SYMBOL:
                outputStringBuilder.append(GT_OP).append(NEWLINE);
                break;
            case DIV_SYMBOL:
                writeCall("Math.divide", 2);
                break;
            case MUL_SYMBOL:
                writeCall("Math.multiply", 2);
                break;
            case NOT_SYMBOL:
                outputStringBuilder.append(NOT_OP).append(NEWLINE);
                break;
            case NEG_SYMBOL:
                outputStringBuilder.append(NEG_OP).append(NEWLINE);
                break;
        }
    }

    public void writeLabel(String label)
    {
        outputStringBuilder.append(LABEL_OPERATION).append(SPACE).append(label).append(NEWLINE);
    }

    public void writeGoto(String label)
    {
        outputStringBuilder.append(GOTO_OPERATION).append(SPACE).append(label).append(NEWLINE);
    }

    public void writeIf(String label)
    {
        outputStringBuilder.append(IF_GOTO_OPERATION).append(SPACE).append(label).append(NEWLINE);
    }

    public void writeCall(String name, int nArgs)
    {
        outputStringBuilder.append(FUNCTION_CALL).append(SPACE).append(name).append(SPACE).append(nArgs).append(NEWLINE);
    }

    public void writeFunction(String name, int nLocals)
    {
        outputStringBuilder.append(FUNCTION_DECLARATION).append(SPACE).append(name).append(SPACE).append(nLocals).append(NEWLINE);
    }

    public void writeReturn()
    {
        outputStringBuilder.append(FUNCTION_RETURN).append(NEWLINE);
    }

    public void writeToFile()
    {
        printWriter.write(outputStringBuilder.toString());
        outputStringBuilder.setLength(0);
    }


    public void close()
    {
        printWriter.close();
    }

}
