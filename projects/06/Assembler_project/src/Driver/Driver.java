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
        translateToAssembly(parser);
    }

    private static void translateToAssembly(Parser parser)
    {
        parser.setPassCount(Constants.FirstPass);
        parser.advance();
        parser.setPassCount(Constants.SecondPass);
        parser.advance();
        parser.close();
    }

    private static StringBuilder getOutputFileName(String inputFileName)
    {
        StringBuilder outputFileName;
        int dotPos = inputFileName.indexOf(".");
        outputFileName = new StringBuilder(inputFileName.substring(0, dotPos + 1));
        outputFileName.append("hack");
        return outputFileName;
    }
}
