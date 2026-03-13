import java.util.*;

// Reservation class
class Reservation {

    private String reservationId;
    private String guestName;
    private String roomType;
    private String roomId;
    private boolean active;

    public Reservation(String reservationId, String guestName, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
        this.active = true;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomId() {
        return roomId;
    }

    public boolean isActive() {
        return active;
    }

    public void cancel() {
        active = false;
    }

    public void display() {
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Guest Name: " + guestName);
        System.out.println("Room Type: " + roomType);
        System.out.println("Room ID: " + roomId);
        System.out.println("Status: " + (active ? "Confirmed" : "Cancelled"));
        System.out.println("---------------------------");
    }
}

// Cancellation Service
class CancellationService {

    private Map<String, Reservation> reservations;
    private Map<String, Integer> inventory;
    private Stack<String> rollbackStack;

    public CancellationService(Map<String, Reservation> reservations,
                               Map<String, Integer> inventory) {
        this.reservations = reservations;
        this.inventory = inventory;
        this.rollbackStack = new Stack<>();
    }

    public void cancelBooking(String reservationId) {

        if (!reservations.containsKey(reservationId)) {
            System.out.println("Cancellation Failed: Reservation not found.");
            return;
        }

        Reservation res = reservations.get(reservationId);

        if (!res.isActive()) {
            System.out.println("Cancellation Failed: Reservation already cancelled.");
            return;
        }

        // Record room ID for rollback tracking
        rollbackStack.push(res.getRoomId());

        // Restore inventory
        String roomType = res.getRoomType();
        inventory.put(roomType, inventory.get(roomType) + 1);

        // Mark reservation cancelled
        res.cancel();

        System.out.println("Booking cancelled successfully for ID: " + reservationId);
    }

    public void showRollbackStack() {
        System.out.println("Rollback Stack (Released Room IDs): " + rollbackStack);
    }
}

  class UseCase10BookingCancellation {

    public static void main(String[] args) {

        // Inventory
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Standard", 1);
        inventory.put("Deluxe", 1);

        // Reservation storage
        Map<String, Reservation> reservations = new HashMap<>();

        // Simulate confirmed bookings
        Reservation r1 = new Reservation("RES301", "Alice", "Standard", "S101");
        Reservation r2 = new Reservation("RES302", "Bob", "Deluxe", "D201");

        reservations.put(r1.getReservationId(), r1);
        reservations.put(r2.getReservationId(), r2);

        CancellationService cancelService = new CancellationService(reservations, inventory);

        System.out.println("Before Cancellation:");
        r1.display();
        r2.display();

        // Cancel booking
        cancelService.cancelBooking("RES301");

        // Attempt invalid cancellation
        cancelService.cancelBooking("RES999");

        System.out.println("\nAfter Cancellation:");
        r1.display();
        r2.display();

        cancelService.showRollbackStack();

        System.out.println("\nUpdated Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " : " + inventory.get(type));
        }
    }
}