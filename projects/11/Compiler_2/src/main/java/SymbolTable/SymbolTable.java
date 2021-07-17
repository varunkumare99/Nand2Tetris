package SymbolTable;

import org.javatuples.Triplet;

import java.util.HashMap;
import java.util.Map;

import static SymbolTable.Constants.*;

public class SymbolTable
{
    HashMap<String, Triplet<String, String, Integer>> classSymbolTable;
    HashMap<String, Triplet<String, String, Integer>> subroutineSymbolTable;
    Integer staticVarCount;
    Integer fieldVarCount;
    Integer argumentVarCount;
    Integer localVarCount;

    public SymbolTable()
    {
        classSymbolTable = new HashMap<>();
        staticVarCount = 0;
        fieldVarCount = 0;
    }

    public void startSubroutine()
    {
        subroutineSymbolTable = new HashMap<>();
        argumentVarCount = 0;
        localVarCount = 0;
    }

    public void define(String name, String type, String kindOf)
    {
        if (kindOf.equals(ARGUMENT_VAR) || kindOf.equals(LOCAL_VAR))
        {
            if (kindOf.equals(ARGUMENT_VAR))
            {
                subroutineSymbolTable.put(name, new Triplet<>(type, kindOf, argumentVarCount));
                argumentVarCount++;
            } else
            {
                subroutineSymbolTable.put(name, new Triplet<>(type, kindOf, localVarCount));
                localVarCount++;
            }
        } else
        {
            if (kindOf.equals(STATIC_VAR))
            {
                classSymbolTable.put(name, new Triplet<>(type, kindOf, staticVarCount));
                staticVarCount++;
            } else
            {
                classSymbolTable.put(name, new Triplet<>(type, kindOf, fieldVarCount));
                fieldVarCount++;
            }
        }
    }

    public Integer varCount(String kindOf)
    {

        switch (kindOf)
        {
            case STATIC_VAR:
                return staticVarCount;
            case ARGUMENT_VAR:
                return argumentVarCount;
            case FIELD_VAR:
                return fieldVarCount;
            case LOCAL_VAR:
                return localVarCount;
            default:
                return 0;
        }
    }

    public String kindOf(String name)
    {
        if (subroutineSymbolTable != null && subroutineSymbolTable.containsKey(name))
        {
            return subroutineSymbolTable.get(name).getValue1();
        } else if (classSymbolTable.containsKey(name))
        {
            return classSymbolTable.get(name).getValue1();
        } else
        {
            return NONE_VAR;
        }
    }

    public String typeOf(String name)
    {
        if (subroutineSymbolTable.containsKey(name))
        {
            return subroutineSymbolTable.get(name).getValue0();
        } else //class variable
        {
            return classSymbolTable.get(name).getValue0();
        }
    }

    public Integer indexOf(String name)
    {
        if (subroutineSymbolTable.containsKey(name))
        {
            return subroutineSymbolTable.get(name).getValue2();
        } else //class variable
        {
            return classSymbolTable.get(name).getValue2();
        }
    }

    public boolean isVariableDefinedAnyWhereInClass(String name)
    {
        return ((subroutineSymbolTable != null && subroutineSymbolTable.containsKey(name)) || classSymbolTable.containsKey(name));
    }

    public int getNumberOfFieldVariables()
    {
        int count = 0;
        for (Map.Entry<String, Triplet<String, String, Integer>> row : classSymbolTable.entrySet())
        {
            Triplet<String, String, Integer> trip = row.getValue();
            if (trip.getValue1().equals(FIELD_VAR))
                count++;
        }
        return count;
    }
}
