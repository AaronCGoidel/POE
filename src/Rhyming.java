import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Rhyming
{
    public static ArrayList<String> findRhymes(String word, int depth, Map<String, String[]> dict)
    {
        ArrayList<String> matches = new ArrayList();

        String[] inPronunciation = dict.get(word);
        String[] inEnding = Arrays.copyOfRange(inPronunciation,
                inPronunciation.length - depth, inPronunciation.length);

        //System.out.println(Arrays.toString(inEnding));

        for(String key : dict.keySet()){
            String[] checkPronunciation = dict.get(key);

            try{
                String[] checkEnding = Arrays.copyOfRange(checkPronunciation,
                        checkPronunciation.length - depth, checkPronunciation.length);
                //System.out.println(Arrays.toString(checkEnding));

                if(Arrays.equals(inEnding, checkEnding)){
                    //System.out.println(key);
                    matches.add(key);
                }
            }catch(ArrayIndexOutOfBoundsException oops){
                //System.out.println("word too short");
            }
        }

        //System.out.println(matches.size());
        return matches;
    }

    public static boolean isRhyme(String base, String compare, int depth, Map<String, String[]> dict)
    {
        ArrayList rhymes = findRhymes(base, depth, dict);
        return rhymes.contains(compare);
    }
}
