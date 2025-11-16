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


       public void EditWrite(int id){
        try(BufferedWriter write = new BufferedWriter(new FileWriter("Data.txt"))){
            int counter = 0;
            int index = 0;
            int quantityIndex = 0;
            int itemIndex= 0;
            String Ref = Integer.toString(id);
            while(true){
                if(ref.get(index).equals(Ref)){
                    itemIndex = counter - 1;
                    quantityIndex = counter + 1;
                    System.out.println("all good here");
                    break;
                }
                counter++;
                index++;
            }

            if(ref.get(itemIndex).equals(editedName) && ref.get(quantityIndex).equals(editedQuantity)){
                System.out.println("ID ref: " + ref.get(itemIndex));
                System.out.println("Item ref: " + ref.get(quantityIndex));
            }else {
                System.out.println("woops");
            }

        }catch(IOException e){
            System.out.println(e);
        }


            System.out.println("Debug: " +  editedName);
            System.out.println("Debug: " + editedQuantity);
       }

}
