import java.io.*;
import java.util.*;


class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    String reservationId;
    String guestName;
    String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println(reservationId + " | " + guestName + " | " + roomType);
    }
}


class SystemState implements Serializable {

    private static final long serialVersionUID = 1L;

    Map<String, Integer> inventory;
    List<Reservation> bookingHistory;

    public SystemState(Map<String, Integer> inventory, List<Reservation> bookingHistory) {
        this.inventory = inventory;
        this.bookingHistory = bookingHistory;
    }
}


class PersistenceService {

    private static final String FILE_NAME = "hotel_state.dat";

    // Save system state
    public void saveState(SystemState state) {

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(state);
            System.out.println("System state saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving system state: " + e.getMessage());
        }
    }

    public SystemState loadState() {

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            System.out.println("System state restored successfully.");
            return (SystemState) ois.readObject();

        } catch (Exception e) {

            System.out.println("No previous state found. Starting fresh.");

            Map<String, Integer> inventory = new HashMap<>();
            inventory.put("Standard", 3);
            inventory.put("Deluxe", 2);

            return new SystemState(inventory, new ArrayList<>());
        }
    }
}

  class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        PersistenceService persistenceService = new PersistenceService();

        SystemState state = persistenceService.loadState();

        Map<String, Integer> inventory = state.inventory;
        List<Reservation> bookingHistory = state.bookingHistory;

        System.out.println("\nCurrent Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " : " + inventory.get(type));
        }

        Reservation newBooking = new Reservation("RES501", "Alice", "Standard");

        if (inventory.get("Standard") > 0) {
            inventory.put("Standard", inventory.get("Standard") - 1);
            bookingHistory.add(newBooking);
            System.out.println("\nBooking confirmed for Alice.");
        } else {
            System.out.println("\nNo Standard rooms available.");
        }

        System.out.println("\nBooking History:");
        for (Reservation r : bookingHistory) {
            r.display();
        }

        persistenceService.saveState(new SystemState(inventory, bookingHistory));
    }
}