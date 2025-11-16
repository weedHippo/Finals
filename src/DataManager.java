import java.io.*;
import java.util.ArrayList;

public class DataManager {
       public void Write(int id, String item, int quantity) {
            try{
                BufferedWriter write = new BufferedWriter(new FileWriter("Data.txt", true));
                while(true){
                    write.write(item);
                    write.write("\n");
                    write.write(Integer.toString(id));
                    write.write("\n");
                    write.write(Integer.toString(quantity));
                    write.write("\n");
                    write.close();
                    System.out.println("Successfully wrote to the file.");
                }
            }catch(IOException e){
                System.out.println(e);
            }
       }

       public void EditWrite(String item, int quantity, int id) {
           ArrayList<String> ref = new ArrayList<>();
           try (BufferedReader read = new BufferedReader(new FileReader("Data.txt"))){
               while(read.readLine() != null){
                   ref.add(read.readLine());
                   System.out.println(read.readLine());
               }
           } catch (IOException e) {
               System.out.println(e.getMessage());
           }
       }

}
