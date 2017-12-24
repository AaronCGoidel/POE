package components.rhyming.dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Words
{
    public static Map<String, String[]> readDictionary(File cmuDict) throws IOException
    {
        Map<String, String[]> phoneticDictionary = new HashMap<>();

        BufferedReader in = new BufferedReader(new FileReader(cmuDict));

        for(String entry = in.readLine(); entry != null; entry = in.readLine()){
            String[] tokenizedLine = entry.split(" "); // splits line on space

            // adds entry to map which is the original string mapped to the phonetic string as a list of sounds
            phoneticDictionary.put(tokenizedLine[0], Arrays.copyOfRange(tokenizedLine, 1, tokenizedLine.length));

        }
        in.close();

        return phoneticDictionary;
    }
}
