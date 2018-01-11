package components.rhyming;

import components.Utils;
import components.rhyming.dictionary.RhymeSchemes;

public class RhymeScheme
{
    private String[][] text;
    private StringBuilder pattern;
    private Rhymer rhymer;

    public RhymeScheme(String[][] text, Rhymer rhymer)
    {
        this.text = text;
        this.pattern = new StringBuilder(text.length);
        for(int i = 0; i < text.length; i++){ // make default pattern all dashes (dashes denote non-rhymes)
            pattern.append("-");
        }
        this.rhymer = rhymer;
    }

    /**
     * Takes the text of a poem and returns the actual rhyming pattern
     * Takes the last word from each line and finds rhyming lines later in the poem
     * @return String The rhyming pattern of the poem
     */
    private String calculatePattern()
    {
        char currentSymbol = 96; // represent first rhyming sound with the letter a
        for(int line = 0; line < text.length; line++){
            boolean found = false; // to keep track of whether to switch to next letter
            for(int next = line + 1; next < text.length; next++){
                if(pattern.charAt(next) == '-'){
                    String[] baseLine = text[line];
                    String[] nextLine = text[next];

                    if(rhymer.isRhyme(baseLine[baseLine.length - 1], nextLine[nextLine.length - 1])){
                        if(!found){ // if this rhyme is new then increase to next letter
                            found = true;
                            currentSymbol++;
                        }
                        pattern.setCharAt(next, currentSymbol);
                        pattern.setCharAt(line, currentSymbol);
                    }
                }
            }
        }
        return pattern.toString();
    }

    /**
     * Returns the best estimate of the type of rhyme scheme based on the pattern of the rhyming lines
     * Takes the Levenshtein distance between each rhyme scheme and the pattern from the poem
     * Selects the closest match as the form of the poem
     * @return RhymeSchemes The closest form to the pattern found in the poem
     */
    public RhymeSchemes estimateScheme()
    {
        String actualPattern = calculatePattern();
        //System.out.println(actualPattern);

        RhymeSchemes best = RhymeSchemes.NONE; // defaults to no scheme
        int min = Integer.MAX_VALUE; // set the initial minimum distance to something absurdly high
        for(RhymeSchemes rhymePattern : RhymeSchemes.values()){ // iterate through the rhyme schemes
            // calculate difference between actual pattern from the poem and the pattern of the rhyme scheme
            int d = Utils.levenshteinDistance(actualPattern, rhymePattern.pattern);
            if(d < min){ // check if this distance is closer than the previous best
                min = d;
                best = rhymePattern;
            }
        }
        return best;
    }
}
