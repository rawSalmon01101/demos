import java.util.*;
class numguess
{
    static int g, t, score = 10, tries = 5;
    static boolean tryPref = true;
    public static void main (String args[])
    {
        System.out.println("Welcome! The numbers generated are always between 0 and 9, both limits included, and you get 5 tries. Have fun!");
        numGen();
    }
    public static void numGen()
    {
        double ran = Math.random() * 10;
        t = (int) ran;
        start();
    }
    public static void start()
    {
        Scanner start = new Scanner (System.in);
        if (score < 1)
        {
            System.out.println("Whoops! You hit the bottom of the pit; better luck next time.");
            System.exit(0);
        }
        System.out.println("\n\nGuess the number!");
        g = start.nextInt();
        matchHandler();
        start.close();
    }
    public static void matchHandler()
    {
        Scanner Handler = new Scanner (System.in);
        if (g == t)
        {
            score = score + 10;
            System.out.println("Correct guess! Do you want to get a new number or quit?\tScore: "+score);
            choice();
        }
        else if (tryPref == true && g != t)
        {
            tries--;
            if (tries > 0)
            {
                score = score - 2;
                System.out.println("Wrong guess! You have "+tries+" try/tries left. Continue, quit or get a new number? Score: "+score);
                choice();
            }
            else
            {
                System.out.println("You have exhausted your tries! Fetching a new number for you...\n--------------------\n");
                numGen();
            }
        }
        else if (g != t)
        {
            System.out.println("Wrong guess! New number or quit?");
            choice();
        }
        Handler.close();
    }
    public static void choice()
    {
        Scanner choice = new Scanner (System.in);
        String ch = choice.nextLine().toLowerCase();
        if (ch.contains("continue"))
        {
            start();
        }
        else if (ch.contains("quit"))
        {
            System.exit(0);
        }
        else if (ch.contains("new"))
        {
            tries = 5;
            numGen();
        }
        else if (ch.contains("preferences"))
        {
            gameSettings();
        }
        choice.close();
    }
    public static void gameSettings()
    {
        Scanner set = new Scanner (System.in);
        String op = "default";
        while (op != "resumegame")
        {
            System.out.println("Enter options as they are, to open them.\n");
            System.out.println("resumegame\ntries\nabout");
            op = set.nextLine();
            if (op.contains("resume_game"))
            {
                start();
            }
            else if (op.contains("tries"))
            {
                System.out.print("status: ");
                if (tryPref == true)
                {
                    System.out.println("true. write false / exit");
                    op = set.nextLine();
                    if (op.contains("false"))
                    {
                        tryPref = false;
                        System.out.println("changes-saved");
                        continue;
                    }
                    else if (op.contains("exit"))
                    {
                        continue;
                    }
                }
                else if (tryPref == false)
                {
                    System.out.println("false. write true / exit");
                    op = set.nextLine();
                    if (op.contains("true"))
                    {
                        tryPref = true;
                        System.out.println("changes-saved");
                        continue;
                    }
                    else if (op.contains("exit"))
                    {
                        continue;
                    }
                }
            }
            else if (op.contains("about"))
            {
                prefAbout();
            }
        }
        set.close();
    }
    public static void prefAbout()
    {
        System.out.println("A huge thanks to the contributors below: "); 
        System.out.println("Me\nMyself\nNo one else");
        System.out.println("\n\nMade under 2 hours with the motivation being a test of what I've learnt over the years.");
        System.out.println("Jumping back into game...");
        start();
    }
}