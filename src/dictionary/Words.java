package dictionary;

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
            String[] tokenizedLine = entry.split(" "); // splits line on tab so that element

            phoneticDictionary.put(tokenizedLine[0], Arrays.copyOfRange(tokenizedLine, 1, tokenizedLine.length)); // maps phonetic token to phonetic classification

        }
        in.close();

        return phoneticDictionary;
    }
}
