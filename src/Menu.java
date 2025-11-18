import java.util.*;

public class Menu {
    Scanner input = new Scanner(System.in);
    private final Service sr;

    public Menu(Service sr) {
        this.sr = sr;
    }
    public void mMenu(){
        do {
            System.out.println("==========  -Inventory Manager-  ==========");
            System.out.println("\t1. Add an Item");
            System.out.println("\t2. list Items");
            System.out.println("\t3. Remove an Item");
            System.out.println("\t4. Edit an Item");
            System.out.println("\t5. Refresh");
            System.out.println("\t6. Exit");
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
                    sr.preLoad();
                    break;
                case 6:
                    System.out.println("Goodbye!!");
                    System.out.println("And thank you :D");
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
            System.out.println("Warning: you're about to remove an item from the inventory.");
            System.out.println("Type Y to proceed, or N to return to the menu.");
            System.out.print("Y/N: ");

            String choice = input.nextLine().trim();

            if (choice.equalsIgnoreCase("N")) {
                System.out.println("Returning to the main menu...");
                break;
            }

            if (choice.equalsIgnoreCase("Y")) {
                System.out.print("Enter the ID of the item you want to remove: ");

                if (!input.hasNextInt()) {
                    System.out.println("Invalid ID! Must be a number.");
                    input.nextLine();
                    continue;
                }

                int itemID = input.nextInt();
                input.nextLine();

                sr.remove(itemID);
                break;
            }

            System.out.println("Invalid choice. Please enter only Y or N.");
        }
    }

    public void Edit() {
        System.out.print("Enter the Item Id you want to edit: ");
        int itemID = input.nextInt();
        input.nextLine();
        System.out.print("Enter the new Item Name: ");
        String itemName = input.nextLine();
        System.out.print("Enter the new Item Quantity: ");
        int itemQuantity = input.nextInt();
        sr.Edit(itemID, itemName, itemQuantity);
    }

}

