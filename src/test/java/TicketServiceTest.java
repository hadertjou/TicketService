import java.util.UUID;

public class TicketServiceTest {
    public static void main(String[] args) {
        Venue venue = new Venue(10,10);


        venue.findAndHoldSeats(4, "sarah@gmail.com");
        venue.findAndHoldSeats(4, "bob@gmail.com");
        venue.findAndHoldSeats(4, "karen@gmail.com");
        venue.findAndHoldSeats(4, "derek@gmail.com");


    }


    private static void assertHoldRelease(Venue venue, int number, String customerEmail) {
        if () {
            throw new AssertionError();
        }
    }

    private static void assertReserveHold(Venue venue, UUID uuid, String customerEmail) {
        if () {
            throw new AssertionError();
        }
    }
}
