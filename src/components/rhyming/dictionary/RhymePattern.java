package components.rhyming.dictionary;

public enum RhymePattern
{
    COUPLETS("aabb"),
    ALTERNATE("abab"),
    ENCLOSED("abba"),
    SONNET("ababcdcdefefgg"),
    NONE("----");

    private String pattern;

    RhymePattern(String s)
    {
        this.pattern = s;
    }
}

