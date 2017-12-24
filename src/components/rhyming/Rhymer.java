package components.rhyming;

import components.rhyming.dictionary.SoundClass;

import java.util.Map;

public class Rhymer
{
    Map<String, String[]> dict;
    Map<String, SoundClass> phonetics;
    int depth;

    /*
    Constructor with default depth
     */
    public Rhymer(Map<String, String[]> dict, Map<String, SoundClass> phonetics)
    {
        this.dict = dict;
        this.phonetics = phonetics;
        this.depth = 3;
    }

    public Rhymer(Map<String, String[]> dict, Map<String, SoundClass> phonetics, int depth)
    {
        this.dict = dict;
        this.phonetics = phonetics;
        this.depth = depth;
    }

    public boolean isRhyme(String base, String compare)
    {
        // get phonetic breakdowns from components.rhyming.dictionary
        String[] pronunciationOne = dict.get(base);
        String[] pronunciationTwo = dict.get(compare);

        // shorten depth of search for small words
        int vowelCount = 0;
        for(String sound : pronunciationOne){ // iterate over sounds
            if(phonetics.get(sound.length() < 2 ? sound : sound.substring(0, 2)) == SoundClass.VOWEL){
                vowelCount++; // count the number of vowels found
            }
        }
        if(vowelCount < 3){
            depth = 2;
        }
        for(int i = pronunciationOne.length - 1, j = pronunciationTwo.length - 1;
            (i > (pronunciationOne.length - depth)) && (j > (pronunciationTwo.length - depth)); i--, j--){
            if(!pronunciationOne[i].equals(pronunciationTwo[j])){ // compare the pronunciations from the end to the beginning in the range
                return false;
            }
        }
        return true;
    }
}
