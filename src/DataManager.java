import java.io.*;
import java.util.ArrayList;

abstract class DataManager {
    ArrayList<String> ref = new ArrayList<>();
    String currentLine;
    String targetIDString;
    String editedName;
    String editedQuantity;

    String filePath = "Data.txt";

    private static final int BLOCK_SIZE = 3;
    private static final int ITEM_INDEX = 0;
    private static final int ID_INDEX = 1;
    private static final int QUANTITY_INDEX = 1;
    private static final int REF_Quan_INDEX = 2;

    String empty_quan = "0";
    String empty_ID = "0000";
    String empty_NAME = "Empty";

    boolean emptyBlock = false;

    int Temp_ID;

    public void Add_Load(int Id, String Name, int Quantity) {
        try( BufferedReader ADD_LOAD = new BufferedReader(new FileReader(filePath))){
            int index = 0;
            int counter = 0;

            String FinalName;
            String FinalQuantity;
            String FinalID;

            String newName = Name;
            String newQuantity = Integer.toString(Quantity);
            String newID = Integer.toString(Id);

            ref.clear();
            while ((currentLine = ADD_LOAD.readLine()) != null) {
                ref.add(currentLine);
            }

            System.out.println("Loaded");

        //here for debug purposes, or maybe might stay?
        while (true){
            if(ref.get(index).equals(empty_quan) || ref.get(index).equals(empty_ID)){
                System.out.println("the empty block is found");
                emptyBlock = true;

                int ITEM_INDEX = counter  - ID_INDEX; //16
                int ID_INDEX = index;
                int QUAN_IDX = counter + QUANTITY_INDEX;

                ref.set(ID_INDEX, newID);
                ref.set(QUAN_IDX, newQuantity);
                ref.set(ITEM_INDEX, newName);


                FinalName = ref.get(ID_INDEX);
                FinalQuantity = ref.get(QUAN_IDX);
                FinalID = ref.get(ID_INDEX);

                Add_Write(FinalName, FinalQuantity, FinalID);

                break;
            }else{
                String Append_item = Name;
                String Append_quantity = Integer.toString(Quantity);
                String Append_ID = Integer.toString(Id);
                Add_Write(Append_item, Append_quantity, Append_ID);
            }


            index ++;
            counter ++;


        }

        }catch(IOException e){
            System.out.println("Error in Add Load");
        }
    }



    public void Add_Write(String id, String item, String quantity) {
        //Disabled: For Reference.
        /*try {
            BufferedWriter write = new BufferedWriter(new FileWriter(filePath, true));
            write.write(item);
            write.newLine();
            write.write(Integer.toString(id));
            write.newLine();
            write.write(Integer.toString(quantity));
            write.newLine();
            write.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
         */
        try{
            int index = 0;

            if(emptyBlock){
                BufferedWriter ADD_WRITE_OW = new BufferedWriter(new FileWriter(filePath));
                for (int i = ITEM_INDEX; i < ref.size(); i += BLOCK_SIZE){
                    String currentID = ref.get(i + ID_INDEX);
                    if (currentID.equals(empty_ID)) {

                        ADD_WRITE_OW.write(item);
                        ADD_WRITE_OW.newLine();
                        ADD_WRITE_OW.write(id);
                        ADD_WRITE_OW.newLine();
                        ADD_WRITE_OW.write(quantity);
                        ADD_WRITE_OW.newLine();
                    } else {
                        ADD_WRITE_OW.write(ref.get(i));
                        ADD_WRITE_OW.newLine();
                        ADD_WRITE_OW.write(ref.get(i + ID_INDEX));
                        ADD_WRITE_OW.newLine();
                        ADD_WRITE_OW.write(ref.get(i + REF_Quan_INDEX));
                        ADD_WRITE_OW.newLine();
                    }

                }
                ADD_WRITE_OW.close();
            } else {
                BufferedWriter ADD_WRITE_AP = new BufferedWriter(new FileWriter(filePath, true));
                ADD_WRITE_AP.write(item);
                ADD_WRITE_AP.newLine();
                ADD_WRITE_AP.write(id);
                ADD_WRITE_AP.newLine();
                ADD_WRITE_AP.write(quantity);
                ADD_WRITE_AP.newLine();

                ADD_WRITE_AP.close();
            }

            System.out.println("Successfully wrote to the file.");
            emptyBlock = false;

        }catch (IOException e){
            System.out.println("Error: File not found");
        }
    }



    public void EditLoad(String item, int quantity, int id) {
        String newItemName = item;
        String newQuantity = Integer.toString(quantity);

        try (BufferedReader read = new BufferedReader(new FileReader(filePath))) {
            int counter = 0;
            int index = 0;
            while ((currentLine = read.readLine()) != null) {
                ref.add(currentLine);
            }

            while (true) {
                targetIDString = Integer.toString(id);
                if (ref.get(index).equals(targetIDString)) {
                    int itemIndex = counter - ID_INDEX;
                    int quantityIndex = counter + QUANTITY_INDEX;

                    System.out.println("ID found: " + ref.get(index));
                    System.out.println("Item found: " + ref.get(itemIndex));
                    System.out.println("Quantity found: " + ref.get(quantityIndex));

                    ref.set(itemIndex, newItemName);
                    ref.set(quantityIndex, newQuantity);

                    editedName = ref.get(itemIndex);
                    editedQuantity = ref.get(quantityIndex);
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
        String targetID = Integer.toString(id);

        try (BufferedWriter write = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = ITEM_INDEX; i < ref.size(); i += BLOCK_SIZE) {
                String currentID = ref.get(i + ID_INDEX);
                if (currentID.equals(targetID)) {
                    write.write(editedName);
                    write.newLine();
                    write.write(targetID);
                    write.newLine();
                    write.write(editedQuantity);
                    write.newLine();
                } else {
                    write.write(ref.get(i));
                    write.newLine();
                    write.write(ref.get(i + ID_INDEX));
                    write.newLine();
                    write.write(ref.get(i + REF_Quan_INDEX));
                    write.newLine();
                }
            }
            System.out.println("Successfully updated the file.");
            ref.clear();
            System.out.println("Debug: " + ref.get(ITEM_INDEX));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public void removeLoad(int remID, String remItem, int remQuan) {
        String removeItemName = remItem;
        String removeQuantity = Integer.toString(remQuan);

        try (BufferedReader read = new BufferedReader(new FileReader(filePath))) {
            int counter = 0;
            int index = 0;
            while ((currentLine = read.readLine()) != null) {
                ref.add(currentLine);
            }

            while (true) {
                String targetIDString = Integer.toString(remID);
                if (ref.get(index).equals(targetIDString)) {
                    int itemIndex = counter - ID_INDEX;
                    int quantityIndex = counter + QUANTITY_INDEX;

                    System.out.println("ID found: " + ref.get(index));
                    System.out.println("Item found: " + ref.get(itemIndex));
                    System.out.println("Quantity found: " + ref.get(quantityIndex));

                    ref.set(itemIndex, removeItemName);
                    ref.set(quantityIndex, removeQuantity);

                    editedName = ref.get(itemIndex);
                    editedQuantity = ref.get(quantityIndex);
                    break;
                }
                counter++;
                index++;
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void RemoveItem(int id) {
        String targetID = Integer.toString(id);

        try (BufferedWriter write = new BufferedWriter(new FileWriter(filePath))) { // overwrite
            for (int i = ITEM_INDEX; i < ref.size(); i += BLOCK_SIZE) { // step by 3 lines (item, id, quantity)
                String currentID = ref.get(i + ID_INDEX);

                if (currentID.equals(targetID)) {
                    write.write(empty_NAME);
                    write.newLine();
                    write.write(empty_ID);
                    write.newLine();
                    write.write(empty_quan);
                    write.newLine();

                } else {
                    write.write(ref.get(i));
                    write.newLine();
                    write.write(ref.get(i + ID_INDEX));
                    write.newLine();
                    write.write(ref.get(i + REF_Quan_INDEX));
                    write.newLine();
                }
            }

            System.out.println("Successfully removed the item (fields blanked).");

            ref.clear();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }



}
