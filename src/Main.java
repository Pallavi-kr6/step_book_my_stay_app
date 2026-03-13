import java.util.*;

// Custom Exception for invalid booking scenarios
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Reservation class
class Reservation {

    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Guest Name: " + guestName);
        System.out.println("Room Type: " + roomType);
        System.out.println("---------------------------");
    }
}

// Validator class
class InvalidBookingValidator {

    private Map<String, Integer> roomInventory;

    public InvalidBookingValidator(Map<String, Integer> roomInventory) {
        this.roomInventory = roomInventory;
    }

    // Validate booking request
    public void validateBooking(String roomType) throws InvalidBookingException {

        // Check if room type exists
        if (!roomInventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid Room Type: " + roomType);
        }

        // Check availability
        if (roomInventory.get(roomType) <= 0) {
            throw new InvalidBookingException("No rooms available for type: " + roomType);
        }
    }
}

// Booking Manager
class BookingManager {

    private Map<String, Integer> roomInventory;
    private InvalidBookingValidator validator;

    public BookingManager() {

        roomInventory = new HashMap<>();

        roomInventory.put("Standard", 2);
        roomInventory.put("Deluxe", 1);
        roomInventory.put("Suite", 0); // Example unavailable room

        validator = new InvalidBookingValidator(roomInventory);
    }

    public Reservation bookRoom(String reservationId, String guestName, String roomType)
            throws InvalidBookingException {

        // Validate first (Fail-Fast)
        validator.validateBooking(roomType);

        // Update inventory
        roomInventory.put(roomType, roomInventory.get(roomType) - 1);

        return new Reservation(reservationId, guestName, roomType);
    }

    public void showInventory() {
        System.out.println("Current Room Inventory:");
        for (String type : roomInventory.keySet()) {
            System.out.println(type + " : " + roomInventory.get(type));
        }
        System.out.println("---------------------------");
    }
}

 class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        BookingManager manager = new BookingManager();

        manager.showInventory();

        try {

            Reservation r1 = manager.bookRoom("RES201", "Alice", "Deluxe");
            r1.display();

            // Invalid booking (no availability)
            Reservation r2 = manager.bookRoom("RES202", "Bob", "Suite");
            r2.display();

        } catch (InvalidBookingException e) {

            // Graceful failure handling
            System.out.println("Booking Failed: " + e.getMessage());
        }

        try {

            // Invalid room type
            Reservation r3 = manager.bookRoom("RES203", "Charlie", "Luxury");
            r3.display();

        } catch (InvalidBookingException e) {

            System.out.println("Booking Failed: " + e.getMessage());
        }

        manager.showInventory();
    }
}