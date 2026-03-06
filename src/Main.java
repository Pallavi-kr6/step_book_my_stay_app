/**
 * ABSTRACT CLASS - Room
 * Represents a generic hotel room.
 * Models attributes common to all room types.
 */
public abstract class Room {

    /** Number of beds available in the room */
    protected int numberOfBeds;

    /** Total size of the room in square feet */
    protected int squareFeet;

    /** Price charged per night */
    protected double pricePerNight;

    /**
     * Constructor used by child classes
     */
    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    /** Displays room details */
    public void displayRoomDetails() {
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Room Size: " + squareFeet + " sq ft");
        System.out.println("Price Per Night: Rs." + pricePerNight);
    }
}
/**
 * CLASS - SingleRoom
 * Represents a single room in the hotel.
 */
public class SingleRoom extends Room {

    /** Initializes a SingleRoom with predefined attributes */
    public SingleRoom() {
        super(1, 250, 1500.0);
    }
}

/**
 * CLASS - DoubleRoom
 * Represents a double room in the hotel.
 */
public class DoubleRoom extends Room {

    /** Initializes a DoubleRoom with predefined attributes */
    public DoubleRoom() {
        super(2, 400, 2500.0);
    }
}

public class main {

    public static void main(String[] args) {
  System.out.println("");
        Room r1 = new SingleRoom();
        Room r2 = new DoubleRoom();

        System.out.println("Single Room Details:");
        r1.displayRoomDetails();

        System.out.println("\nDouble Room Details:");
        r2.displayRoomDetails();
    }
}