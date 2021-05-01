package CodeMod;

import Driver.Constants;

public class CodeMod
{
    private CodeMod()
    {

    }

    public static String dest(String mnemonic)
    {
        return Constants.destMap.get(mnemonic);
    }


    public static String comp(String mnemonic)
    {
        return Constants.compMap.get(mnemonic);
    }


    public static String jump(String mnemonic)
    {
        return Constants.jumpMap.get(mnemonic);
    }
}
