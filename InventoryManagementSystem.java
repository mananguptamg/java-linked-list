// Class representing an Inventory Item
class Item {
    String itemName;
    int itemID;
    int quantity;
    double price;
    Item next; // Pointer to next item

    // Constructor to initialize item details
    public Item(String itemName, int itemID, int quantity, double price) {
        this.itemName = itemName;
        this.itemID = itemID;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

// Class to manage the Inventory as a Singly Linked List
class Inventory {
    private Item head; // Head of the linked list

    // Method to add an item at the beginning
    public void addItemAtBeginning(String itemName, int itemID, int quantity, double price) {
        Item newItem = new Item(itemName, itemID, quantity, price);
        newItem.next = head;
        head = newItem;
    }

    // Method to add an item at the end
    public void addItemAtEnd(String itemName, int itemID, int quantity, double price) {
        Item newItem = new Item(itemName, itemID, quantity, price);
        if (head == null) {
            head = newItem;
            return;
        }
        Item temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newItem;
    }

    // Method to add an item at a specific position
    public void addItemAtPosition(String itemName, int itemID, int quantity, double price, int position) {
        if (position <= 0) {
            System.out.println("Invalid position.");
            return;
        }

        Item newItem = new Item(itemName, itemID, quantity, price);
        if (position == 1) {
            newItem.next = head;
            head = newItem;
            return;
        }

        Item temp = head;
        for (int i = 1; i < position - 1 && temp != null; i++) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Position out of range.");
            return;
        }

        newItem.next = temp.next;
        temp.next = newItem;
    }

    // Method to remove an item based on Item ID
    public void removeItem(int itemID) {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }

        if (head.itemID == itemID) {
            head = head.next;
            return;
        }

        Item temp = head;
        while (temp.next != null && temp.next.itemID != itemID) {
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Item with ID " + itemID + " not found.");
            return;
        }

        temp.next = temp.next.next;
    }

    // Method to update the quantity of an item by Item ID
    public void updateItemQuantity(int itemID, int newQuantity) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemID == itemID) {
                temp.quantity = newQuantity;
                System.out.println("Updated quantity for Item ID " + itemID + " to " + newQuantity);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item with ID " + itemID + " not found.");
    }

    // Method to search for an item by Item ID
    public void searchItemByID(int itemID) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemID == itemID) {
                System.out.println("Item Found: " + temp.itemName + " | ID: " + temp.itemID + " | Quantity: " + temp.quantity + " | Price: " + temp.price);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item with ID " + itemID + " not found.");
    }

    // Method to search for an item by Item Name
    public void searchItemByName(String itemName) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemName.equalsIgnoreCase(itemName)) {
                System.out.println("Item Found: " + temp.itemName + " | ID: " + temp.itemID + " | Quantity: " + temp.quantity + " | Price: " + temp.price);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item with Name '" + itemName + "' not found.");
    }

    // Method to calculate the total inventory value
    public void calculateTotalValue() {
        double totalValue = 0;
        Item temp = head;
        while (temp != null) {
            totalValue += temp.price * temp.quantity;
            temp = temp.next;
        }
        System.out.println("Total Inventory Value: " + totalValue);
    }

    // Method to display the inventory list
    public void displayInventory() {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("Inventory List:");
        Item temp = head;
        while (temp != null) {
            System.out.println(temp.itemName + " | ID: " + temp.itemID + " | Quantity: " + temp.quantity + " | Price: " + temp.price);
            temp = temp.next;
        }
    }
}

// Main class to run the program
public class InventoryManagementSystem {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        // Adding items to the inventory
        inventory.addItemAtEnd("Laptop", 1010, 5, 50000);
        inventory.addItemAtEnd("Mouse", 1020, 20, 500);
        inventory.addItemAtBeginning("Keyboard", 1030, 15, 900);
        inventory.addItemAtPosition("Monitor", 1040, 10, 15000, 2);

        // Displaying inventory
        inventory.displayInventory();

        // Searching for an item
        inventory.searchItemByID(1020);
        inventory.searchItemByName("Monitor");

        // Updating quantity
        inventory.updateItemQuantity(1010, 7);

        // Calculating total inventory value
        inventory.calculateTotalValue();

        // Removing an item
        inventory.removeItem(1030);

        // Displaying inventory after removal
        inventory.displayInventory();
    }
}

//SampleOutput
//Inventory List:
//Keyboard | ID: 1030 | Quantity: 15 | Price: 900.0
//Monitor | ID: 1040 | Quantity: 10 | Price: 15000.0
//Laptop | ID: 1010 | Quantity: 5 | Price: 50000.0
//Mouse | ID: 1020 | Quantity: 20 | Price: 500.0
//Item Found: Mouse | ID: 1020 | Quantity: 20 | Price: 500.0
//Item Found: Monitor | ID: 1040 | Quantity: 10 | Price: 15000.0
//Updated quantity for Item ID 1010 to 7
//Total Inventory Value: 523500.0
//Inventory List:
//Monitor | ID: 1040 | Quantity: 10 | Price: 15000.0
//Laptop | ID: 1010 | Quantity: 7 | Price: 50000.0
//Mouse | ID: 1020 | Quantity: 20 | Price: 500.0
