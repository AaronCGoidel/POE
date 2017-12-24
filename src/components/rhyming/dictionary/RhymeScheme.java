package components.rhyming.dictionary;

public enum RhymeScheme
{
    COUPLETS("aabb"),
    ALTERNATE("abab"),
    ENCLOSED("abba"),
    SONNET("ababcdcdefefgg"),
    NONE("----");

    private String pattern;

    RhymeScheme(String s)
    {
        this.pattern = s;
    }
}

