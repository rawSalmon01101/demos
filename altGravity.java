import simple.*;
import java.io.*;

class altGravity
{
    static final double BIG_G = 6.6743E-11; //                  N m^2 kg^-2
    static final double R_EARTH = 6371; //                      km
    static final double SMALL_G = 9.80665; //                   m/s^2

    static double MASS_EARTH; //                                kg

    public static void main(String[] args) throws IOException, InterruptedException
    {
        //determining the mass of Earth in this case
        MASS_EARTH = (SMALL_G * (R_EARTH * R_EARTH)) / BIG_G;

        printVals();

        //benchmarking
        long start = System.nanoTime();

        //begin calculation
        FileWriter fw = new FileWriter("result.csv");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Height(x),Accn due to gravity(Y)\n"); //define cols

        //range from surface to earth radius from the surface of earth
        for (double i = R_EARTH; i < R_EARTH * 2; i += 1.0d) //simulating dx
        {
            double g = (BIG_G * MASS_EARTH) / (i * i);
            System.out.println("Relative height: " + (i - R_EARTH) + ", g = " + g);
            bw.write(i + "," + g + "\n");
        }

        //finalising IO
        System.out.println("Finalising file IO...");
        bw.flush();
        bw.close();
        fw.close();

        //end benchmark
        long end = System.nanoTime();
        System.out.print("Task fnished in ");
        System.out.print(((double) end - start) / 1_000_000);
        System.out.print(" ms\n");

        postWrite(5);
    }

    public static void postWrite(int secDelay) throws InterruptedException
    {
        System.out.println("Sent notification");
        legacyNotifs.send("Your CSV is ready!", "It will open in Excel automatically " + secDelay + "s later.");
        Thread.sleep(secDelay * 1000);

        //composing launch dir / excel commandline
        String currentDir = win_PowerShell.get_output("get-location");
        currentDir = currentDir.substring(currentDir.lastIndexOf('-') + 1).trim();

        String excelPath = System.getenv("programfiles(x86)") + "\\Microsoft Office\\root\\Office16\\excel.exe";
        String csvPath = currentDir + "\\result.csv";

        String cmd = String.format(
        "Start-Process -FilePath '%s' -ArgumentList '%s'",
        excelPath.replace("'", "''"),
        csvPath.replace("'", "''")
        );
        
        win_PowerShell.run_once(cmd);
    }
    public static void printVals() throws InterruptedException
    {
        System.out.println("MASS = " + MASS_EARTH);
        System.out.println("R_EARTH = " + R_EARTH);
        System.out.println("SMALL_G = " + SMALL_G);
        System.out.println("BIG_G = " + BIG_G);
        Thread.sleep(4000);
    }
}


/*
 * How I'd imagine this program as a Shakespearean theatre:
 * 
    Stage: “altGravity Cookery” - a peculiar tavern where physics is served hot. A wooden sign reads “Equations Cooked Fresh Daily.”

    Enter CUSTOMER
    CUSTOMER:
    Good morrow, kind folk! I crave a dish of falling weights-
    Bring forth the feast of gravities, so I may chart their descent.

    Enter CHEF NEWTONIUS (stirring pots of numbers)
    CHEF:
    Anon! I take yon constant, mighty G,
    Stir mass and radius into thine iron pot,
    And lo! From heaven’s table falls acceleration!
    (flings CSV rows like pancakes onto a tray)

    Enter BUTLER LEGACY (bowing with tray in hand)
    BUTLER:
    M’lord, thy feast is writ upon this scroll,
    A parchment most precise—nay, a CSV.
    Attend thine ear: ding!- the meal is ready.

    Enter VALET POWERSHELLINGTON
    VALET:
    Shall I, good sir, fetch noble Excel hence?
    He waiteth yonder in chamber Office16,
    Array’d in green and graphs most fair.
    (snaps fingers, summons Sir Excel in a puff of dust)

    Enter SIR EXCEL (flourish, monocle gleaming)
    EXCEL:
    Behold! With axes twain shall I plot thy curve,
    A slope that wanes as height ascendeth.
    Come, let us feast upon the law of squares inverse!

    Enter LADY XAMLIA (sweeping in with flair)
    XAMLIA:
    Perchance thou desirest buttons, images, or flair?
    I bring adornments fit for modern eyes,
    Though legacy be swifter, plain, and true.

    CUSTOMER (raising goblet):
    Hark! A troupe of code turned actors bold!
    This tavern serves no meat nor ale,
    But data piping hot, and gravity distilled.

    Exeunt all, stage left.
*/