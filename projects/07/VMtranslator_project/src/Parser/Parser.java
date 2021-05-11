package Parser;

import CodeWriter.CodeWriter;
import Driver.Constants;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Parser
{
    BufferedReader bufferedReader;
    String inputFileName;
    String currentCommand;
    String[] tokens;
    CodeWriter codeWriter;

    public Parser(String inputFileName, String outputFileName)
    {
        try
        {
            this.inputFileName = inputFileName;
            bufferedReader = new BufferedReader(new FileReader(this.inputFileName));
            codeWriter = new CodeWriter(outputFileName);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void advance()
    {
        while (hasMoreCommands())
        {
            trimCommand();
            if (checkForCommentsWhiteSpaces())
            {
                continue;
            } else
            {
                removeTrailingComment();
                trimCommand();
                Integer commandType = commandType();
                handleCommand(commandType);
            }
        }
    }

    private void handleCommand(Integer commandType)
    {
        String arg1;
        String arg2;
        splitCommand();
        codeWriter.writeString("//"+currentCommand+"\n");
        switch (commandType)
        {
            case Constants.C_PUSH:
            {
                arg1 = arg1();
                arg2 = arg2();
                codeWriter.writePushPop(Constants.C_PUSH, arg1, Integer.parseInt(arg2));
                break;
            }
            case Constants.C_POP:
            {
                arg1 = arg1();
                arg2 = arg2();
                codeWriter.writePushPop(Constants.C_POP, arg1, Integer.parseInt(arg2));
                break;
            }
            case Constants.C_ARITHMETIC:
            {
                arg1 = arg1();
                codeWriter.writeArithmetic(arg1);
                break;
            }
        }
    }

    private Boolean hasMoreCommands()
    {
        try
        {
            if ((currentCommand = bufferedReader.readLine()) != null)
            {
                return true;
            }
            return false;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    private Integer commandType()
    {
        if (currentCommand.startsWith(Constants.STACK_PUSH))
            return Constants.C_PUSH;
        else if (currentCommand.startsWith(Constants.STACK_POP))
            return Constants.C_POP;
        else if (currentCommand.startsWith(Constants.STACK_POP))
            return Constants.C_POP;
        else if (checkIfArthmetic())
            return Constants.C_ARITHMETIC;
        else
            return -1;
    }

    private String arg1()
    {
        if (tokens.length == 1)
            return tokens[0];
        else
            return tokens[1];
    }

    private String arg2()
    {
        return tokens[2];
    }

    private void splitCommand()
    {
        tokens = currentCommand.split(" ");
    }

    private Boolean checkIfArthmetic()
    {
        if (currentCommand.startsWith(Constants.ARITHMETIC_ADD)
                || currentCommand.startsWith(Constants.ARITHMETIC_SUB)
                || currentCommand.startsWith(Constants.ARITHMETIC_AND)
                || currentCommand.startsWith(Constants.ARITHMETIC_OR)
                || currentCommand.startsWith(Constants.ARITHMETIC_EQ)
                || currentCommand.startsWith(Constants.ARITHMETIC_LT)
                || currentCommand.startsWith(Constants.ARITHMETIC_NEG)
                || currentCommand.startsWith(Constants.ARITHMETIC_NOT)
                || currentCommand.startsWith(Constants.ARITHMETIC_GT))
            return true;
        return false;
    }

    private Boolean checkForCommentsWhiteSpaces()
    {
        if (currentCommand.startsWith(Constants.Comment) || currentCommand.startsWith(Constants.WhiteSpace) || currentCommand.isEmpty())
        {
            return true;
        }
        return false;
    }

    private void removeTrailingComment()
    {
        if (currentCommand.contains(Constants.Comment))
        {
            int commentPos = currentCommand.indexOf(Constants.Comment);
            currentCommand = currentCommand.substring(0, commentPos);
        }
    }

    private void trimCommand()
    {
        currentCommand = currentCommand.trim();
    }

    public void close()
    {
        try
        {
            bufferedReader.close();
            codeWriter.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
