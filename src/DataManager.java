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

    public void AddToFile(String name, int id, int quantity) {
        try {
            // Load the file into memory
            ref.clear();
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    ref.add(line);
                }
            }

            String strID = Integer.toString(id);
            String strQuantity = Integer.toString(quantity);
            boolean written = false;

            for (int i = 0; i < ref.size(); i += 3) {
                String blockID = ref.get(i + 1);
                String blockQuantity = ref.get(i + 2);

                if (!written && (blockID.equals(empty_ID) || blockQuantity.equals(empty_quan))) {
                    ref.set(i, name);
                    ref.set(i + 1, strID);
                    ref.set(i + 2, strQuantity);
                    written = true;
                    break;
                }
            }

            if (!written) {
                ref.add(name);
                ref.add(strID);
                ref.add(strQuantity);
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
                for (int i = 0; i < ref.size(); i++) {
                    bw.write(ref.get(i));
                    bw.newLine();
                }
            }

            System.out.println("Successfully added/updated item.");

        } catch (IOException e) {
            System.out.println("Error in Add_Load: " + e.getMessage());
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
