import dictionary.Phonetics;
import dictionary.Words;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class PhoneticsReaderTest
{
    public static void main(String[] args) throws IOException
    {
        File cmuSoundList = new File("src/dictionary/files/cmudict.phones.txt");

        Map phonics = Phonetics.readPhonicsMap(cmuSoundList);

        //phonics.forEach((key, value) -> System.out.println(key + " -> " + value)); // pretty print

        File cmuDict = new File("src/dictionary/files/cmudict.dict.txt");

        Map words = Words.readDictionary(cmuDict);
        System.out.println("Dictionary Read");

        //words.forEach((key, value) -> System.out.println(key + " -> " + Arrays.toString((String[]) value))); // pretty print

//        System.out.println(Arrays.toString((String[]) words.get("lay")));
//        System.out.println(Arrays.toString((String[]) words.get("obey")));

        //Rhyming.findRhymes("sight", 2, words);
        Rhyming rhymer = new Rhyming(words);
        System.out.println(rhymer.isRhyme("thee", "me", 1));

    }
}
