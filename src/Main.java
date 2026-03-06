import java.util.HashMap;
import java.util.Map;

/**
 * CLASS - RoomInventory
 * Responsible for maintaining centralized room availability.
 */
  class RoomInventory {

    /** HashMap storing room type -> available count */
    private HashMap<String, Integer> inventory;

    /**
     * Constructor initializes room availability
     */
    public RoomInventory() {
        inventory = new HashMap<>();

        // Register room types with initial availability
        inventory.put("SingleRoom", 10);
        inventory.put("DoubleRoom", 5);
    }

    /**
     * Retrieves availability of a specific room type
     */
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    /**
     * Updates availability for a given room type
     */
    public void updateAvailability(String roomType, int newCount) {
        inventory.put(roomType, newCount);
    }

    /**
     * Displays full inventory state
     */
    public void displayInventory() {
        System.out.println("Current Room Inventory:");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " -> Available: " + entry.getValue());
        }
    }
}

/**
 * MAIN CLASS
 * Demonstrates centralized room inventory management
 */
public class Main{

    public static void main(String[] args) {

        // Initialize inventory system
        RoomInventory inventory = new RoomInventory();

        // Display current inventory
        inventory.displayInventory();

        System.out.println("\nChecking availability for SingleRoom:");
        System.out.println("Available: " + inventory.getAvailability("SingleRoom"));

        // Update inventory after booking
        System.out.println("\nUpdating inventory after booking...");
        inventory.updateAvailability("SingleRoom", 9);

        // Display updated inventory
        System.out.println("\nUpdated Inventory:");
        inventory.displayInventory();
    }
}