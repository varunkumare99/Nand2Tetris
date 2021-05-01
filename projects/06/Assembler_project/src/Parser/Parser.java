package Parser;

import CodeMod.CodeMod;
import Driver.Constants;
import SymbolTable.SymbolTable;

import java.io.*;

public class Parser
{
    BufferedReader bufferedReader;
    PrintWriter printWriter;
    StringBuilder outputString;
    String currentCommand;
    String inputFileName;
    String outputFileName;
    Integer nextLineNumber = 0;
    Integer variableCount = Constants.StartingMemoryLocation;
    Integer passCount;
    SymbolTable symbolTable;

    public Parser(String inputFileName, String outputFileName)
    {
        try
        {
            this.inputFileName = inputFileName;
            this.outputFileName = outputFileName;
            this.outputString = new StringBuilder("");
            bufferedReader = new BufferedReader(new FileReader(this.inputFileName));
            printWriter = new PrintWriter(new FileWriter(outputFileName));
            symbolTable = new SymbolTable();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setPassCount(Integer passCount)
    {
        this.passCount = passCount;
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

    public void advance()
    {
        if (passCount == Constants.FirstPass)
            firstPass();
        else if (passCount == Constants.SecondPass)
            secondPass();
    }

    private void firstPass()
    {
        while (hasMoreCommands())
        {
            trimCommand();
            if (checkForCommentsWhiteSpaces())
            {
                continue;
            }
            else if (commandType() == Constants.L_COMMAND)
            {
                handleLcommand();
            }
            else
            {
                nextLineNumber++;
            }
        }
    }

    private void secondPass()
    {
        initIOfiles();
        while (hasMoreCommands())
        {
            trimCommand();
            if (checkForCommentsWhiteSpaces() || currentCommand.startsWith(Constants.LabelOpen))
            {
                continue;
            } else
            {
                removeTrailingComment();
                trimCommand();
                Integer commandType = commandType();
                handleCommands(commandType);
            }
            writeToFile();
        }
    }

    private void initIOfiles()
    {
        try
        {
            bufferedReader = new BufferedReader(new FileReader(this.inputFileName));
            printWriter = new PrintWriter(new FileWriter(this.outputFileName));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

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

    private void stripLabelName()
    {
        int openLabelPos = currentCommand.indexOf(Constants.LabelOpen);
        int closeLabelPos = currentCommand.indexOf(Constants.LabelClose);
        currentCommand = currentCommand.substring(openLabelPos + 1, closeLabelPos);
    }

    private Integer commandType()
    {
        String firstChar = currentCommand.substring(0, 1);
        if (firstChar.compareTo(Constants.Ainstruction) == 0)
            return Constants.A_COMMAND;
        else if (firstChar.compareTo(Constants.LabelOpen) == 0)
            return Constants.L_COMMAND;
        else
            return Constants.C_COMMAND;
    }

    private void handleCommands(Integer commandType)
    {
        if (commandType == Constants.A_COMMAND)
            handleAcommand();
        else
            handleCcommand();
    }

    private void handleCcommand()
    {
        outputString.append(Constants.CinstructionPrefix);
        outputString.append(comp());
        outputString.append(dest());
        outputString.append(jump());
    }

    private void handleAcommand()
    {
        symbol();
        Integer decimalNumber;
        try
        {
            decimalNumber = Integer.parseInt(currentCommand);
        } catch (NumberFormatException e)
        {
            decimalNumber = getNumber();
        }
        convertNumberToBinaryString(decimalNumber);
    }

    private void convertNumberToBinaryString(Integer number)
    {
        String binaryNumber = Integer.toBinaryString(number);
        addZeroes(binaryNumber);
        outputString.append(binaryNumber);
    }

    private Integer getNumber()
    {
        Integer number;
        if (symbolTable.contains(currentCommand))
            number = symbolTable.getAddress(currentCommand);
        else
        {
            symbolTable.addEntry(currentCommand, variableCount);
            number = variableCount;
            variableCount++;
        }
        return number;
    }

    private void handleLcommand()
    {
        symbol();
        symbolTable.addEntry(currentCommand, nextLineNumber);
    }

    private void addZeroes(String binaryNumber)
    {
        for (int i = 0; i < 16 - binaryNumber.length(); ++i)
            appendZeroAtBeginning();
    }

    private void appendZeroAtBeginning()
    {
        outputString.reverse();
        outputString.append("0");
        outputString.reverse();
    }

    private void symbol()
    {
        if (commandType() == Constants.A_COMMAND)
            currentCommand = currentCommand.substring(1, currentCommand.length());   //remove @ in the beginning
        else if (commandType() == Constants.L_COMMAND)
            stripLabelName();

    }

    private String dest()
    {
        String destString;
        int equalPos = currentCommand.indexOf(Constants.equals);

        if(equalPos != -1)
            destString = currentCommand.substring(0, equalPos);
        else
            destString = Constants.dest_NULL_DEST;
        return CodeMod.dest(destString);
    }

    private String comp()
    {
        String compString = "";
        int equalPos = currentCommand.indexOf(Constants.equals);
        int colonPos = currentCommand.indexOf(Constants.semiColon);
        if(colonPos != -1 && equalPos != -1)
            compString = currentCommand.substring(equalPos+1, colonPos);
        else if(colonPos == -1 && equalPos != -1)
            compString = currentCommand.substring(equalPos+1,currentCommand.length());
        else if(colonPos != -1 && equalPos == -1)
            compString = currentCommand.substring(0,colonPos);

        return CodeMod.comp(compString);
    }

    private String jump()
    {
        String jumpString;
        int colonPos = currentCommand.indexOf(Constants.semiColon);
        if(colonPos != -1)
            jumpString = currentCommand.substring(colonPos + 1, currentCommand.length());
        else
            jumpString = Constants.jump_NULL_JUMP;

        return CodeMod.jump(jumpString);
    }


    private void writeToFile()
    {
        printWriter.write(outputString.toString() + "\n");
        outputString.setLength(0);
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
        printWriter.close();
    }
}
