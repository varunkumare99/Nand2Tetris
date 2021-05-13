package Driver;

import CodeWriter.CodeWriter;
import Parser.Parser;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        initiateProcessing(inputFileName);
    }

    private static void initiateProcessing(String inputFileName)
    {
        Path path = Paths.get(inputFileName);
        Boolean isDirectory = Files.isDirectory(path);
        String outputFileName = getOutputFileName(path.toAbsolutePath().toString(), isDirectory).toString();
        vmTranslate(inputFileName, outputFileName, isDirectory);
    }

    private static void vmTranslate(String inputFileName, String outputFileName, Boolean isDirectory)
    {
        if (isDirectory)
        {
            handleDirectory(inputFileName, outputFileName);
        } else
        {
            handleSingleFile(inputFileName, outputFileName);
        }
    }

    private static void handleSingleFile(String inputFileName, String outputFileName)
    {
        CodeWriter codeWriter = new CodeWriter(outputFileName);
        Parser parser = new Parser(inputFileName);
        vmTranslateSingleFile(parser, codeWriter);
        parser.close();
        codeWriter.close();
    }

    private static void handleDirectory(String inputFileName, String outputFileName)
    {
        String fileName;
        CodeWriter codeWriter = new CodeWriter(outputFileName);
        codeWriter.writeInit();
        FilenameFilter filter = getFilenameFilter();
        File[] files = new File(inputFileName).listFiles(filter);
        for (File file : files)
        {

            fileName = file.getAbsolutePath();
            Parser parser = new Parser(fileName);
            String currentFileName = file.getName().substring(0, file.getName().indexOf("."));
            codeWriter.setCurrentFileName(currentFileName);
            vmTranslateSingleFile(parser, codeWriter);
            parser.close();
        }
        codeWriter.close();
    }

    private static FilenameFilter getFilenameFilter()
    {
        FilenameFilter filter = new FilenameFilter()
        {
            @Override
            public boolean accept(File f, String name)
            {
                return name.endsWith(".vm");
            }
        };
        return filter;
    }

    private static void vmTranslateSingleFile(Parser parser, CodeWriter codeWriter)
    {
        while (parser.hasMoreCommands())
        {
            parser.advance();
            Integer commandType = parser.commandType();
            handleCommand(parser, codeWriter, commandType);
        }
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
            case Constants.C_LABEL:
            {
                arg1 = parser.arg1();
                codeWriter.writeLabel(arg1);
                break;
            }
            case Constants.C_GOTO:
            {
                arg1 = parser.arg1();
                codeWriter.writeGoto(arg1);
                break;
            }
            case Constants.C_IF:
            {
                arg1 = parser.arg1();
                codeWriter.writeIf(arg1);
                break;
            }
            case Constants.C_FUNCTION:
            {
                arg1 = parser.arg1();
                arg2 = parser.arg2();
                codeWriter.writeFunction(arg1, Integer.parseInt(arg2));
                break;
            }
            case Constants.C_CALL:
            {
                arg1 = parser.arg1();
                arg2 = parser.arg2();
                codeWriter.writeCall(arg1, Integer.parseInt(arg2));
                break;
            }
            case Constants.C_RETURN:
            {
                codeWriter.writeReturn();
                break;
            }
        }
    }

    private static StringBuilder getOutputFileName(String inputFileName, Boolean isDirectory)
    {
        StringBuilder outputFileName;
        if (!isDirectory)
        {
            int dotPos = inputFileName.indexOf(".");
            outputFileName = new StringBuilder(inputFileName.substring(0, dotPos + 1));
            outputFileName.append("asm");
            return outputFileName;
        } else
        {
            int slashPos = inputFileName.lastIndexOf("/");
            outputFileName = new StringBuilder(inputFileName);
            outputFileName.append("/" + inputFileName.substring(slashPos + 1, inputFileName.length()));
            outputFileName.append(".asm");
            return outputFileName;
        }
    }
}
