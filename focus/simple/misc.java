package simple;

public class misc
{
    public class $gate
    {
        public static boolean marriage (boolean male, boolean female)
        {
            return (male == false && female == false)? false : true;
        }
        public static String marriage_String(boolean male, boolean female)
        {
            return (male == false && female == false)? "Man's fault" : "Woman is right";
        }
    }

    public static String betterReplace (String str, int pos, char newChar)
    {
        String dup = "";
        for (int i = 0; i < str.length(); i++)
        {
            if (i == pos)
                dup = dup.concat(newChar + "");
            else
                dup = dup.concat(str.charAt(i) + "");
        }

        return dup;
    }
}