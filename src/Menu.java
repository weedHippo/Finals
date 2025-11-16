import java.util.*;

public class Menu {
    Scanner input = new Scanner(System.in);
    Service sr = new Service();
    public void mMenu(){
        do {
            System.out.println("==========  -Inventory Manager-  ==========");
            System.out.println("\t1. Add an Item");
            System.out.println("\t2. list Items");
            System.out.println("\t3. Remove an Item");
            System.out.println("\t4. Edit an Item");
            System.out.println("\t5. Exit");
            System.out.println("====================================");
            System.out.print("\tEnter your choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    Add();
                    break;
                case 2:
                    sr.DisplayAll();
                    break;
                case 3:
                    Remove();
                    break;
                case 4:
                    Edit();
                    break;
                case 5:
                    System.out.println("Goodbye!!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");

            }
        } while (true);
    }

    public void Add() {
        try {
            while (true) {
                System.out.println("Please enter the name and ID of the item you want to add");
                System.out.print("Item ID: ");
                int itemID = input.nextInt();
                input.nextLine();

                System.out.print("Product Name: ");
                String itemName = input.nextLine();


                System.out.print("Item quantity: ");
                int itemQuantity = input.nextInt();
                input.nextLine();


                sr.AddItem(itemName, itemID, itemQuantity);
                System.out.print("\nAdd an item? Y/N: ");
                String choice = input.nextLine();

                if (choice.equalsIgnoreCase("N")) {
                    break;
                }

            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input — please enter numbers for ID and quantity only.");
            input.nextLine();
        } catch (NullPointerException e) {
            System.out.println("Unexpected null value encountered.");
        }
    }
    public void Remove() {
        while (true) {
            System.out.println("Warning: youre about to remove an item in the inventory" + "\n Type Y to proceed, press N to return to the Menu");
            System.out.print("Y/N: ");
            String choice = input.nextLine();
            input.nextLine();

            if (choice.equalsIgnoreCase("N")) {
                System.out.println("returning to the main menu...");
                break;
            } else if (choice.equalsIgnoreCase("Y")) {
                System.out.println("Enter the Id of the item you want to remove: ");
                System.out.print("Item ID: ");
                int itemID = input.nextInt();
                sr.remove(itemID);
            } else {
                System.out.println("Invalid input");
            }
        }
    }
    //jo
    public void Edit() {
        //instead of editing the quantity how bout like manually add or subtract quan idk
        System.out.print("Enter the Item Id you want to edit: ");
        int itemID = input.nextInt();
        input.nextLine();
        System.out.print("Enter the new Item Name: ");
        String itemName = input.nextLine();
        System.out.print("Enter the new Item Quantity: ");
        int itemQuantity = input.nextInt();
        assert itemID >= 1000 && itemID <= 9999: "Id out of bounds";
        assert itemQuantity >= 50 &&  itemQuantity <= 999: "Quantity out of bounds";
        sr.Edit(itemID, itemName, itemQuantity);
    }

}

