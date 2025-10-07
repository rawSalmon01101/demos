import java.util.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.Locale;

import simple.input;

/*
 * Formatting of timer.config
 * LINE 1: <is calibrated before?> 1 for true, 0 for false
 * LINE 2: <amount of tab space>
 * LINE 3: <amount of whitespace>
 * LINE 4: TODO base64 hash
 * ------END
 * 
 * 
 * TODO: Implement base64 based simple hashing
 * so that config tampering detection is possible
 * 
 * TODO: work needed
 * 
 */
class focus 
{
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception
    {
        //tabs and spaces stored.
        int tab = 0;
        int spaces = 0;

        //code may generate nfe, e
        try
        {
            FileReader fr = new FileReader("timer.config");
            BufferedReader br = new BufferedReader(fr);

            //verifying config integrity
            if (Integer.parseInt(br.readLine()) == 1)
            {
                tab = Integer.parseInt(br.readLine());
                spaces = Integer.parseInt(br.readLine());
            }
            else
            {
                tab = calibrateScreenSpace();
                spaces = calibrateScreenWhitespace(tab);

                writeConfig(tab, spaces);
            }
        }
        catch (NumberFormatException nfee)
        {
            System.out.println("Required integer, found other type. Rebuilding config...");
            Thread.sleep(800);
            tab = calibrateScreenSpace();
            spaces = calibrateScreenWhitespace(tab);
            writeConfig(tab, spaces);
        }
        catch (FileNotFoundException fnfe)
        {
            FileWriter dummy = new FileWriter("timer.config", true);

            System.out.println("Building timer.config for first time...");
            Thread.sleep(800);

            tab = calibrateScreenSpace();
            spaces = calibrateScreenWhitespace(tab);

            //writing config. TODO move to else and exception.
            writeConfig(tab, spaces);
            dummy.close();
        }
        catch (Exception globale)
        {
            System.out.println("Something seriously went wrong.");
            System.out.println(globale);
        }
        
        //clearing screen after config done and command call.
        clearScreen();

        //choose option
        System.out.println("1> TIMER \t 2> SETTINGS");
        int choice = input.integers("");
        if (choice == 1)
        {
            
        }
        
        //timer picker ui
        clearScreen();
        System.out.println("1> TIMER");
        System.out.println("Duration (Minutes)");
        double min = sc.nextDouble();

        //clearing screen again
        clearScreen();

        //timer loop
        for (int i = (int) (min * 60); i > 0; i--)
        {
            int minutes = i / 60;
            int seconds = i % 60;
            System.out.print((minutes <= 10? "0" : "") + (minutes));
            System.out.print(":");
            System.out.print((seconds < 10? "0" : "") + (seconds));

            for (int j = 1; j <= tab; j++)
                System.out.print("\t");
            
            for (int j = 1; j <= spaces; j++)
                System.out.print(" ");

            System.out.println(getLocalTime()); 
            
            //abusing thread.sleep because why not?!?!??!
            Thread.sleep(1000);
            clearScreen();
        }

        //bell doesn't work in some terminals, alternative soln
        for (int i = 0; i < 10000; i++)
            System.out.print('O');
        
        System.exit(0);
    }

    public static int calibrateScreenSpace() throws InterruptedException
    {
        System.out.println("Calibrating screen space. Manual input required.");
        Thread.sleep(1000);
        clearScreen();

        char ch = ' '; //adjusting.
        int tabSpace = 10; //predefined, suits most FHD monitors as is.

        System.out.println("MM:SS");
        
        while (true)
        {
            clearScreen();

            System.out.print("MM:SS");
            for (int i = 1; i <= tabSpace; i++)
            {
                System.out.print("\t");
            }
            System.out.print("XX:YY"); //system clock

            System.out.println("\n\nDoes the system clock roughly match the screen edge? Y(yes) /L(less space needed) / M(more needed)");
            System.out.println("You can make fine adjustments later.");
            ch = sc.next().charAt(0);

            if (ch == 'Y' || ch == 'y')
                break;
            else if (ch == 'L' || ch == 'l')
                tabSpace -= 1;
            else if (ch == 'M' || ch == 'm')
                tabSpace += 1;
        }

        return tabSpace;
    }
    public static int calibrateScreenWhitespace (int tab) throws IOException, InterruptedException
    {
        //whitespace based configuration.
        System.out.println("Refining configuration...");
        Thread.sleep(500);

        int whitespaces = 10;
        char ch = ' ';

        while (true)
        {
            clearScreen();
            
            System.out.print("MM:HH");
            for (int j = 1; j <= tab; j++)
                System.out.print("\t");
            
            for (int j =  1; j <= whitespaces; j++)
                System.out.print(" ");
            
            System.out.print("XX:YY");

            System.out.println("\n\nAdjust so that the clock sits just at the edge of the right \ncorner of screen.");
            System.out.println("Y(yes) /L(less space needed) / M(more needed)");

            ch = sc.next().charAt(0);

            if (ch == 'Y' || ch == 'y')
                break;
            else if (ch == 'L' || ch == 'l')
                whitespaces -= 1;
            else if (ch == 'M' || ch == 'm')
                whitespaces += 1;
        }

        return whitespaces;
    }

    public static void writeConfig (int tab, int space) throws IOException, InterruptedException, NumberFormatException //idk why i'm throwing these but it makes me cool ig
    {
        //actual filewriter for config
        FileWriter fw = new FileWriter("timer.config");
        fw.write("1\n");
        fw.write(Integer.toString(tab) + "\n");
        fw.write(Integer.toString(space));
        //TODO here: base64 based hashing. refer #1
        fw.close();
    }

    public static String getLocalTime () throws Exception
    {
        try 
        {
            //get current time zone based
            LocalTime now = LocalTime.now(ZoneId.systemDefault());
            //formatting MM:SS
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault());

            return now.format(formatter);
        }
        catch (Exception e)
        {
            throw new Exception("Error fetching time. Report to devs" + e.getMessage(), e);
        }
    }

    public static void clearScreen ()
    {
        System.out.print("\033[H\033[2J");
    }

    @Deprecated
    public static void settings () throws Exception
    {
        clearScreen();
        System.out.println("2> SETTINGS");
        System.out.println("1 Rebuild screen space configurations.");
        System.out.println("2 Credits");
        //TODO implement a view history option

        byte ch = sc.nextByte();

        switch(ch)
        {
            case 1:
                errorNoImplementation();
                Thread.sleep(1000);

                FileWriter fw2 = new FileWriter("timer.config", false);
                //TODO: Delete the timer.config file
                fw2.write("0");
                fw2.close();
                main(new String[0]);
            break;

            case 2:
                System.out.println("Being built by rawSalmon01101 (aka Owoo6)");
                System.out.println("Hobby project, so if you find this useful, consider starring the repo.");
                System.out.println("Press Enter to continue...");
                sc.nextLine();
                sc.nextLine();
                break;
                
            default:
                System.out.println("Value not implemented.");
        }
        
        main(new String[0]);
    }
    public static void betterSettings() throws InterruptedException
    {
        System.out.println("Opening settings > ");
        settingsfocus sf = new settingsfocus();
        sf.openSettings();
    }

    public static void errorNoImplementation()
    {
        System.out.println("Method under implementation.");
    }
}