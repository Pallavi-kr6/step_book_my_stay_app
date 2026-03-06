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

public class Main {

    public static void main(String[] args) {

        Room r1 = new SingleRoom();
        Room r2 = new DoubleRoom();

        System.out.println("Single Room Details:");
        r1.displayRoomDetails();

        System.out.println("\nDouble Room Details:");
        r2.displayRoomDetails();
    }
}