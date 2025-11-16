import java.io.*;
import java.util.ArrayList;

abstract class DataManager {
    ArrayList<String> ref = new ArrayList<>();
    String temp;
    String temp2;
    String editedName;
    String editedQuantity;

    String File_Path = "Data.txt";

    private static final int BLOCK_SIZE = 3;
    private static final int ITEM_INDEX = 0;
    private static final int ID_INDEX = 1;
    private static final int QUANTITY_INDEX = 2;



    public void Write(int id, String item, int quantity) {
            try{
                BufferedWriter write = new BufferedWriter(new FileWriter(File_Path, true));
                write.write(item);
                write.write("\n");
                write.write(Integer.toString(id));
                write.write("\n");
                write.write(Integer.toString(quantity));
                write.write("\n");
                write.close();
                System.out.println("Successfully wrote to the file.");
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
       }

       public void EditLoad(String item, int quantity, int id) {
           String ProdName =  item;
           String NewQuantity = Integer.toString(quantity);

           try (BufferedReader read = new BufferedReader(new FileReader(File_Path))) {
               int counter = 0;
               int index = 0;
               while((temp = read.readLine()) != null){
                   ref.add(temp);
               }

               while(true){
                   temp2 = Integer.toString(id);
                   if(ref.get(index).equals(temp2)){
                       int itemIndex = counter - ID_INDEX;
                       int quantityIndex = counter + ITEM_INDEX;

                       System.out.println("ID found: " + ref.get(index));
                       System.out.println("Item found: " + ref.get(itemIndex));
                       System.out.println("Qauntity found: " + ref.get(quantityIndex));

                       ref.set(itemIndex, ProdName);
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

        try (BufferedWriter write = new BufferedWriter(new FileWriter(File_Path))) { // overwrite
            for (int i = ITEM_INDEX; i < ref.size(); i += BLOCK_SIZE) { // step by 3 lines (item, id, quantity)
                String currentID = ref.get(i + ID_INDEX);
                if (currentID.equals(Ref)) {

                    write.write(editedName);
                    write.newLine();
                    write.write(Ref);
                    write.newLine();
                    write.write(editedQuantity);
                    write.newLine();
                } else {
                    write.write(ref.get(i));
                    write.newLine();
                    write.write(ref.get(i + ID_INDEX));
                    write.newLine();
                    write.write(ref.get(i + QUANTITY_INDEX));
                    write.newLine();
                }
            }
            System.out.println("Successfully updated the file.");
        } catch (IOException e) {
            System.out.println(e);
        }
    }


}
