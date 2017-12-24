import components.rhyming.dictionary.RhymeScheme;

public class Poem
{
    String rawText;
    String[] lines;
    int numLines;
    int numStanza;
    RhymeScheme rhymeScheme;
    Stresses meter;

    public Poem(String text)
    {
        this.rawText = text;
    }
}
