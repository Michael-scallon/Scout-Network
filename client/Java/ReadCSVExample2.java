package Java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;



public class ReadCSVExample2 {
    public static void main(String[] args) {
       String file = "Java/resources/football.csv";
       String headers;
       String line;
       List test;
       
        try (BufferedReader br =
                     new BufferedReader(new FileReader(file))) {
            headers = br.readLine();
            

            while((line = br.readLine()) != null){
                System.out.println(line);
                line.split(",");
                System.out.println("############################");
                System.out.println("############################");
                String[] name = null;  
                name = line.split(",");
                // System.out.println(name[0]);
                get_name(name);
            }
        } catch (Exception e){
            System.out.println(e);
        }

    }

    public static String get_name(String[] name) {
        return "true";
    }
}