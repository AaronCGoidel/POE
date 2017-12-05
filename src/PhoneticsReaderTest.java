import dictionary.Phonetics;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class PhoneticsReaderTest
{
    public static void main(String[] args) throws IOException
    {
        File cmuSoundList = new File("src/dictionary/files/cmudict.phones.txt");

        Map phonics = Phonetics.readPhonicsMap(cmuSoundList);

        phonics.forEach((key, value) -> System.out.println(key + " -> " + value)); // pretty print

        System.out.println("DONE");
    }
}
