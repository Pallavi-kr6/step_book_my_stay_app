/**
 * CLASS - Reservation
 * Represents a guest's intent to book a specific room type
 */
import java.util.LinkedList;
import java.util.Queue;

/**
 * CLASS - BookingRequestQueue
 * Stores booking requests in arrival order (FIFO)
 */
  class BookingRequestQueue {
    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add a reservation to the queue
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Booking request added: " + reservation);
    }

    // Peek at the next reservation (without removing)
    public Reservation peekNext() {
        return queue.peek();
    }

    // Process (remove) the next reservation
    public Reservation processNext() {
        return queue.poll();
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // Display all pending requests
    public void displayQueue() {
        System.out.println("Current Booking Request Queue:");
        for (Reservation r : queue) {
            System.out.println(r);
        }
    }
}
  class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    @Override
    public String toString() {
        return "Guest: " + guestName + ", Room Type: " + roomType;
    }
}

/**
 * MAIN CLASS
 * Demonstrates first-come-first-served booking request queue
 */
public class Main{

    public static void main(String[] args) {

        BookingRequestQueue requestQueue = new BookingRequestQueue();

        // Simulate guest booking requests
        requestQueue.addRequest(new Reservation("Alice", "SingleRoom"));
        requestQueue.addRequest(new Reservation("Bob", "DoubleRoom"));
        requestQueue.addRequest(new Reservation("Charlie", "SingleRoom"));

        System.out.println("\nDisplaying all pending booking requests:");
        requestQueue.displayQueue();

        System.out.println("\nProcessing requests in arrival order (FIFO):");
        while (!requestQueue.isEmpty()) {
            Reservation r = requestQueue.processNext();
            System.out.println("Processing: " + r);
        }

        System.out.println("\nQueue after processing all requests:");
        requestQueue.displayQueue();
    }
}