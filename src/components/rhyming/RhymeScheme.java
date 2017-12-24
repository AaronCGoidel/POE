package components.rhyming;

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

    public String getScheme()
    {
        char currentSymbol = 96;
        for(int line = 0; line < text.length; line++){
            boolean found = false;
            for(int next = line + 1; next < text.length; next++){
                if(pattern.charAt(next) == '-'){
                    String[] baseLine = text[line];
                    String[] nextLine = text[next];

                    if(baseLine.equals("")){
                        pattern.setCharAt(next, ' ');
                    }else if(rhymer.isRhyme(baseLine[baseLine.length - 1], nextLine[nextLine.length - 1])){
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
}
