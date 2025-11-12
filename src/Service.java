import java.util.*;

public class Service {
    Scanner input = new Scanner(System.in);
   private HashMap<Integer,String> Inv = new HashMap<>();
   private HashMap<Integer,Integer> Quan = new HashMap<>();
   private ArrayList<Integer> userInputs = new ArrayList<>();
   boolean easterEgg = false;
   boolean Error = false;
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

        try{
            while(true){
                if(Inv.containsKey(ID)){
                    System.out.println("Item with that ID already exists!");
                    System.err.println("Try Again");
                    break;
                }

                if(ID >= 1000 && ID <= 9999 && quan >= 50 && quan <= 999) {
                    userInputs.add(ID);
                    Inv.put(ID, item);
                    Quan.put(ID, quan);
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
            int quantity = Quan.getOrDefault(id, 0);
            System.out.println("ID: " + id + " | Name: " + name + " | Quantity: " + quantity);
        }
        if (easterEgg == false) {
            System.out.println("Achievement get: Keeping Inventory");
            easterEgg = true;
        }

        System.out.println("--------------------------");
        System.out.println("\n");

    }

    public void remove(int inp){
       if(Inv.containsKey(inp)){
           System.out.println("Are you sure you want to remove the item " + Inv.get(inp) + " ?" );
           System.out.print("Your Choice Y/N: ");
           String op = input.next();

           if(op.equals("Y")){
               Inv.remove(inp);
           } else if(op.equals("N")){
               System.out.println("Action Cancelled");
           }
       } else {
           System.out.println("Item not found!");
       }



    }


    //hello
    public void Edit(int inp, String item, int quan) {
        try{
            if(userInputs.contains(inp)){
                Inv.put(inp, item);
                Quan.put(inp, quan);
            System.out.println("Item is now edited!");
            } else{
                System.out.println("Item does not exist!");
            }
        }catch(Exception e){
            System.out.println("Error!");
        }
    }
}