import java.util.*;

public class Service {
    Scanner input = new Scanner(System.in);
   private HashMap<Integer,String> Inv = new HashMap<>();
   private HashMap<Integer,Integer> Quan = new HashMap<>();
   private ArrayList<Integer> userInputs = new ArrayList<>();

    public void setInv(HashMap<Integer, String> inv) {
        this.Inv = inv;
    }
    public void setQuan(HashMap<Integer, Integer> quan) {
        this.Quan = quan;
    }
    public void setUserInputs(ArrayList<Integer> userInputs) {
        this.userInputs = userInputs;
    }

    public void AddItem(String item, int ID, int quan) {
        Inv.put(ID, item);
        Quan.put(ID, quan);
        userInputs.add(ID); // store the ID for later reference

        System.out.println("\nItem added successfully!");
        System.out.println("ID: " + ID + " | Name: " + item + " | Quantity: " + quan);
    }

    public void DisplayAll() {
        System.out.println("\n--- Current Inventory ---");
        for (int id : Inv.keySet()) {
            String name = Inv.get(id);
            int quantity = Quan.getOrDefault(id, 0);
            System.out.println("ID: " + id + " | Name: " + name + " | Quantity: " + quantity);
        }
        System.out.println("--------------------------");
    }

    public void remove(int inp){
       if(Inv.containsKey(inp)){
           System.out.println("Are you sure you want to remove the item " + Inv.get(inp) + " ?" );
           System.out.print("Your Choice: ");
           String op = input.next();

           if(op.equals("Y")){
               Inv.remove(inp);
           } else if(op.equals("N")){
               System.out.println("Action Cencelled");
           }
       } else {
           System.out.println("Item not found!");
       }




    }
}