import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Files;

class spellchecker
{
    static private float threshold = 0.3f; //0 means everything will show and 1 means none.
    static private boolean flags = false; //Enable flags for debugging purposes?
    public static void main(String[] args) throws IOException, InterruptedException
    {
        //Object initialisation
        BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
        FileWriter fr = new FileWriter("dictionary.txt", true);
        FileReader read = new FileReader("dictionary.txt");
        FileReader fetch = new FileReader("validwords.txt");
        BufferedReader fileRead = new BufferedReader(read);
        BufferedReader fetch2 = new BufferedReader(fetch);
        spellchecker schk = new spellchecker();
        //Object initialisation end

        //Database of correct spellings. Can be adjusted.
        String correctWords [] = new String [(int)schk.countLines(fetch)];

        for (int i = 0; i < correctWords.length; i++)
            correctWords[i] = fetch2.readLine();
        
        //Confidence handling
        double confidencePercent [] = new double [correctWords.length];
        int highestConfidence = 0;
        
        //User input
        String word = br.readLine().toLowerCase();

        //Contextial learning fetch (from dictionary.txt)
        String line = null;
        while ((line = fileRead.readLine()) != null)
        {
            if (line.contains(word))
            {   
                //splits the string into two and stores it in this array. [delimiter: " "]
                String arrsplit2 [] = line.split(" ", 2);
                //the word will always be at index 0
                if (arrsplit2[0].equals(word))
                {
                    int spaceIndex = line.indexOf(" ", word.length());
                    String ogSpell = line.substring(spaceIndex + 1);
                    System.out.println("Predicted \""+ogSpell+"\" based on contextual learning.");
                    System.exit(0);
                }
                else
                {
                    //if word does not match dictionary
                    break;
                }
            }
        }
        //Contextual learning end

        //Confidence calculation
        for (int i = 0; i < correctWords.length; i++) 
        {
            double currentConfidence = 0;
            int minLength = Math.min(correctWords[i].length(), word.length());

            for (int j = 0; j < minLength; j++) 
            {
                if (correctWords[i].charAt(j) == word.charAt(j)) 
                {
                    currentConfidence++;
                }
            }
        
            confidencePercent[i] = currentConfidence / correctWords[i].length();
        }

        //debugging flag
        if (flags == true)
        {
            for (int i = 0; i < confidencePercent.length; i++)
            {
                System.out.print(confidencePercent[i]+"   ");
            }
            System.out.println();
        }

        //Word with highest confidence
        for (int i = 1; i < confidencePercent.length; i++)
        {
            if (confidencePercent[i] >= confidencePercent[highestConfidence])
                highestConfidence = i;
        }

        System.out.println("Predicted the word \""+correctWords[highestConfidence]+"\" with "+(confidencePercent[highestConfidence]*100)+"% confidence.");

        if ((confidencePercent[highestConfidence] * 100) == 0.0)
            System.out.println("We couldn't find a match for that one. Sorry!");

        Thread.sleep(150);

        //All other words with confidence >= float threshold except itself.
        System.out.println("\n\nOther potentially similar word(s): ");
        for (int i = 0; i < confidencePercent.length; i++)
        {
            Thread.sleep(2);
            if (confidencePercent[i] >= threshold && confidencePercent[i] != confidencePercent[highestConfidence])
                System.out.println(correctWords[i]);
        }

        //Feedback
        System.out.println("Did I make a mistake? (y/n)");
        char feedback = br.readLine().toLowerCase().charAt(0);
        if(feedback == 'y')
        {
            System.out.print("What's the actual word? ");
            String intendedCorrection = br.readLine().toLowerCase();
            for (int i = 0; i < correctWords.length; i++)
            {
                if (correctWords[i].equals(intendedCorrection))
                fr.write(word+" "+intendedCorrection+"\n");
            }
            fr.close();
            System.out.println("Thanks for letting me know!");
        }
        else if (feedback == 'n')
            System.out.println("Much appreciated!");
        fileRead.close();
    }

    private long countLines(FileReader obj)
    {
        Path filePath = Path.of("validwords.txt");

        try {
            long lineCount = Files.lines(filePath).count();
            return lineCount;
        }
        catch(IOException e) {
            System.out.println("An error occured. Please restart.");
        }
        return -1;
    }
}