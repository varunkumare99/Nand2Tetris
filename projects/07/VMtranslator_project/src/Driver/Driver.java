package Driver;

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
        Parser parser = new Parser(inputFileName, outputFileName.toString());
        vmTranslate(parser);
    }

    private static void vmTranslate(Parser parser)
    {
        parser.advance();
        parser.close();
    }

    private static StringBuilder getOutputFileName(String inputFileName)
    {
        StringBuilder outputFileName;
        int dotPos = inputFileName.indexOf(".");
        outputFileName = new StringBuilder(inputFileName.substring(0, dotPos + 1));
        outputFileName.append("asm");
        return outputFileName;
    }
}
