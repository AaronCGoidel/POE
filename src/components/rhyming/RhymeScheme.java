package components.rhyming;

import components.Utils;
import components.rhyming.dictionary.RhymePattern;

public class RhymeScheme
{
    private String[][] text;
    private StringBuilder pattern;
    private Rhymer rhymer;

    public RhymeScheme(String[][] text, Rhymer rhymer)
    {
        this.text = text;
        this.pattern = new StringBuilder(text.length);
        for(int i = 0; i < text.length; i++){
            pattern.append("-");
        }
        this.rhymer = rhymer;
    }

    public String calculatePattern()
    {
        char currentSymbol = 96;
        for(int line = 0; line < text.length; line++){
            boolean found = false;
            for(int next = line + 1; next < text.length; next++){
                if(pattern.charAt(next) == '-'){
                    String[] baseLine = text[line];
                    String[] nextLine = text[next];

                    if(rhymer.isRhyme(baseLine[baseLine.length - 1], nextLine[nextLine.length - 1])){
                        if(!found){
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

    public RhymePattern estimateScheme()
    {
        String actualPattern = calculatePattern();

        RhymePattern best = RhymePattern.NONE;
        int min = Integer.MAX_VALUE;
        for(RhymePattern rhymePattern : RhymePattern.values()){
            int d = Utils.levenshteinDistance(actualPattern, rhymePattern.pattern);
            if(d < min){
                min = d;
                best = rhymePattern;
            }
        }
        return best;
    }
}
