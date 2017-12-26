import components.parsing.PoemReader;
import components.rhyming.dictionary.RhymePattern;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Poem
{
    private String[] rawText;
    private String[][] cleanText;
    private int numLines;
    private int numStanza;
    private RhymePattern rhymeScheme;
    private Stresses meter;

    public Poem(String poemPath)
    {
        // read in poem
        try{
            this.rawText = PoemReader.readPoem(new FileReader(poemPath));
        } catch(FileNotFoundException oops){
            System.out.println("Poem Not Found");
        }
        this.cleanText = PoemReader.cleanRaw(rawText); // clean and tokenize into words
        this.numLines = countLines();
        this.numStanza = countStanzas();
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

    public RhymePattern getRhymeScheme()
    {
        return rhymeScheme;
    }

    public Stresses getMeter()
    {
        return meter;
    }
}
