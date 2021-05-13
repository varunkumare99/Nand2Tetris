package Driver;

import CodeWriter.CodeWriter;
import Parser.Parser;

public class Driver
{
    public static void main(String[] args)
    {
        if (args.length == 0 || args.length > 1)
        {
            System.err.println("Error: input should contain only a single fileName");
            System.exit(0);
        }

        String inputFileName = args[0];
        StringBuilder outputFileName = getOutputFileName(inputFileName);
        Parser parser = new Parser(inputFileName);
        CodeWriter codeWriter = new CodeWriter(outputFileName.toString());
        vmTranslate(parser,codeWriter);
    }

    private static void vmTranslate(Parser parser, CodeWriter codeWriter)
    {
        while (parser.hasMoreCommands())
        {
            parser.advance();
            Integer commandType = parser.commandType();
            handleCommand(parser, codeWriter, commandType);
        }
        parser.close();
        codeWriter.close();
    }

    private static StringBuilder getOutputFileName(String inputFileName)
    {
        StringBuilder outputFileName;
        int dotPos = inputFileName.indexOf(".");
        outputFileName = new StringBuilder(inputFileName.substring(0, dotPos + 1));
        outputFileName.append("asm");
        return outputFileName;
    }

    private static void handleCommand(Parser parser, CodeWriter codeWriter, Integer commandType)
    {
        String arg1;
        String arg2;
        codeWriter.writeString("//" + parser.getCurrentCommand() + "\n");
        switch (commandType)
        {
            case Constants.C_PUSH:
            {
                arg1 = parser.arg1();
                arg2 = parser.arg2();
                codeWriter.writePushPop(Constants.C_PUSH, arg1, Integer.parseInt(arg2));
                break;
            }
            case Constants.C_POP:
            {
                arg1 = parser.arg1();
                arg2 = parser.arg2();
                codeWriter.writePushPop(Constants.C_POP, arg1, Integer.parseInt(arg2));
                break;
            }
            case Constants.C_ARITHMETIC:
            {
                arg1 = parser.arg1();
                codeWriter.writeArithmetic(arg1);
                break;
            }

        }
    }
}
