import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Rhyming
{
    Map<String, String[]> dict;

    public Rhyming(Map<String, String[]> dict)
    {
        this.dict = dict;
    }

    private ArrayList<String> findRhymes(String word, int depth)
    {
        ArrayList<String> matches = new ArrayList();

        String[] inPronunciation = dict.get(word); // get pronunciation from phonetic dictionary
        String[] inEnding = Arrays.copyOfRange(inPronunciation,
                inPronunciation.length - depth, inPronunciation.length); // get the ending of the pronunciation to depth


        for(String key : dict.keySet()){ // iterate over dictionary
            String[] checkPronunciation = dict.get(key);

            try{
                String[] checkEnding = Arrays.copyOfRange(checkPronunciation,
                        checkPronunciation.length - depth, checkPronunciation.length); // get the ending of the current word

                if(Arrays.equals(inEnding, checkEnding)){ // if the endings are the same add current word to list of rhymes
                    matches.add(key);
                }
            } catch(ArrayIndexOutOfBoundsException oops){ // if word is shorter than depth
            }
        }

        return matches;
    }

    public boolean isRhyme(String base, String compare, int depth)
    {
        ArrayList rhymes = findRhymes(base, depth);
        return rhymes.contains(compare);
    }
}
