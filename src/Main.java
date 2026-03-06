import java.util.HashMap;
import java.util.Map;
abstract class Room {
    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    public void displayRoomDetails() {
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Room Size: " + squareFeet + " sq ft");
        System.out.println("Price Per Night: Rs." + pricePerNight);
    }
}

class SingleRoom extends Room {
    public SingleRoom() {
        super(1, 250, 1500.0);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 400, 2500.0);
    }
}
 class RoomSearchService {

    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Displays all available room types with their details
     */
    public void displayAvailableRooms() {

        System.out.println("Available Rooms:");

        // Get inventory map
        HashMap<String, Integer> invMap = inventory.getInventoryMap();

        for (Map.Entry<String, Integer> entry : invMap.entrySet()) {
            String roomType = entry.getKey();
            int available = entry.getValue();

            if (available > 0) {  // Only show rooms with availability
                Room room = null;

                // Instantiate room object to get details
                switch (roomType) {
                    case "SingleRoom":
                        room = new SingleRoom();
                        break;
                    case "DoubleRoom":
                        room = new DoubleRoom();
                        break;
                }

                if (room != null) {
                    System.out.println("\nRoom Type: " + roomType);
                    room.displayRoomDetails();
                    System.out.println("Available: " + available);
                }
            }
        }
    }
}
 class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("SingleRoom", 10);
        inventory.put("DoubleRoom", 5);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int newCount) {
        inventory.put(roomType, newCount);
    }

    // Added method for read-only access
    public HashMap<String, Integer> getInventoryMap() {
        return new HashMap<>(inventory);  // return a copy for safety
    }

    public void displayInventory() {
        System.out.println("Current Room Inventory:");
        for (String key : inventory.keySet()) {
            System.out.println(key + " -> Available: " + inventory.get(key));
        }
    }
}

/**
 * MAIN CLASS
 * Demonstrates room search & availability check
 */
public class Main  {

    public static void main(String[] args) {

        // Initialize inventory system
        RoomInventory inventory = new RoomInventory();

        // Initialize search service
        RoomSearchService searchService = new RoomSearchService(inventory);

        // Display available rooms (read-only)
        searchService.displayAvailableRooms();

        // Demonstrate that inventory remains unchanged
        System.out.println("\nInventory after search (should be unchanged):");
        inventory.displayInventory();
    }
}