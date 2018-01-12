package components.parsing;

import au.com.bytecode.opencsv.CSVReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSV
{
    public static void write()
    {
        try(Reader reader = Files.newBufferedReader(Paths.get("src/texts/all.csv.txt"));
            CSVReader csvReader = new CSVReader(reader);
        ){
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

                String filename = String.format("src/texts/poems/poem%d.txt", count);
                PrintWriter writer = new PrintWriter(filename, "UTF-8");
//                writer.println(nextRecord[0]);
//                writer.println(nextRecord[2]);
//                writer.println(nextRecord[3]);
                //writer.println(nextRecord[4]);
                writer.println(nextRecord[1]);
                writer.close();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

}
