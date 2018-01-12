package components.theme.neural_net;

import au.com.bytecode.opencsv.CSVReader;
import components.rhyming.Rhymer;
import components.rhyming.dictionary.PhoneticDictionary;
import components.theme.BagReader;
import main.Poem;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ThemeNet
{
    public static void main(String[] args) throws FileNotFoundException
    {
        File cmuSoundList = new File("src/components/rhyming/dictionary/files/cmudict.phones.txt");
        File cmuDict = new File("src/components/rhyming/dictionary/files/cmudict.dict.txt");

        PhoneticDictionary dictionary = new PhoneticDictionary(cmuSoundList, cmuDict);
        Rhymer rhymer = new Rhymer(dictionary);

        BagReader bag = new BagReader(new File("src/components/theme/files/bag_of_words.txt"));

        int[] layout = {bag.bagOfWords.size(), 20000, 10000, 5000, 1000, 500, 500, 100, 10, 3};
        Network ThemeNetwork = new Network(layout);
        System.out.println("Initialized Network");
        Neuron.rate = 0.09;
        Neuron.momentum = 0.015;

        boolean trained = false;

        int[] inputs = new int[bag.bagOfWords.size()];

        double[] outputs = {0, 0, 0};

        while(!trained){
            double err = 0d;
            try(Reader reader = Files.newBufferedReader(Paths.get("src/texts/all.csv.txt"));
                CSVReader csvReader = new CSVReader(reader);
            ){
                int answer = 0;
                String[] nextRecord;
                while((nextRecord = csvReader.readNext()) != null){
                    if(nextRecord[4].equalsIgnoreCase("Mythology & Folklore")){
                        answer = 1;
                    }else if(nextRecord[4].equalsIgnoreCase("Nature")){
                        answer = 2;
                    }else if(nextRecord[4].equalsIgnoreCase("Love")){
                        answer = 0;
                    }
                    for(int i = 0; i < outputs.length; i++){
                        if(i == answer){
                            outputs[i] = 1;
                        }else {
                            outputs[i] = 0;
                        }
                    }
                    PrintWriter writer = new PrintWriter("src/texts/poems/poem.txt", "UTF-8");
                    writer.println(nextRecord[1]);
                    writer.close();
                    Poem poem = new Poem("src/texts/poems/poem.txt", rhymer);

                    bag.mapPoem(poem);

                    int i = 0;
                    for(String key : bag.bagOfWords.keySet()){
                        inputs[i] = bag.bagOfWords.get(key);
                        i++;
                    }
                    ThemeNetwork.setInputValues(inputs);
                    ThemeNetwork.advance();
                    ThemeNetwork.backProp(outputs);

                    err += ThemeNetwork.calculateNetworkCost(outputs);

                    bag.revert();

                    System.out.println("[Cost] " + err);
                    if(err < 0.01){
                        System.out.println("System Trained");
                        trained = true;
                    }
                }
            } catch(IOException e){
                e.printStackTrace();
            }

        }


        ThemeNetwork.setInputValues(inputs);
        ThemeNetwork.advance();
        System.out.println(ThemeNetwork.getDecision());
    }
}
