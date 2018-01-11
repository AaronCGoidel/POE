import components.parsing.CSV;
import components.rhyming.Rhymer;
import components.rhyming.dictionary.PhoneticDictionary;

import java.io.File;

public class tester
{
    public static void main(String[] args)
    {
        File cmuSoundList = new File("src/components/rhyming/dictionary/files/cmudict.phones.txt");
        File cmuDict = new File("src/components/rhyming/dictionary/files/cmudict.dict.txt");

        PhoneticDictionary dictionary = new PhoneticDictionary(cmuSoundList, cmuDict);
        Rhymer rhymer = new Rhymer(dictionary);
        Poem test = new Poem("src/texts/testpoem.txt", rhymer);

        //System.out.println(sonnet55 + "\n\n");

        CSV.write();

    }
}
