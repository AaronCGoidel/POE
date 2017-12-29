import components.parsing.PoemReader;
import components.rhyming.RhymeScheme;
import components.rhyming.Rhymer;
import components.rhyming.dictionary.RhymeSchemes;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Poem
{
    private String[] rawText;
    private String[][] cleanText;
    private int numLines;
    private int numStanza;
    private RhymeSchemes rhymeScheme;
    private Stresses meter;
    RhymeScheme rhymeFinder;

    public Poem(String poemPath, Rhymer rhymer)
    {
        // read in poem
        try{
            this.rawText = PoemReader.readPoem(new FileReader(poemPath));
        } catch(FileNotFoundException oops){
            System.out.println("Poem Not Found");
        }
        this.cleanText = PoemReader.cleanRaw(rawText); // clean and tokenize into words

        RhymeScheme rhymeFinder = new RhymeScheme(cleanText, rhymer);

        this.numLines = countLines();
        this.numStanza = countStanzas();
        this.rhymeScheme = rhymeFinder.estimateScheme();
    }

    /**
     * Counts the number of lines in the poem
     * Ignores blank lines
     * @return int Line count
     */
    private int countLines()
    {
        int count = 0;
        for(String line : rawText){
            if(!line.equals("")){ // only increase line count if the line is not empty
                count++;
            }
        }
        return count;
    }

    /**
     * Counts the number of stanzas in the poem
     * Increments count every time there is a line skip
     * @return int Number of stanzas in the poem
     */
    private int countStanzas()
    {
        int count = 1;
        for(String line : rawText){
            if(line.equals("")){
                count++;
            }
        }
        return count;
    }

    public String[] getRawText()
    {
        return rawText;
    }

    public String[][] getCleanText()
    {
        return cleanText;
    }

    public int getNumLines()
    {
        return numLines;
    }

    public int getNumStanza()
    {
        return numStanza;
    }

    public RhymeSchemes getRhymeScheme()
    {
        return rhymeScheme;
    }

    public Stresses getMeter()
    {
        return meter;
    }
}
