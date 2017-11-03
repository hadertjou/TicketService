public class TicketService {
    public static void main(String[] args) {
        System.out.println("Hello World");
        Venue venue = new Venue(7, 7);
        venue.numSeatsAvailable();
        System.out.println(venue.numSeatsAvailable());
        SeatHold sh = venue.findAndHoldSeats(40, "sarah@gmail.com");
        System.out.println(venue.numSeatsAvailable());
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        // your code here
                        System.out.println("EXECUTING TIMER TASK RESERVE SEAT");
                        //venue.reserveSeats(sh.getSeatHoldID(), "sarah@gmail.com");
                        System.out.println(venue.numSeatsAvailable());
                        venue.printReservedSeats();
                    }
                },
                5000
        );
        System.out.println(venue.numSeatsAvailable());
        venue.printReservedSeats();

    }



}
