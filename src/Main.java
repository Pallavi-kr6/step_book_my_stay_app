import java.util.*;

// Reservation class representing a confirmed booking
class Reservation {

    private String reservationId;
    private String guestName;
    private String roomType;
    private int nights;

    public Reservation(String reservationId, String guestName, String roomType, int nights) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.nights = nights;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNights() {
        return nights;
    }

    public void display() {
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Guest Name: " + guestName);
        System.out.println("Room Type: " + roomType);
        System.out.println("Nights: " + nights);
        System.out.println("-----------------------------");
    }
}

// Booking History storage
class BookingHistory {

    private List<Reservation> reservations;

    public BookingHistory() {
        reservations = new ArrayList<>();
    }

    // Add confirmed reservation to history
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    // Retrieve booking history
    public List<Reservation> getReservations() {
        return reservations;
    }
}

// Reporting Service
class BookingReportService {

    // Display all bookings
    public void displayAllBookings(List<Reservation> reservations) {

        System.out.println("----- Booking History -----");

        for (Reservation r : reservations) {
            r.display();
        }
    }

    // Generate summary report
    public void generateSummaryReport(List<Reservation> reservations) {

        System.out.println("----- Booking Summary Report -----");

        int totalBookings = reservations.size();
        int totalNights = 0;

        for (Reservation r : reservations) {
            totalNights += r.getNights();
        }

        System.out.println("Total Bookings: " + totalBookings);
        System.out.println("Total Nights Booked: " + totalNights);
    }
}

 class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // Simulate confirmed bookings
        Reservation r1 = new Reservation("RES101", "Alice", "Deluxe", 2);
        Reservation r2 = new Reservation("RES102", "Bob", "Suite", 3);
        Reservation r3 = new Reservation("RES103", "Charlie", "Standard", 1);

        // Add to booking history
        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);

        // Admin views booking history
        reportService.displayAllBookings(history.getReservations());

        // Admin generates summary report
        reportService.generateSummaryReport(history.getReservations());
    }
}