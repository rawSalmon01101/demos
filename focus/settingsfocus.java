import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import simple.input;

public class settingsfocus
{
    public void openSettings()
    {
        checkConfig();
        
        try
        {
            Thread.sleep(8000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        settingsMenu();
        int choice = input.integers(null);
    }

    private void checkConfig()
    {
        
        try
        {
            FileReader fr = new FileReader("settings.config");
        }
        catch(IOException e)
        {
            try
            {
                System.out.println("File was either missing or created for first time.");
                FileWriter fw = new FileWriter("settings.config");
                fw.close();
                System.out.println("Rebuild completed.");
            }
            catch (IOException  ioe)
            {
                ioe.printStackTrace();
                System.out.println("Serious bug encountered. Terminating program.");
                System.exit(999);
            }
        }
    }

    public void settingsMenu()
    {
        final String options[] = {"Rebuild configurations",
                                  "Change alert mode",
                                  "Save and exit"};

        simple.printFormatter.numberedListPrint(options);
    }
}