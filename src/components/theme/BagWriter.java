package components.theme;

import components.parsing.PoemReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BagWriter
{
    public static void main(String[] args) throws IOException
    {
        File folder = new File("src/texts/");
        File[] listOfFiles = folder.listFiles();

        FileReader in;


        ArrayList<String> list = new ArrayList<>();
        Scanner s = new Scanner(new File("src/components/theme/files/bag_of_words.txt"));
        while (s.hasNext()){
            list.add(s.next());
        }
        s.close();


        String[][] words;
        for(File file : listOfFiles){
            if(file.isFile()){
                in = new FileReader(file);
                words = PoemReader.cleanRaw(PoemReader.readPoem(in));
                for(String[] line : words){
                    for(String str : line){
                        if(!list.contains(str)){
                            list.add(str);
                        }
                    }
                }
            }
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(
                "src/components/theme/files/bag_of_words.txt"));
        for(String word : list){
            writer.write(word);
            writer.newLine();
        }
        writer.close();
    }
}
