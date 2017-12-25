import components.parsing.PoemReader;
import components.rhyming.dictionary.RhymePattern;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Poem
{
    private String[] rawText;
    private String[] cleanText;
    private int numLines;
    private int numStanza;
    private RhymePattern rhymeScheme;
    private Stresses meter;

    public Poem(String poemPath)
    {
        try{
            this.rawText = PoemReader.readPoem(new FileReader(poemPath));
        } catch(FileNotFoundException oops){
            System.out.println("Poem Not Found");
        }
        this.cleanText =  PoemReader.cleanRaw(rawText);
        this.numLines = rawText.length;
    }
}
