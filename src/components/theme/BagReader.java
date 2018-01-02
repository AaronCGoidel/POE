package components.theme;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class BagReader
{
    public Map<String, Integer> bagOfWords;

    public BagReader(File bagFile)
    {
        try{
            this.bagOfWords = readBag(bagFile);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private Map<String, Integer> readBag(File file) throws IOException
    {
        Map<String, Integer> bagOfWords = new HashMap<>();

        BufferedReader in = new BufferedReader(new FileReader(file));

        for(String word = in.readLine(); word != null; word = in.readLine()){
            bagOfWords.put(word, 0);
        }
        in.close();

        return bagOfWords;
    }
}
