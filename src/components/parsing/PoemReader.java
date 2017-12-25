package components.parsing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PoemReader
{
    public static String[] readPoem(FileReader poem)
    {
        List<String> text = new ArrayList<>();
        BufferedReader poemIn = new BufferedReader(poem);
        String line;

        try{
            while((line = poemIn.readLine()) != null){
                text.add(line);
            }
        } catch(IOException oops){
            oops.printStackTrace();
        }

        return text.toArray(new String[0]);
    }

    public static String[] cleanRaw(String[] rawText)
    {
        String[] clean = new String[rawText.length];
        for(int i = 0; i < clean.length; i++){
            clean[i] = rawText[i].replaceAll(".,;:", "");
        }

        return clean;
    }
}
