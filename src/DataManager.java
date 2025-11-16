import java.io.*;
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

}
