import java.util.UUID;

public class TicketServiceTest {
    public static void main(String[] args) {
        Venue venue = new Venue(10,10);


        SeatHold sarah = venue.findAndHoldSeats(4, "sarah@gmail.com");
        SeatHold bob = venue.findAndHoldSeats(4, "bob@gmail.com");
        SeatHold karen = venue.findAndHoldSeats(4, "karen@gmail.com");
        SeatHold derek = venue.findAndHoldSeats(4, "derek@gmail.com");

        venue.reserveSeats(sarah.getSeatHoldID(), "sarah@gmail.com");
        venue.reserveSeats(bob.getSeatHoldID(), "bob@gmail.com");
        venue.reserveSeats(karen.getSeatHoldID(), "karen@gmail.com");
        venue.reserveSeats(derek.getSeatHoldID(), "derek@gmail.com");

        venue.numSeatsAvailable();

        TicketServiceTest.assertReserveHold(venue, sarah.getSeatHoldID(), "sarah@gmail.com");
        //TicketServiceTest.assertReserveHold();


    }


    private static void assertHoldRelease(Venue venue, int number, String customerEmail) {
        SeatHold sarah = venue.findAndHoldSeats(4, "sarah@gmail.com");

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        if(venue.numSeatsAvailable() != 100)
                        {
                            throw new AssertionError("Seats Not Releasing After delay");
                        }
                    }
                },
                5000
        );

    }

    private static void assertReserveHold(Venue venue, UUID uuid, String customerEmail) {
        if (venue.numSeatsAvailable() != 96) {
            throw new AssertionError("Seats not removing");
        }
    }
}
