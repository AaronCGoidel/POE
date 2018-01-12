package components.theme;

import main.Poem;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class BagReader
{
    public Map<String, Integer> bagOfWords;

    public BagReader(File bagFile)
    {
        this.bagOfWords = readBag(bagFile);
    }

    private Map<String, Integer> readBag(File file)
    {
        Map<String, Integer> bagOfWords = new HashMap<>();

        BufferedReader in = null;
        try{
            in = new BufferedReader(new FileReader(file));

            for(String word = in.readLine(); word != null; word = in.readLine()){
                bagOfWords.put(word, 0);
            }
            in.close();
        } catch(IOException e){
            e.printStackTrace();
        }


        return bagOfWords;
    }

    public void mapPoem(Poem poem)
    {
        for(String[] line : poem.getCleanText()){
            for(String word : line){
                Integer count = bagOfWords.get(word);
                if(count != null){
                    bagOfWords.put(word, count + 1);
                }
            }
        }
    }

    public void revert()
    {
        for(String key : bagOfWords.keySet()){
            bagOfWords.put(key, 0);
        }
    }
}
