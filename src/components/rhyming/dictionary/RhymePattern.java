package components.rhyming.dictionary;

public enum RhymePattern
{
    COUPLET("aabbccddeeffgg"),
    ALTERNATE("ababcdcdefefghgh"),
    ENCLOSED("abbacddceffe"),
    SONNET("ababcdcdefefgg"),
    NONE("--------");

    public String pattern;

    RhymePattern(String s)
    {
        this.pattern = s;
    }
}

