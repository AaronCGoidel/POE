import components.rhyming.Rhymer;
import components.rhyming.dictionary.PhoneticDictionary;

import java.io.File;
import java.io.IOException;

public class tester
{
    public static void main(String[] args) throws IOException
    {
        File cmuSoundList = new File("src/components/rhyming/dictionary/files/cmudict.phones.txt");
        File cmuDict = new File("src/components/rhyming/dictionary/files/cmudict.dict.txt");

        PhoneticDictionary dictionary = new PhoneticDictionary(cmuSoundList, cmuDict);
        Rhymer rhymer = new Rhymer(dictionary);
        Poem sonnet55 = new Poem("src/s55.txt", rhymer);

        System.out.println(sonnet55 + "\n\n");

        Poem elegy = new Poem("src/popestanzas.txt", rhymer);

        System.out.println(elegy);
    }
}
