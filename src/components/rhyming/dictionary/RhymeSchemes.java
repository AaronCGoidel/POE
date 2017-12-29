package components.rhyming.dictionary;

public enum RhymeSchemes
{
    COUPLET("aabbccddeeffgg"),
    ALTERNATE("ababcdcdefefghgh"),
    ENCLOSED("abbacddceffe"),
    SONNET("ababcdcdefefgg"),
    NONE("--------");

    public String pattern;

    RhymeSchemes(String s)
    {
        this.pattern = s;
    }
}

