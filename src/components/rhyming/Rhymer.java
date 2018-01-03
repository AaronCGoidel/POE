package components.rhyming;

import components.rhyming.dictionary.Phonemes;
import components.rhyming.dictionary.PhoneticDictionary;

public class Rhymer
{
    private PhoneticDictionary dictionary;
    private int depth;

    /*
    Constructor with default depth
     */
    public Rhymer(PhoneticDictionary dictionary)
    {
        this.dictionary = dictionary;
        this.depth = 3;
    }

    public Rhymer(PhoneticDictionary dictionary, int depth)
    {
        this.dictionary = dictionary;
        this.depth = depth;
    }

    public boolean isRhyme(String base, String compare)
    {
        // get phonetic breakdowns from components.rhyming.dictionary
        String[] pronunciationOne = dictionary.words.get(base.toLowerCase());
        String[] pronunciationTwo = dictionary.words.get(compare.toLowerCase());
        if(pronunciationOne == null){
            System.out.println(base);
            return false;
        }
        if(pronunciationTwo == null){
            System.out.println(compare);
            return false;
        }

        /* shorten depth of search for small words */
        // word one
        int vowelCount = 0;
        for(String sound : pronunciationOne){ // iterate over sounds
            if(dictionary.phonemes.get(sound.length() < 2 ? sound : sound.substring(0, 2)) == Phonemes.VOWEL){
                vowelCount++; // count the number of vowels found
            }
        }
        // same thing for word two
        for(String sound : pronunciationTwo){
            if(dictionary.phonemes.get(sound.length() < 2 ? sound : sound.substring(0, 2)) == Phonemes.VOWEL){
                vowelCount++;
            }
        }

        if(vowelCount <= 3){
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
