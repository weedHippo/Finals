import java.io.*;
import java.util.ArrayList;

public class DataManager {
    ArrayList<String> ref = new ArrayList<>();
    String temp;
    String temp2;
    String editedName;
    String editedQuantity;

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

       public void EditLoad(String item, int quantity, int id) {
           String NewName =  item;
           String NewQuantity = Integer.toString(quantity);

           try (BufferedReader read = new BufferedReader(new FileReader("Data.txt"))){
               int counter = 0;
               int index = 0;
               while((temp = read.readLine()) != null){
                   ref.add(temp);
               }

               while(true){
                   temp2 = Integer.toString(id);
                   if(ref.get(index).equals(temp2)){
                       int itemIndex = counter - 1;
                       int quantityIndex = counter + 1;

                       System.out.println("ID found: " + ref.get(index));
                       System.out.println("Item: " + ref.get(itemIndex));
                       System.out.println("Qauntity: " + ref.get(quantityIndex));

                       ref.set(itemIndex, NewName);
                       ref.set(quantityIndex, NewQuantity);

                       editedName = ref.get(itemIndex);
                       editedQuantity =  ref.get(quantityIndex);
                       break;
                   }
                   counter++;
                   index++;
               }

           } catch (IOException e) {
               System.out.println(e.getMessage());
           }
       }


    public void EditWrite(int id) {
        String Ref = Integer.toString(id);

        try (BufferedWriter write = new BufferedWriter(new FileWriter("Data.txt"))) { // overwrite
            for (int i = 0; i < ref.size(); i += 3) { // step by 3 lines (item, id, quantity)
                String currentID = ref.get(i + 1);
                if (currentID.equals(Ref)) {
                    // write edited block
                    write.write(editedName);
                    write.newLine();
                    write.write(Ref);
                    write.newLine();
                    write.write(editedQuantity);
                    write.newLine();
                } else {
                    // write existing block
                    write.write(ref.get(i));
                    write.newLine();
                    write.write(ref.get(i + 1));
                    write.newLine();
                    write.write(ref.get(i + 2));
                    write.newLine();
                }
            }
            System.out.println("Successfully updated the file.");
        } catch (IOException e) {
            System.out.println(e);
        }
    }


}
