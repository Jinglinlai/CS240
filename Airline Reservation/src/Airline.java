import java.util.*;

// Represents a passenger on the plane
class Passenger {
    String name, ticketType, id;
    int seatNumber;

    // Creates a new passenger with their details
    public Passenger(String name, String ticketType, int seatNumber, String id) {
        this.name = name;
        this.ticketType = ticketType;
        this.seatNumber = seatNumber;
        this.id = id;
    }

    // Returns passenger info as a string for display
    public String toString() {
        return id + " | " + name + " | " + ticketType + " | Seat " + seatNumber;
    }
}

public class Airline {
    static List<Passenger> passengers = new ArrayList<>(); // List to store all passengers
    static int idCounter = 50; // Used to give unique IDs to passengers
    static Random rand = new Random(); // Used to randomly assign passengers at start
    static Map<String, Integer> priority = Map.of("First", 1, "Business", 2, "Economy", 3); // Boarding order

    public static void main(String[] args) {
        fillRandomPassengers(); // Fill plane with 20 random passengers
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display menu options
            System.out.println("Airline Reservation System\n--------------------------");
            System.out.println("1. Buy Ticket  \n2. Board Plane  \n3. Cancel Flight  \n4. Show Passengers  \n5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Fixes input issue

            if (choice == 1) buyTicket(scanner);       // Buy a ticket
            else if (choice == 2) boardPlane();        // Board passengers
            else if (choice == 3) cancelFlight(scanner); // Cancel a booking
            else if (choice == 4) passengers.forEach(System.out::println); // Show all passengers
            else break; // Exit program
        }
    }

    // Fills the plane with 20 random passengers at the start
    static void fillRandomPassengers() {
        String[] types = {"First", "Business", "Economy"}; // Ticket types
        for (int i = 0; i < 20; i++) {
            String type = types[rand.nextInt(3)]; // Pick a random ticket type
            int seat = getSeat(type); // Find an available seat
            if (seat != -1) passengers.add(new Passenger("Passenger_" + i, type, seat, "P" + idCounter++)); // Add passenger
        }
    }

    // Allows a user to buy a ticket
    static void buyTicket(Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Ticket Type (First, Business, Economy): ");
        String type = scanner.nextLine();
        int seat = getSeat(type); // Find an available seat

        if (seat == -1) System.out.println("No seats left in " + type + " class.");
        else passengers.add(new Passenger(name, type, seat, "P" + idCounter++)); // Add new passenger
    }

    static int getSeat(String type) {
        int start; // The starting seat number
        int end; // The ending seat number
    
        // Set seat ranges based on ticket type
        if (type.equals("First")) {
            start = 1;
            end = 10;
        } else if (type.equals("Business")) {
            start = 11;
            end = 20;
        } else { // For "Economy"
            start = 21;
            end = 40;
        }
    
        // Loop through the seat range to find an available seat
        for (int i = start; i < end; i++) {
            boolean seatOccupied = false;
            
            // Check if any passenger has this seat
            for (Passenger p : passengers) {
                if (p.seatNumber == i) {
                    seatOccupied = true;
                    break;
                }
            }
            
            // If no passenger has this seat, it's available
            if (!seatOccupied) {
                return i;
            }
        }
        
        return -1; // No available seats
    }

    // Boards passengers in order: First → Business → Economy
    static void boardPlane() {
        PriorityQueue<Passenger> queue = new PriorityQueue<>(Comparator.comparingInt(p -> priority.get(p.ticketType)));
        queue.addAll(passengers); // Add all passengers to priority queue

        System.out.println("\n--- Boarding Order ---");
        while (!queue.isEmpty()) 
        System.out.println(queue.poll()); // remove and return the one with the highest priority
    }

    // Cancels a flight by removing a passenger from the list
    static void cancelFlight(Scanner scanner) {
        System.out.print("Enter Passenger ID to Cancel: ");
        String id = scanner.nextLine();
        passengers.removeIf(p -> p.id.equals(id)); // Remove passenger if ID matches
    }
}
