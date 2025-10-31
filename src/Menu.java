import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    Scanner input = new Scanner(System.in);
    Service sr = new Service();
    public void mMenu(){
        while(true){
            System.out.println("Inv management");
            System.out.println("1. list Inventory");
            System.out.println("2. Add Item");
            System.out.println("3. Remove Item");
            System.out.println("4. Edit Item");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();

            switch(choice){
                case 1:
                    sr.DisplayAll();
                    break;
                case 2:
                    Add();
                    break;
                case 3:
                    Remove();
                    break;
                default:
                    System.out.println("Invalid choice");

            }
        }
    }

    public void Add() {
        try {
            while (true) {
                System.out.println("Please enter the name and ID of the item you want to add");

                System.out.print("Item ID: ");
                int itemID = input.nextInt();
                input.nextLine();

                System.out.print("Item Name: ");
                String itemName = input.nextLine();

                System.out.print("Item quantity: ");
                int itemQuantity = input.nextInt();
                input.nextLine();

                sr.AddItem(itemName, itemID, itemQuantity);
                System.out.println("Item added successfully!");

                System.out.print("Add another item? Y/N: ");
                String choice = input.nextLine();

                if (choice.equalsIgnoreCase("N")) {
                    break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input — please enter numbers for ID and quantity only.");
            input.nextLine(); // clear invalid input buffer
        } catch (NullPointerException e) {
            System.out.println("Unexpected null value encountered.");
        }
    }

    public void Remove() {
        try{
            while (true) {
                System.out.println("Warning: youre about to remove an item in the inventory" + "\n Type Y to proceed, press N to return to the Menu");
                System.out.print("Y/N: ");
                String choice = input.nextLine();
                if (choice.equalsIgnoreCase("N")) {
                    System.out.println("returning to the main menu...");
                    break;
                }else if (choice.equalsIgnoreCase("Y")) {
                    System.out.println("Enter the Id of the item you want to remove: ");
                    System.out.print("Item ID: ");
                    int itemID = input.nextInt();
                    sr.remove(itemID);
                }else {
                    System.out.println("Invalid input");
                }
            }
        }catch(InputMismatchException e){
            System.out.println(e.getMessage());
        }
    }

}
