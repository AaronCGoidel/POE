import components.Utils;
import components.rhyming.RhymeScheme;
import components.rhyming.Rhymer;
import components.rhyming.dictionary.Phonetics;
import components.rhyming.dictionary.RhymePattern;
import components.rhyming.dictionary.Words;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class tester
{
    public static void main(String[] args) throws IOException
    {
        File cmuSoundList = new File("src/components/rhyming/dictionary/files/cmudict.phones.txt");

        Map phonics = Phonetics.readPhonicsMap(cmuSoundList);

        File cmuDict = new File("src/components/rhyming/dictionary/files/cmudict.dict.txt");

        Map words = Words.readDictionary(cmuDict);
        System.out.println("Dictionary Read");

        Rhymer rhymer = new Rhymer(words, phonics);

        String[][] fakePoem = {{"height"}, {"sight"}, {"hello"}};
//        String[][] fakePoem = {{"monuments"}, {"rhyme"}, {"contents"},
//                {"time"}, {"overturn"}, {"masonry"}, {"burn"}, {"memory"},
//                {"enmity"}, {"room"}, {"posterity"}, {"doom"}, {"arise"}, {"eyes"}};

        RhymeScheme scheme = new RhymeScheme(fakePoem, rhymer);

        String rscheme = scheme.getScheme();

        int[] distances = new int[5];

        RhymePattern best = RhymePattern.NONE;
        int min = 100000;
        for(RhymePattern rhymePattern : RhymePattern.values()){
            int d = Utils.levenshteinDistance(rscheme, rhymePattern.pattern.substring(0, rscheme.length()));
            if(d < min){
                min = d;
                best = rhymePattern;
            }
        }
        System.out.println(best);
    }
}
