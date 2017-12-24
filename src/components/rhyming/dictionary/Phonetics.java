package components.rhyming.dictionary;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Phonetics
{
    public static Map<String, SoundClass> readPhonicsMap(File cmuPhones) throws IOException
    {
        Map<String, SoundClass> sounds = new HashMap<>();

        BufferedReader in = new BufferedReader(new FileReader(cmuPhones));

        for(String entry = in.readLine(); entry != null; entry = in.readLine()){
            String[] tokenizedLine = entry.split("\t"); // splits line on tab

            // maps phonetic token to phonetic classification
            sounds.put(tokenizedLine[0], SoundClass.valueOf(tokenizedLine[1].toUpperCase()));
        }

        in.close();

        return sounds;
    }
}