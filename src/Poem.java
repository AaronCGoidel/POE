import components.rhyming.dictionary.RhymePattern;

public class Poem
{
    String rawText;
    String[] lines;
    int numLines;
    int numStanza;
    RhymePattern rhymeScheme;
    Stresses meter;

    public Poem(String text)
    {
        this.rawText = text;
    }
}
