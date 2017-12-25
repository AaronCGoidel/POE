import components.parsing.PoemReader;
import components.rhyming.Rhymer;
import components.rhyming.dictionary.Phonetics;
import components.rhyming.dictionary.Words;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class PhoneticsReaderTest
{
    public static void main(String[] args) throws IOException
    {
        File cmuSoundList = new File("src/components/rhyming/dictionary/files/cmudict.phones.txt");

        Map phonics = Phonetics.readPhonicsMap(cmuSoundList);

        //phonics.forEach((key, value) -> System.out.println(key + " -> " + value)); // pretty print

        File cmuDict = new File("src/components/rhyming/dictionary/files/cmudict.dict.txt");

        Map words = Words.readDictionary(cmuDict);
        System.out.println("Dictionary Read");

        //words.forEach((key, value) -> System.out.println(key + " -> " + Arrays.toString((String[]) value))); // pretty print

//        System.out.println(Arrays.toString((String[]) words.get("lay")));
//        System.out.println(Arrays.toString((String[]) words.get("obey")));

        Rhymer rhymer = new Rhymer(words, phonics);

        Scanner in = new Scanner(System.in);

//        System.out.println("Enter Word One: ");
//        String wordOne = in.next();
//        System.out.println("Enter Word Two: ");
//        String wordTwo = in.next();
//        System.out.println(rhymer.isRhyme(wordOne, wordTwo));

        System.out.println(Arrays.toString(PoemReader.readPoem(new FileReader("src/s55.txt"))));
    }
}
