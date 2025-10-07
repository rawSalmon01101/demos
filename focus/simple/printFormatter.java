package simple;

public class printFormatter 
{
    //toolbar printers
    public static void toolbar_no_anim (String toolbar[], String customSeparator)
    {
        //thanos snaps out of existence
        win_cmd.run_once("cls");

        for (int i = 0; i < toolbar.length; i++)
            if (i + 1 == toolbar.length)
                System.out.print(toolbar[i]);
            else
                System.out.print(toolbar[i].concat(customSeparator));
    }
    public static void toolbar_ease_out (String toolbar[], int spacing)
    {
        win_cmd.run_once("cls"); //the terminal cries WAaAAaAaAaaAaAaAaAaAaAAaAAaaaAA

        for (int i = 1; i <= spacing; i++)
        {
            for (int j = 0; j < toolbar.length; j++)
            {
                System.out.print(toolbar[j]);
                
                for (int k = 1; k <= i; k++)
                    System.out.print(" ");
            }

            try
            {
                // *snore* zzzzzzzzz
                Thread.sleep((10 * (i * (i / 2))));
                //the terminal cries WaAaaaaAaaaAAaaAaaA again
                win_cmd.run_once("cls"); 
            }
            catch (Exception e)
            {
                //todo nothing to enter catch
            }
        }

        for (int i = 0; i < toolbar.length; i++)
        {
            System.out.print(toolbar[i]);
            
            for (int j = 1; j <= spacing; j++)
                System.out.print(" ");
        }

        System.out.println();
    }
    public static void numberedListPrint(String str[])
    {
        for (int i = 0; i < str.length; i++)
            System.out.println((i + 1) + ".\t" + str[i]);
    }

    public static void indeterminateProgress(int loops, String message)
    {
        try
        {
            for (int i = 1; i <= loops; i++)
            {
                for (int j = 1; j <= 70; j++)
                {
                    System.out.println(message + "\n---------------------------------------------------" + "\n");
    
                    for (int k = 1; k <= 50; k++)
                    {
                        if (j == k || j - 5 == k || j - 10 == k || j - 15 == k || j - 20 == k)
                            System.out.print(">");
                        else
                            System.out.print(" ");
                    }

                    System.out.print("|");
    
                    //Thread.sleep((j == 100)? 200 : 20);
                    Thread.sleep(80);
                    win_cmd.run_once("cls");
                }
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}