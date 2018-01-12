import components.parsing.CSV;

import java.io.IOException;

public class tester
{
    public static void main(String[] args) throws IOException
    {
//        File cmuSoundList = new File("src/components/rhyming/dictionary/files/cmudict.phones.txt");
//        File cmuDict = new File("src/components/rhyming/dictionary/files/cmudict.dict.txt");
//
//        PhoneticDictionary dictionary = new PhoneticDictionary(cmuSoundList, cmuDict);
//        Rhymer rhymer = new Rhymer(dictionary);
//
//
//        Poem test = new Poem("src/texts/testpoem.txt", rhymer);
//
//        System.out.println(test + "\n\n");
//
//        BagReader bag = new BagReader(new File("src/components/theme/files/bag_of_words.txt"));
//
//        bag.mapPoem(test);
//
//        bag.bagOfWords.forEach((key, value) -> System.out.print(value > 0 ? key + ":" + value + "\n" : ""));
        CSV.write();
    }
}
