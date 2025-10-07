package simple;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class input
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static int integers(String customMessage)
    {
        System.out.print(customMessage);
        int n = Integer.MIN_VALUE;
        try
        {
            n = Integer.parseInt(br.readLine());
        }
        catch (Exception e)
        {
            //todo add catch blocks
            System.out.println("Bad expression for type Integer");
            e.printStackTrace();
            
            return integers("Re-enter integer again: ");
        }
        
        return n;
    }
    
    public static double real(String customMessage)
    {
        System.out.print(customMessage);
        double n = Double.MIN_VALUE;
        try
        {
            n = Double.parseDouble(br.readLine());
        }
        catch (Exception e)
        {
            //todo add catch blocks
            System.out.println("Bad expression for type Integer");
            e.printStackTrace();
            
            return real("Re-enter real no. again: ");
        }
        
        return n;
    }
    
    public static String line(String customMessage)
    {
        System.out.print(customMessage);
        String n = null;
        try
        {
            n = br.readLine();
        }
        catch (Exception e)
        {
            //todo add catch blocks
            System.out.println("Bad expression for type String");
            e.printStackTrace();
            
            return line("Re-enter String again: ");
        }
        
        return n;
    }
    
    public static boolean bool(String customMessage)
    {
        System.out.println(customMessage);
        String n = null;
        try
        {
            n = br.readLine();
        }
        catch (Exception e)
        {
            //todo add exceptions here
            System.out.println("Bad expression for type String >> boolean");
            System.out.println("Use \"true\" or \"false\" inputs only");
            e.printStackTrace();
            
            return bool("Re-enter boolean again: ");
        }
        
        boolean eval = false;
        if (n.toLowerCase().equalsIgnoreCase("true"))
            eval = true;
        else if (n.toLowerCase().equalsIgnoreCase("false"))
            eval = false;
        else
            return bool("Invalid choice for types \'true\' and \'false\'\n");
        return eval;
    }

    public static char character(String customMessage)
    {
        System.out.println(customMessage);
        char ch = (char) 0;
        try
        {
            ch = br.readLine().charAt(0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Bad expression for type Character");
            return character("Re-enter character again.");
        }

        return ch;
    }
    
    public static void enterLock()
    {
        System.out.println("Press Enter to continue.");
        try
        {
            br.readLine();
        }
        catch (Exception e)
        {
            //todo add more catch logic here
            e.printStackTrace();
        }
    }
}