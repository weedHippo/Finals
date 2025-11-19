import java.util.*;
import java.io.*;
public class Service extends DataManager {
    Scanner input = new Scanner(System.in);
   private HashMap<Integer,String> Inv = new HashMap<>();
   private HashMap<Integer,Integer> Quan = new HashMap<>();
   private ArrayList<Integer> userInputs = new ArrayList<>();

   boolean easterEgg = false;

   private static final int minIDcap = 1000;
   private static final int maxIDcap = 9999;

   private static final int minQuanCap = 50;
   private static final int maxQuanCap = 999;

   private static final String choice_Yes = "Y";
   private static final String choice_No = "N";
   private static final String File_Path = "Data.txt";


    public void setInv(HashMap<Integer, String> inv) {
        this.Inv = inv;
    }
    public void setQuan(HashMap<Integer, Integer> quan) {
        this.Quan = quan;
    }
    public void setUserInputs(ArrayList<Integer> userInputs) {
        this.userInputs = userInputs;
    }



    public void preLoad() {
        try (BufferedReader read = new BufferedReader(new FileReader(File_Path))) {
            String name_Line;
            while ((name_Line = read.readLine()) != null) {
                String ID_Line = read.readLine();
                String quan_Line = read.readLine();

                if (ID_Line == null || quan_Line == null) {
                    System.out.println("Warning: incomplete block detected, skipping");
                    break;
                }

                int ID = Integer.parseInt(ID_Line);
                int Qun = Integer.parseInt(quan_Line);

                userInputs.add(ID);
                Inv.put(ID, name_Line);
                Quan.put(ID, Qun);
            }

        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }


   public void AddItem(String item, int ID, int quan) {
        try{
            while(true){

                if(Inv.containsKey(ID)){
                    System.out.println("\n");
                    System.out.println("Item with that ID already exists!");
                    System.out.println("Try Again");
                    break;
                }

                if(ID >=  minIDcap && ID <= maxIDcap && quan >= minQuanCap && quan <= maxQuanCap) {
                    userInputs.add(ID);
                    Inv.put(ID, item);
                    Quan.put(ID, quan);
                    super.AddToFile(item,ID,quan);
                    System.out.println("\nItem added successfully!");
                    System.out.println("\nID: " + ID + " | Name: " + item + " | Quantity: " + quan);
                    break;


                } else{
                    System.out.println("The ID or quantity you entered is out of bounds");
                    break;
                }
            }

        } catch(InputMismatchException e){
            System.out.println(e.getMessage());
        }

    }


    public void DisplayAll() {
        System.out.println("\n--- Current Inventory ---");
        List<Integer> IDs = new ArrayList<>(Inv.keySet());
        Collections.sort(IDs);
        for (int id : IDs) {
            String name = Inv.get(id);
            Integer quantity = Quan.get(id);

            boolean missingName = (name == null || name.trim().isEmpty());
            boolean missingQuantity = (quantity == null || quantity == 0);

            if (missingName || missingQuantity) {
                System.out.println("ID: " + id + " | Name: <Not Found> | Quantity: <Not Found>");
            } else {
                System.out.println("ID: " + id + " | Name: " + name + " | Quantity: " + quantity);
            }
        }

        if (!easterEgg) {
            System.out.println("Achievement get: Keeping Inventory");
            easterEgg = true;
        }

        System.out.println("--------------------------\n");
    }


    public void remove(int inp) {

        if (!Inv.containsKey(inp)) {
            System.out.println("Item not found!");
            System.out.println("Enter the proper ID and try again");
            return;
        }

        System.out.println("\nAre you sure you want to remove: " + Inv.get(inp) + "?");
        System.out.println("This process is irreversible.");
        System.out.print("Your choice (Y/N): ");

        String op = input.nextLine().trim();

        if (op.equalsIgnoreCase(choice_Yes)) {

            String item = Inv.get(inp);
            int remQuan = Quan.getOrDefault(inp, 0);
            int refID = inp;


            super.removeLoad(refID, item, remQuan);
            super.RemoveItem(inp);

            Inv.remove(inp);
            Quan.remove(inp);

            // Mark entry as removed (empty slot)
            int emptyID = 0000;
            Inv.put(emptyID, "");
            Quan.put(emptyID, 0);

            System.out.println("Item removed successfully!");

        } else if (op.equalsIgnoreCase(choice_No)) {
            System.out.println("Action cancelled.");
        } else {
            System.out.println("Invalid input. Removal aborted.");
        }
    }



    public void Edit(int inp, String item, int quan) {
        try{
            assert inp >= minIDcap && inp <= maxIDcap: "Id out of bounds";
            assert quan >= minQuanCap &&  quan <= maxQuanCap: "Quantity out of bounds";
            if(userInputs.contains(inp)){
                Inv.put(inp, item);
                Quan.put(inp, quan);
                super.EditLoad(item, quan, inp);
                super.EditWrite(inp);
            System.out.println("Item is now edited!");
            } else{
                System.out.println("Item does not exist!");
            }
        }catch(Exception e){
            System.out.println("Error!");
        }
    }
}