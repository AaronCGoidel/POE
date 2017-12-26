import components.rhyming.Rhymer;
import components.rhyming.dictionary.Phonetics;
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

        Poem test = new Poem("src/s55.txt", rhymer);

        System.out.println(test.getRhymeScheme());
    }
}
