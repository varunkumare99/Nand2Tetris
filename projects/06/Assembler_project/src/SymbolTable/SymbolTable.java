package SymbolTable;

import Driver.Constants;

import java.util.HashMap;

public class SymbolTable
{
    public HashMap<String, Integer> symbolTable;

    public SymbolTable()
    {
        symbolTable = new HashMap<String,Integer>();
        symbolTable.put(Constants.R0, Integer.valueOf(0));
        symbolTable.put(Constants.R1, Integer.valueOf(1));
        symbolTable.put(Constants.R2, Integer.valueOf(2));
        symbolTable.put(Constants.R3, Integer.valueOf(3));
        symbolTable.put(Constants.R4, Integer.valueOf(4));
        symbolTable.put(Constants.R5, Integer.valueOf(5));
        symbolTable.put(Constants.R6, Integer.valueOf(6));
        symbolTable.put(Constants.R7, Integer.valueOf(7));
        symbolTable.put(Constants.R8, Integer.valueOf(8));
        symbolTable.put(Constants.R9, Integer.valueOf(9));
        symbolTable.put(Constants.R10, Integer.valueOf(10));
        symbolTable.put(Constants.R11, Integer.valueOf(11));
        symbolTable.put(Constants.R12, Integer.valueOf(12));
        symbolTable.put(Constants.R13, Integer.valueOf(13));
        symbolTable.put(Constants.R14, Integer.valueOf(14));
        symbolTable.put(Constants.R15, Integer.valueOf(15));
        symbolTable.put(Constants.SCREEN, Integer.valueOf(16384));
        symbolTable.put(Constants.KBD, Integer.valueOf(24576));
        symbolTable.put(Constants.SP, Integer.valueOf(0));
        symbolTable.put(Constants.LCL, Integer.valueOf(1));
        symbolTable.put(Constants.ARG, Integer.valueOf(2));
        symbolTable.put(Constants.THIS, Integer.valueOf(3));
        symbolTable.put(Constants.THAT, Integer.valueOf(4));
    }

    public void addEntry(String symbol, Integer address)
    {
        symbolTable.put(symbol, address);
    }

    public boolean contains(String symbol)
    {
        return symbolTable.containsKey(symbol);
    }

    public Integer getAddress(String symbol)
    {
        return symbolTable.get(symbol);
    }
}
