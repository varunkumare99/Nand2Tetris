package JackAnalyzer;

import CompilationEngine.CompilationEngine;
import static JackTokenizer.Constants.*;
import JackTokenizer.JackTokenizer;

import java.io.File;
import java.io.FilenameFilter;


public class JackAnalyzer
{
    public static void main(String[] args)
    {
        if (args.length != 1)
        {
            System.err.println("Error: input should contain only a single fileName");
            System.exit(0);
        }

        String directoryName = args[0];
        handleDirectory(directoryName);
    }


    private static String getOutputFileName(String inputFileName)
    {
        StringBuilder outputFileName;
        int dotPos = inputFileName.indexOf(".");
        outputFileName = new StringBuilder(inputFileName.substring(0, dotPos));
        outputFileName.append(".vm");
        return outputFileName.toString();
    }

    private static void handleDirectory(String inputDirectoryName)
    {
        String inputFileName, outputFileName;
        JackTokenizer jackTokenizer;
        CompilationEngine compilationEngine;
        FilenameFilter filter = getFilenameFilter();
        File[] files = new File(inputDirectoryName).listFiles(filter);
        assert files != null;
        for (File file : files)
        {

            inputFileName = file.getAbsolutePath();
            outputFileName = getOutputFileName(inputFileName);
            jackTokenizer = new JackTokenizer(inputFileName, outputFileName);
            tokenizeFile(jackTokenizer);
            compilationEngine = new CompilationEngine(inputFileName, outputFileName, jackTokenizer.getJackTokens());
            compilationEngine.compileClass();
            compilationEngine.writeToFile();
            jackTokenizer.close();
            compilationEngine.close();

        }
    }

    private static FilenameFilter getFilenameFilter()
    {
        FilenameFilter filter = new FilenameFilter()
        {
            @Override
            public boolean accept(File f, String name)
            {
                return name.endsWith(".jack");
            }
        };
        return filter;
    }

    private static void tokenizeFile(JackTokenizer jackTokenizer)
    {
        jackTokenizer.writeString(TOKENS_START_TAG+NEWLINE);
        while (jackTokenizer.hasMoreCommands())
        {
            jackTokenizer.advance();
            int tokenType = jackTokenizer.tokenType();
            handleToken(jackTokenizer, tokenType);
        }
        jackTokenizer.writeString(TOKENS_END_TAG);
    }

    private static void handleToken(JackTokenizer jackTokenizer, int tokenType)
    {
        TOKENS tokens = TOKENS.values()[tokenType];
        switch (tokens)
        {
            case TOKEN_KEYWORD:
            {
                jackTokenizer.buildOutputString(KEYWORD_TAG, jackTokenizer.keyword());
                break;
            }
            case TOKEN_SYMBOL:
            {
                String stringSymbol = getStringSymbol(jackTokenizer);
                jackTokenizer.buildOutputString(SYMBOL_TAG, stringSymbol + EMPTY_STRING);
                break;
            }
            case TOKEN_IDENTIFIER:
            {
                jackTokenizer.buildOutputString(IDENTIFIER_TAG, jackTokenizer.identifier());
                break;
            }
            case TOKEN_INT_CONST:
            {
                jackTokenizer.buildOutputString(INTEGER_CONSTANT_TAG, jackTokenizer.intVal() + EMPTY_STRING);
                break;
            }
            case TOKEN_STRING_CONST:
            {
                jackTokenizer.buildOutputString(STRING_CONSTANT_TAG, jackTokenizer.stringVal());
                break;
            }
            default:
            {
                break;
            }
        }
    }

    private static String getStringSymbol(JackTokenizer jackTokenizer)
    {
        String stringSymbol;
        char symbol = jackTokenizer.symbol();
        stringSymbol = getXMLSymbol(symbol);
        return stringSymbol;
    }

    private static String getXMLSymbol(char symbol)
    {
        String stringSymbol;
        if (symbol == SYMBOL_LESS_THAN)
            stringSymbol = XML_LESS_THAN;
        else if (symbol == SYMBOL_GREATER_THAN)
            stringSymbol = XML_GREATER_THAN;
        else if (symbol == SYMBOL_AND)
            stringSymbol = XML_AND;
        else
            stringSymbol = symbol + EMPTY_STRING;
        return stringSymbol;
    }
}
