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
    private static final int QUANTITY_INDEX = 2;

    public void Write(int id, String item, int quantity) {
        try {
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
                    int quantityIndex = counter + ITEM_INDEX;

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

        try (BufferedWriter write = new BufferedWriter(new FileWriter(filePath))) { // overwrite
            for (int i = ITEM_INDEX; i < ref.size(); i += BLOCK_SIZE) { // step by 3 lines (item, id, quantity)
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
                    write.write(ref.get(i + QUANTITY_INDEX));
                    write.newLine();
                }
            }
            System.out.println("Successfully updated the file.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
