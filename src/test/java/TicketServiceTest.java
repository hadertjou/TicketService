import java.util.ArrayList;
import java.util.UUID;

public class TicketServiceTest {
    public static void main(String[] args) {
        Venue venue = new Venue(10,10);


        SeatHold sarah = venue.findAndHoldSeats(4, "sarah@gmail.com");
        TicketServiceTest.assertReserveHold(venue, "sarah@gmail.com");
        TicketServiceTest.assertBestSeatsObject(venue, sarah.getSeatHoldID(), "sarah@gmail.com");
        TicketServiceTest.assertConfirmReserve(venue, sarah.getSeatHoldID(), "sarah@gmail.com");
        

        venue = new Venue(10, 10);
        TicketServiceTest.assertHoldRelease(venue, 5, "sarah@gmail.com");





    }

    private static void assertConfirmReserve(Venue venue, UUID SeatHoldID, String customerEmail) {
        String conf = venue.reserveSeats(SeatHoldID, customerEmail);
        String testconf = "sarah@gmail.comconfirmed";
        if( !conf.equals(testconf)) {
            throw new AssertionError("Reserved confirmation error");
        }
    }


    private static void assertBestSeatsObject(Venue venue, UUID SeatHoldID, String customerEmail) {
        ArrayList<Seat> testseats = new ArrayList<>();
        testseats.add(new Seat(0, 5));
        testseats.add(new Seat(1, 5));
        testseats.add(new Seat(0, 6));
        testseats.add(new Seat(0, 4));
        SeatHold test = new SeatHold(customerEmail, testseats);
        SeatHold actual = venue.getSeatHoldByID(SeatHoldID);
        for(int i = 0; i < test.getSeatCount(); i++) {
            Seat testseat = test.getReservedSeats().get(i);
            Seat actualseat = test.getReservedSeats().get(i);
            if (testseat.column != actualseat.column || testseat.row != actualseat.row) {
                throw new AssertionError("Best Seats don't match up");
            }
        }
    }

    private static void assertReserveHold(Venue venue, String customerEmail) {

        System.out.println(venue.numSeatsAvailable());
        if (venue.numSeatsAvailable() != 96) {
            throw new AssertionError("Seats not removing");
        }
    }



    private static void assertHoldRelease(Venue venue, int number, String customerEmail) {
        SeatHold sarah = venue.findAndHoldSeats(number, "sarah@gmail.com");

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        System.out.println(venue.numSeatsAvailable());
                        if(venue.numSeatsAvailable() != 100)
                        {
                            throw new AssertionError("Seats Not Releasing After delay");
                        }
                    }
                },
                6000
        );

    }
}
