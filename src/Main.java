import java.util.*;

/**
 * UseCase6RoomAllocationService
 *
 * This class simulates a hotel booking system that:
 * - Processes booking requests in FIFO order.
 * - Allocates unique room IDs.
 * - Prevents double-booking by tracking assigned rooms.
 * - Updates inventory immediately after allocation.
 */
 class UseCase6RoomAllocationService {

    // Queue to hold booking requests in FIFO order
    private Queue<BookingRequest> bookingQueue = new LinkedList<>();

    // Map to maintain available inventory: roomType -> count
    private Map<String, Integer> inventory = new HashMap<>();

    // Map to track assigned room IDs by room type: roomType -> set of roomIDs
    private Map<String, Set<String>> allocatedRooms = new HashMap<>();

    // Counter for unique room ID generation
    private int roomIdCounter = 1000;

    public UseCase6RoomAllocationService() {
        // Initialize inventory for each room type
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);

        // Initialize allocatedRooms map
        allocatedRooms.put("Single", new HashSet<>());
        allocatedRooms.put("Double", new HashSet<>());
        allocatedRooms.put("Suite", new HashSet<>());
    }

    // Class representing a booking request
    static class BookingRequest {
        String customerName;
        String roomType;

        public BookingRequest(String customerName, String roomType) {
            this.customerName = customerName;
            this.roomType = roomType;
        }
    }

    /**
     * Adds a booking request to the queue.
     */
    public void addBookingRequest(String customerName, String roomType) {
        bookingQueue.add(new BookingRequest(customerName, roomType));
        System.out.println("Booking request added: " + customerName + " requests " + roomType);
    }

    /**
     * Processes booking requests one by one, allocating rooms if available.
     */
    public void processBookings() {
        while (!bookingQueue.isEmpty()) {
            BookingRequest request = bookingQueue.poll();
            System.out.println("\nProcessing booking for " + request.customerName + " (" + request.roomType + ")...");

            if (!inventory.containsKey(request.roomType)) {
                System.out.println(" - Room type " + request.roomType + " does not exist.");
                continue;
            }

            int available = inventory.get(request.roomType);
            if (available <= 0) {
                System.out.println(" - No rooms available for type: " + request.roomType);
                continue;
            }

            // Allocate unique room ID
            String roomId = generateUniqueRoomId(request.roomType);
            if (roomId == null) {
                System.out.println(" - Failed to allocate a unique room ID. Booking denied.");
                continue;
            }

            // Update inventory and allocated rooms atomically
            inventory.put(request.roomType, available - 1);
            allocatedRooms.get(request.roomType).add(roomId);

            // Confirm reservation
            System.out.println(" - Reservation confirmed!");
            System.out.println("   Assigned Room ID: " + roomId);
            System.out.println("   Remaining inventory for " + request.roomType + ": " + (available - 1));
        }
    }

    /**
     * Generates a unique room ID for the given room type.
     * Ensures no duplicates by checking the allocatedRooms set.
     */
    private String generateUniqueRoomId(String roomType) {
        String candidateId;
        int attempts = 0;
        do {
            candidateId = roomType.substring(0, 1).toUpperCase() + roomIdCounter++;
            attempts++;
            if (attempts > 10000) {
                // Fail-safe to avoid infinite loop
                return null;
            }
        } while (allocatedRooms.get(roomType).contains(candidateId));

        return candidateId;
    }


}

public class Main{
    public static void main(String[] args) {
        UseCase6RoomAllocationService service = new UseCase6RoomAllocationService();

        // Add some booking requests
        service.addBookingRequest("Alice", "Single");
        service.addBookingRequest("Bob", "Double");
        service.addBookingRequest("Charlie", "Suite");
        service.addBookingRequest("Diana", "Single");
        service.addBookingRequest("Eve", "Double");
        service.addBookingRequest("Frank", "Suite");
        service.addBookingRequest("Grace", "Single");
        service.addBookingRequest("Heidi", "Single");
        service.addBookingRequest("Ivan", "Double");
        service.addBookingRequest("Judy", "Single"); // Should fail if inventory is exhausted

        // Process all bookings
        service.processBookings();
    }
}