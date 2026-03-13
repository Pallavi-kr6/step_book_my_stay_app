import java.util.*;

// Represents an optional add-on service
class Service {
    private String serviceName;
    private double price;

    public Service(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getPrice() {
        return price;
    }
}

// Manages add-on services for reservations
class AddOnServiceManager {

    // Map: Reservation ID -> List of Services
    private Map<String, List<Service>> reservationServices = new HashMap<>();

    // Add a service to a reservation
    public void addService(String reservationId, Service service) {

        reservationServices.putIfAbsent(reservationId, new ArrayList<>());

        reservationServices.get(reservationId).add(service);
    }

    // Get services for a reservation
    public List<Service> getServices(String reservationId) {
        return reservationServices.getOrDefault(reservationId, new ArrayList<>());
    }

    // Calculate total cost of services
    public double calculateTotalServiceCost(String reservationId) {

        double total = 0;

        List<Service> services = reservationServices.get(reservationId);

        if (services != null) {
            for (Service s : services) {
                total += s.getPrice();
            }
        }

        return total;
    }
}

 class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "RES101";

        // Available services
        Service breakfast = new Service("Breakfast", 500);
        Service airportPickup = new Service("Airport Pickup", 1200);
        Service spa = new Service("Spa Access", 1500);

        // Guest selects services
        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, airportPickup);
        manager.addService(reservationId, spa);

        // Display selected services
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Selected Add-On Services:");

        for (Service s : manager.getServices(reservationId)) {
            System.out.println("- " + s.getServiceName() + " : ₹" + s.getPrice());
        }

        // Display total add-on cost
        double totalCost = manager.calculateTotalServiceCost(reservationId);

        System.out.println("Total Add-On Cost: ₹" + totalCost);
    }
}