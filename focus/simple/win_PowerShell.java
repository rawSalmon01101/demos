package simple;
import java.io.IOException;


//forked win_cmd, changes pending.
public class win_PowerShell
{
    static input i = new input();
    
    public static void IO ()
    {
        //bug: the input echoes twice
        ProcessBuilder pb = new ProcessBuilder("powershell.exe");
        pb.inheritIO();
        pb.redirectErrorStream(true);
        try
        {
            Process process = pb.start();
            process.waitFor();
        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }
        
        System.out.println("Ended PowerShell session.");
    }
    public static void run_once(String str)
    {
        System.out.println("Java Project >> " + str);
        try
        {
            new ProcessBuilder("powershell.exe", "/c", str).inheritIO().redirectErrorStream(true).start().waitFor();
        }
        catch (Exception e)
        {
            //todo add catch logic
            e.printStackTrace();
        }
    }
    public static void elevated_IO()
    {
        //bug: the input echoes twice
        System.out.println("External terminal, thread halted");
        
        ProcessBuilder pb = new ProcessBuilder("powershell.exe", "-Command", "powershell.exe -Command Start-Process powershell.exe -Verb runAs");
        //buggy line
        //ProcessBuilder pb = new ProcessBuilder("powershell.exe", "-Command", "powershell.exe -Command \"$p = Start-Process cmd -Verb runAs -PassThru; if ($p -ne $null) {Wait-Process -Id $p.Id} \"");
        pb.inheritIO();
        pb.redirectErrorStream(true);
        try
        {
            Process process = pb.start();
            process.waitFor();
        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }
        
        //currently there is no way to tell if the elevated cmd has been terminated 
        //or not frok powershell without a proper oneliner script, thus
        //the detection of termination and sugesting ended process is not
        //possible.
        //System.out.println("Ended elevated cmd session.");
    }
}
