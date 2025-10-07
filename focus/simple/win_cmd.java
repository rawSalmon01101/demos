package simple;
import java.io.IOException;

public class win_cmd
{
    static input i = new input();
    
    @Deprecated
    public static void start_cmd ()
    {
        System.out.println("Running as: current user >> Java projects folder");
        
        commandRecurse();
    }
    @Deprecated
    protected static void commandRecurse ()
    {
        String str = i.line("");
        
        if (str.trim().equalsIgnoreCase("exit"))
            System.exit(0);
        
        try
        {
            new ProcessBuilder("cmd", "/c", str).inheritIO().start().waitFor();
        }
        catch (IOException e)
        {
            System.out.println("Encountered java.io.IOException");
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            System.out.println("Encountered java.lang.InterruptedException");
            e.printStackTrace();
        }
        catch (Exception e)
        {
            System.out.println("Miscellaneous fatal error");
            e.printStackTrace();
        }
        
        commandRecurse();
    }
    
    
    
    public static void IO ()
    {
        //bug: the input echoes twice
        ProcessBuilder pb = new ProcessBuilder("cmd");
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
        
        System.out.println("Ended cmd session.");
    }
    public static void run_once(String str)
    {
        System.out.println("Java Project >> " + str);
        try
        {
            new ProcessBuilder("cmd", "/c", str).inheritIO().redirectErrorStream(true).start().waitFor();
        }
        catch (Exception e)
        {
            //todo add catch logic
            e.printStackTrace();
        }
    }
    public static void start_process (String str, boolean maximised, boolean minimised)
    {
        String line = "start ";
        
        if (maximised && minimised)
            minimised = false;
        if (maximised)
            line = line.concat("/max ");
        if (minimised)
            line = line.concat("/min ");
            
        line = line.concat(str + " ");
            
        System.out.println("Java Project >> " + line);
        
        try
        {
            new ProcessBuilder("cmd", "/c", line).inheritIO().redirectErrorStream(true).start().waitFor();
        }
        catch (Exception e)
        {
            //todo add catch logic
            e.printStackTrace();
        }
    }
    public static void kill_process (String str)
    {
        try
        {
            new ProcessBuilder("cmd", "/c", "taskkill /f /im ".concat(str)).inheritIO().redirectErrorStream(true).start().waitFor();
        }
        catch (Exception e)
        {
            //todo add catch logic
            e.printStackTrace();
        }
    }
    public static void restart_process (String str)
    {
        String line = "taskkill /f /im " + str + " && start " + str;
        System.out.println("Java Project >> " + line);
        
        try
        {
            new ProcessBuilder("cmd", "/c", line).inheritIO().redirectErrorStream(true).start().waitFor();
            
            System.out.println("Started process " + str);
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
        
        ProcessBuilder pb = new ProcessBuilder("powershell.exe", "-Command", "powershell.exe -Command Start-Process cmd -Verb runAs");
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
        
        //oh yeah you wanna see if powershell can handle
        //elevated cmd termnated or not? first give
        //satya nadella and james gosling a sloppy head each
        //after whihc the first one will happily ask you to turn on
        //telemetry in your win11 build so he can have bdmsm with the
        //rest of you
    }
}