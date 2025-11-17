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

            while (true) {
                String name_Line = read.readLine();
                if (name_Line == null) break;

                String ID_Line = read.readLine();
                String quan_Line = read.readLine();

                int ID = Integer.parseInt(ID_Line);
                int Qun = Integer.parseInt(quan_Line);
                String I_Name = name_Line;

                userInputs.add(ID);
                Inv.put(ID, I_Name);
                Quan.put(ID, Qun);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


   public void AddItem(String item, int ID, int quan) {
        try{
            while(true){
                if(Inv.containsKey(ID)){
                    System.out.println("Item with that ID already exists!");
                    System.err.println("Try Again");
                    break;
                }

                if(ID >=  minIDcap && ID <= maxIDcap && quan >= minQuanCap && quan <= maxQuanCap) {
                    userInputs.add(ID);
                    Inv.put(ID, item);
                    Quan.put(ID, quan);

                    super.Write(ID, item, quan);
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

        for (int id : Inv.keySet()) {
            String name = Inv.get(id);
            Integer quantity = Quan.get(id); // use Integer for null check

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

        if (op.equalsIgnoreCase("Y")) {

            String item = Inv.get(inp);
            int remQuan = Quan.getOrDefault(inp, 0);  // safe get
            int refID = inp;

            // Call your file modification logic
            super.removeLoad(refID, item, remQuan);
            super.RemoveItem(inp);

            // Mark entry as removed (empty slot)
            Inv.put(inp, "");
            Quan.put(inp, 0);

            System.out.println("Item removed successfully!");

        } else if (op.equalsIgnoreCase("N")) {
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