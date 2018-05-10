package components.parsing;

import au.com.bytecode.opencsv.CSVReader;
import components.rhyming.Rhymer;
import components.rhyming.dictionary.PhoneticDictionary;
import main.Poem;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CSV
{
    public static ArrayList<Poem> readTrainingData(String set)
    {
        File cmuSoundList = new File("src/components/rhyming/dictionary/files/cmudict.phones.txt");
        File cmuDict = new File("src/components/rhyming/dictionary/files/cmudict.dict.txt");

        PhoneticDictionary dictionary = new PhoneticDictionary(cmuSoundList, cmuDict);
        Rhymer rhymer = new Rhymer(dictionary);
        try(Reader reader = Files.newBufferedReader(Paths.get(set));
            CSVReader csvReader = new CSVReader(reader);
        ){
            ArrayList<Poem> trainingSet = new ArrayList<>();
            int count = 0;
            String[] nextRecord;
            while((nextRecord = csvReader.readNext()) != null){
                count++;

                System.out.println(String.format("main.Poem #%d", count));
                System.out.println("Author : " + nextRecord[0]);
                System.out.println("Title : " + nextRecord[2]);
                System.out.println("Era : " + nextRecord[3]);
                System.out.println("Theme : " + nextRecord[4]);
                System.out.println("Content : " + nextRecord[1]);
                System.out.println("------------------------");

                String filename = "src/texts/poems/poem.txt";
                PrintWriter writer = new PrintWriter(filename, "UTF-8");
//                writer.println(nextRecord[0]);
//                writer.println(nextRecord[2]);
//                writer.println(nextRecord[3]);
//                writer.println(nextRecord[4]);
                writer.println(nextRecord[1]);
                writer.close();

                trainingSet.add(new Poem(filename, rhymer));
            }
            return trainingSet;
        } catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
