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

    public static String[][] cleanRaw(String[] rawText)
    {
        List<String[]> clean = new ArrayList<>();
        for(String line : rawText){
            clean.add(line.replaceAll("[.,;:]", "").split(" "));
        }

        return clean.toArray(new String[0][0]);
    }
}
