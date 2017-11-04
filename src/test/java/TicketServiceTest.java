import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

public class TicketServiceTest {
    private Venue venue = new Venue(10,10);

    public static void main(String[] args) {
        TicketServiceTest tst = new TicketServiceTest();

        //SeatHold sarah = venue.findAndHoldSeats(4, "sarah@gmail.com");
        tst.assertReserveHold();
        tst.assertBestSeatsObject();
        tst.assertConfirmReserve();


        //venue = new Venue(10, 10);
        tst.assertHoldRelease();


    }

    @Test
    public void assertConfirmReserve() {
        SeatHold sarah = venue.findAndHoldSeats(4, "sarah@gmail.com");
        UUID SeatHoldID = sarah.getSeatHoldID();
        String conf = venue.reserveSeats(SeatHoldID, "sarah@gmail.com");
        String testconf = "sarah@gmail.comconfirmed";
        if( !conf.equals(testconf)) {
            throw new AssertionError("Reserved confirmation error");
        }
    }

    @Test
    public void assertBestSeatsObject() {

        SeatHold sarah = venue.findAndHoldSeats(4, "sarah@gmail.com");
        UUID SeatHoldID = sarah.getSeatHoldID();
        String customerEmail = "sarah@gmail.com";
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

    @Test
    public void assertReserveHold() {
        SeatHold sarah = venue.findAndHoldSeats(4, "sarah@gmail.com");
        System.out.println(venue.numSeatsAvailable());
        if (venue.numSeatsAvailable() != 96) {
            throw new AssertionError("Seats not removing");
        }
    }


    @Test
    public void assertHoldRelease() {
        SeatHold sarah = venue.findAndHoldSeats(5, "sarah@gmail.com");

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
