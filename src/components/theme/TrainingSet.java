package components.theme;

import components.parsing.CSV;
import main.Poem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TrainingSet
{
    public ArrayList<Poem> poemSet;
    private Map<String, Integer> classes;

    public TrainingSet()
    {
        classes = new HashMap<>();
        this.poemSet = CSV.readTrainingData("src/texts/all.csv.txt"); //TODO: make filepath param
        for(Poem p : poemSet){
            if(classes.containsKey(p.getTheme())){
                classes.put(p.getTheme(), classes.get(p.getTheme()) + 1);
            }else{
                classes.put(p.getTheme(), 1);
            }
        }
    }

    public int getNumOfKey(String key)
    {
        return classes.get(key);
    }

    public int getNumEntries()
    {
        return classes.size();
    }
}
