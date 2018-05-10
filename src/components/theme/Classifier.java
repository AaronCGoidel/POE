package components.theme;

import main.Poem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Classifier
{
    private String[] classes;
    private Map<String, String> classified;

    public Classifier(String[] classes)
    {
        this.classes = classes;
        this.classified = new HashMap<>();
    }

    private void train(String model, String trainingSet)
    {
        TrainingSet dataSet = new TrainingSet();
        for(String c : classes){

        }
    }

    private String concatenatePoems(TrainingSet dataSet, String key)
    {
        StringBuilder text = new StringBuilder();
        for(Poem p : dataSet.poemSet){
            text.append(Arrays.toString(p.getWords()));
        }
        return text.toString();
    }

    private Map<String, Integer> wordsInPoem(String[][] words)
    {
        Map<String, Integer> wordsInPoem = new HashMap<>();

        for(String[] line : words){
            for(String word : line){
                if(wordsInPoem.containsKey(word)){
                    wordsInPoem.put(word, wordsInPoem.get(word) + 1);
                }else{
                    wordsInPoem.put(word, 1);
                }
            }
        }
        return wordsInPoem;
    }
}
