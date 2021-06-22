package JackTokenizer;

import java.io.*;

public class JackTokenizer
{
    BufferedReader bufferedReader;
    PrintWriter printWriter;
    StringBuilder outputStringBuilder;
    String inputFileName;
    String outputFileName;
    String currentToken;
    String bufferedLine;
    String nextToken;
    boolean inBlockComment;

    public JackTokenizer(String inputFileName, String outputFileName)
    {
        try
        {
            this.inputFileName = inputFileName;
            bufferedReader = new BufferedReader(new FileReader(this.inputFileName));
            this.outputFileName = outputFileName;
            printWriter = new PrintWriter(new FileWriter(outputFileName));
            bufferedLine = "";
            outputStringBuilder = new StringBuilder();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public StringBuilder getJackTokens()
    {
        return this.outputStringBuilder;
    }

    public void advance()
    {
        currentToken = nextToken;
        removeCommentSpaces();
    }

    private void removeCommentSpaces()
    {
        trimBufferedLine();
        removeTrailingComment();
        trimBufferedLine();
    }

    public Boolean hasMoreCommands()
    {
        try
        {
            if (!bufferedLine.isEmpty()) //check if buffered Line has tokens to be processed
            {
                parseToken();
                return true;
            } else
            {
                return isNextLineTokens(); //check for tokens in next lines
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    private Boolean isNextLineTokens() throws IOException
    {
        while ((bufferedLine = bufferedReader.readLine()) != null)
        {
            trimBufferedLine();
            if (!checkForCommentsAndEmptyLines())
            {
                parseToken();
                return true;
            }
        }
        return false;
    }

    private void parseToken()
    {
        trimBufferedLine();
        StringBuilder tempToken = new StringBuilder();
        processTokens(tempToken);
    }

    private void processTokens(StringBuilder tempToken)
    {
        int tokenEndPos = 0;
        for (int i = 0; i < bufferedLine.length(); ++i)
        {
            if (bufferedLine.charAt(i) == Constants.WhiteSpace || bufferedLine.charAt(i) == '\n')
            {
                tokenEndPos = i;
                break;
            } else if (isStringToken(i))
            {
                tokenEndPos = parseStringTokens(tempToken, i);
                break;
            } else if (isSymbol(bufferedLine,i))
            {
                tokenEndPos = parseSymbolTokens(tempToken, i);
                break;
            } else
            {
                //parser keywords, identifiers, integerConstant
                tempToken.append(bufferedLine.charAt(i));
            }
        }
        updateBufferedLine(tokenEndPos);
        nextToken = tempToken.toString();
    }

    private boolean isStringToken(int i)
    {
        return bufferedLine.charAt(i) == '"';
    }

    private int parseSymbolTokens(StringBuilder tempToken, int index)
    {
        int tokenEndPos;
        if (index == 0)
        {
            tempToken.append(bufferedLine.charAt(index));
            tokenEndPos = index;
        } else
        {
            tokenEndPos = index - 1;
        }
        return tokenEndPos;
    }

    private int parseStringTokens(StringBuilder tempToken, int index)
    {
        int tokenEndPos;
        tempToken.append(bufferedLine.charAt(index));
        index = processString(index, tempToken);
        tokenEndPos = index;
        return tokenEndPos;
    }

    private int processString(int index, StringBuilder tempToken)
    {
        for (int i = index + 1; i < bufferedLine.length(); ++i)
        {
            tempToken.append(bufferedLine.charAt(i));
            if (isStringToken(i))
            {
                index = i;
                break;
            }
        }
        return index;
    }

    private void updateBufferedLine(int endPos)
    {
        bufferedLine = bufferedLine.substring(endPos + 1);
    }

    public int tokenType()
    {
        if (currentToken.startsWith("\""))
        {
            return Constants.TOKENS.TOKEN_STRING_CONST.ordinal();
        } else if (isIntegerConstant())
        {
            return Constants.TOKENS.TOKEN_INT_CONST.ordinal();
        } else if (isKeyword())
        {
            return Constants.TOKENS.TOKEN_KEYWORD.ordinal();
        } else if (currentToken.length() == 1 && isSymbol(this.currentToken,0))
        {
            return Constants.TOKENS.TOKEN_SYMBOL.ordinal();
        } else
        {
            return Constants.TOKENS.TOKEN_IDENTIFIER.ordinal();
        }

    }

    private boolean isSymbol(String input, int index)
    {
        switch (input.charAt(index))
        {
            case Constants.SYMBOL_AND:
            case Constants.SYMBOL_CLOSE_BRACE:
            case Constants.SYMBOL_COMMA:
            case Constants.SYMBOL_EQUALS:
            case Constants.SYMBOL_DIVISION:
            case Constants.SYMBOL_CLOSE_CURLY_BRACE:
            case Constants.SYMBOL_CLOSE_SQUARE_BRACE:
            case Constants.SYMBOL_GREATER_THAN:
            case Constants.SYMBOL_LESS_THAN:
            case Constants.SYMBOL_MINUS:
            case Constants.SYMBOL_MULTIPLICATION:
            case Constants.SYMBOL_NOT:
            case Constants.SYMBOL_OPEN_BRACE:
            case Constants.SYMBOL_OPEN_CURLY_BRACE:
            case Constants.SYMBOL_OPEN_SQUARE_BRACE:
            case Constants.SYMBOL_OR:
            case Constants.SYMBOL_PERIOD:
            case Constants.SYMBOL_PLUS:
            case Constants.SYMBOL_SEMI_COLON:
                return true;
            default:
                return false;
        }
    }

    private boolean isKeyword()
    {
        switch (currentToken)
        {
            case Constants.KEYWORD_CLASS:
            case Constants.KEYWORD_BOOLEAN:
            case Constants.KEYWORD_CHAR:
            case Constants.KEYWORD_DO:
            case Constants.KEYWORD_CONSTRUCTOR:
            case Constants.KEYWORD_ELSE:
            case Constants.KEYWORD_FALSE:
            case Constants.KEYWORD_IF:
            case Constants.KEYWORD_INT:
            case Constants.KEYWORD_METHOD:
            case Constants.KEYWORD_NULL:
            case Constants.KEYWORD_RETURN:
            case Constants.KEYWORD_STATIC:
            case Constants.KEYWORD_THIS:
            case Constants.KEYWORD_TRUE:
            case Constants.KEYWORD_VOID:
            case Constants.KEYWORD_WHILE:
            case Constants.KEYWORD_LET:
            case Constants.KEYWORD_FUNCTION:
            case Constants.KEYWORD_FIELD:
            case Constants.KEYWORD_VAR:
                return true;
            default:
                return false;
        }
    }

    private boolean isIntegerConstant()
    {
        try
        {
            Integer.parseInt(currentToken);
            return true;
        } catch (NumberFormatException e)
        {
            return false;
        }
    }

    public String keyword()
    {
        return currentToken;
    }

    public char symbol()
    {
        return currentToken.charAt(0);
    }

    public String identifier()
    {
        return currentToken;
    }

    public int intVal()
    {
        return Integer.parseInt(currentToken);
    }

    public String stringVal()
    {
        int stringStartPos = currentToken.indexOf('"');
        int stringLastPos = currentToken.lastIndexOf('"');
        return currentToken.substring(stringStartPos + 1, stringLastPos);
    }

    public void writeToFile()
    {
        printWriter.write(outputStringBuilder.toString() + "\n\n");
        outputStringBuilder.setLength(0);
    }

    public void buildOutputString(String tokenType, String tokenValue)
    {
        outputStringBuilder.append("<").append(tokenType).append("> ").append(tokenValue).append(" </").append(tokenType).append(">").append("\n");
    }

    public void writeString(String input)
    {
        outputStringBuilder.append(input);
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

    private void trimBufferedLine()
    {
        bufferedLine = bufferedLine.trim();
    }

    private void removeTrailingComment()
    {
        if (bufferedLine.contains(Constants.Comment))
        {
            int commentPos = bufferedLine.indexOf(Constants.Comment);
            bufferedLine = bufferedLine.substring(0, commentPos);
        }
    }

    private Boolean checkForCommentsAndEmptyLines()
    {
        if(checkInsideBlockComment())
        {
            return true;
        }
        else if (checkForSingleLineComment())
        {
            return true;
        } else if (checkForSingleLineBlockComment())
        {
            return handleBlockCommentSingleLine();
        }
        else if (bufferedLine.contains(Constants.blockCommentStart))
        {
            return handleBlockCommentStart();
        }
        else if (bufferedLine.contains(Constants.blockCommentEnd))
        {
            return handleBlockCommentEnd();
        }
        else
        {
            return false;
        }
    }

    private Boolean handleBlockCommentEnd()
    {
        inBlockComment = false;
        int index = bufferedLine.indexOf(Constants.blockCommentEnd);
        if (!bufferedLine.endsWith(Constants.blockCommentEnd))
        {
            bufferedLine = bufferedLine.substring(index + 2);
            return false;
        }
        else
        {
            bufferedLine = "";
            return true;
        }
    }

    private Boolean handleBlockCommentStart()
    {
        inBlockComment = true;
        int index = bufferedLine.indexOf(Constants.blockCommentStart);
        if (index!=0)
        {
            bufferedLine = bufferedLine.substring(0,index);
            return false;
        }
        else
        {
            bufferedLine = "";
            return true;
        }
    }

    private boolean checkForSingleLineBlockComment()
    {
        return bufferedLine.contains(Constants.blockCommentStart) && bufferedLine.contains(Constants.blockCommentEnd);
    }

    private Boolean handleBlockCommentSingleLine()
    {
        String tempToken = "";
        int startIndex = bufferedLine.indexOf(Constants.blockCommentStart);
        int endIndex = bufferedLine.indexOf(Constants.blockCommentEnd);
        if (startIndex != 0)
        {
            tempToken = bufferedLine.substring(0, startIndex);
        }

        if (!bufferedLine.endsWith(Constants.blockCommentEnd))
        {
            tempToken = tempToken + " " + bufferedLine.substring(endIndex + 2);
        }
        bufferedLine = tempToken;
        return bufferedLine.length() == 0;
    }

    private boolean checkForSingleLineComment()
    {
        return bufferedLine.startsWith(Constants.Comment)
                || bufferedLine.isEmpty();
    }

    private boolean checkInsideBlockComment()
    {
        return inBlockComment && !bufferedLine.contains(Constants.blockCommentEnd);
    }
}
