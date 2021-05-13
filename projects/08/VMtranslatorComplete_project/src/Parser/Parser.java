package Parser;

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
    String nextCommand;
    String[] tokens;

    public Parser(String inputFileName)
    {
        try
        {
            this.inputFileName = inputFileName;
            bufferedReader = new BufferedReader(new FileReader(this.inputFileName));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }


    public String getCurrentCommand()
    {
        return currentCommand;
    }

    public void advance()
    {
        currentCommand = nextCommand;
        removeCommentSpaces();
        splitCommand();
    }

    private void removeCommentSpaces()
    {
        trimCommand();
        removeTrailingComment();
        trimCommand();
    }

    public Boolean hasMoreCommands()
    {
        try
        {
            while ((nextCommand = bufferedReader.readLine()) != null)
            {
                if (!checkForCommentsWhiteSpaces())
                    return true;
            }
            return false;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public Integer commandType()
    {
        if (currentCommand.startsWith(Constants.STACK_PUSH))
            return Constants.C_PUSH;
        else if (currentCommand.startsWith(Constants.STACK_POP))
            return Constants.C_POP;
        else if (currentCommand.startsWith(Constants.STACK_POP))
            return Constants.C_POP;
        else if (checkIfArthmetic())
            return Constants.C_ARITHMETIC;
        else if (currentCommand.startsWith(Constants.BRANCH_LABEL))
            return Constants.C_LABEL;
        else if (currentCommand.startsWith(Constants.BRANCH_GOTO))
            return Constants.C_GOTO;
        else if (currentCommand.startsWith(Constants.BRANCH_IF_GOTO))
            return Constants.C_IF;
        else if (currentCommand.startsWith(Constants.FUNCTION_DEFINITION))
            return Constants.C_FUNCTION;
        else if (currentCommand.startsWith(Constants.FUNCTION_CALL))
            return Constants.C_CALL;
        else if (currentCommand.startsWith(Constants.FUNCTION_RETURN))
            return Constants.C_RETURN;
        else
            return -1;
    }

    public String arg1()
    {
        if (tokens.length == 1)
            return tokens[0];
        else
            return tokens[1];
    }

    public String arg2()
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
        if (nextCommand.startsWith(Constants.Comment) || nextCommand.startsWith(Constants.WhiteSpace) || nextCommand.isEmpty())
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
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
