package components.rhyming.dictionary;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PhoneticDictionary
{
    public Map<String, Phonemes> phonemes;
    public Map<String, String[]> words;

    public PhoneticDictionary(File phonesFile, File dictFile)
    {
        try{
            this.phonemes = readPhonicsMap(phonesFile);
            this.words = readDictionary(dictFile);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private static Map<String, Phonemes> readPhonicsMap(File phonesFile) throws IOException
    {
        Map<String, Phonemes> sounds = new HashMap<>();

        BufferedReader in = new BufferedReader(new FileReader(phonesFile));

        for(String entry = in.readLine(); entry != null; entry = in.readLine()){
            String[] tokenizedLine = entry.split("\t"); // splits line on tab

            // maps phonetic token to phonetic classification
            sounds.put(tokenizedLine[0], Phonemes.valueOf(tokenizedLine[1].toUpperCase()));
        }

        in.close();

        return sounds;
    }

    private static Map<String, String[]> readDictionary(File dictFile) throws IOException
    {
        Map<String, String[]> phoneticDictionary = new HashMap<>();

        BufferedReader in = new BufferedReader(new FileReader(dictFile));

        for(String entry = in.readLine(); entry != null; entry = in.readLine()){
            String[] tokenizedLine = entry.split(" "); // splits line on space

            // adds entry to map which is the original string mapped to the phonetic string as a list of sounds
            phoneticDictionary.put(tokenizedLine[0], Arrays.copyOfRange(tokenizedLine, 1, tokenizedLine.length));

        }
        in.close();

        return phoneticDictionary;
    }
}
