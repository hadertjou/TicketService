import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Venue {
    private boolean[][] seats;
    private Map<UUID, SeatHold> holds;
    private int seatCount;
    private final long timeout = 3000;

    public Venue(int rows, int columns) {

        this.seats = new boolean[rows][columns];
        this.seatCount = rows*columns;
        holds = new ConcurrentHashMap<>();
    }

    public int numSeatsAvailable() {
        return this.seatCount;
    }

    public SeatHold findAndHoldSeats(int number, String customerEmail) {
        if(number > numSeatsAvailable())
        {
            return null;
        }

        //Check for open seats towards the front/middle
        ArrayList<Seat> optimalSeats = new ArrayList<>();
        for(int i = 0; i < number; i++)
        {
            Seat s = findBestSeat();
            markSeatUnavailable(s.row, s.column);
            optimalSeats.add(s);
        }

        seatCount-=number;
        SeatHold sh = new SeatHold(customerEmail, optimalSeats);
        UUID seatHoldID = sh.getSeatHoldID();
        this.holds.put(sh.getSeatHoldID(), sh);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                clearHolds(seatHoldID, number);
            }
        }, timeout);

        return sh;
    }

    public String reserveSeats(UUID seatHoldID, String customerEmail) {

        SeatHold currentHold = null;

        currentHold = this.holds.get(seatHoldID);
        if(currentHold == null)
        {
            return null;
        }

        //int seatsOffline = currentHold.getSeatCount();
        //seatCount-=seatsOffline;
        if(currentHold.getCustomerEmail() == customerEmail) {
            currentHold.setReserveConfirmed(true);
            String confirmation = currentHold.getConfirmationCode();
            return confirmation;
        }

        return null;
    }

    private void clearHolds(UUID seatHoldID, int number) {

        SeatHold cur = this.holds.get(seatHoldID);
        if(cur.getReserveConfirmed()){
            return;
        }
        ArrayList<Seat> seatsInCur = cur.getReservedSeats();
        for(Seat s : seatsInCur) {
            markSeatAvailable(s.row, s.column);
        }
        this.holds.remove(seatHoldID);
        this.seatCount+=number;

    }

    //FALSE IS AVAILABLE
    private void markSeatAvailable(int row, int column) {
        this.seats[row][column] = false;
    }

    //TRUE IS TAKEN
    private void markSeatUnavailable(int row, int column) {
        this.seats[row][column] = true;
    }

    private Seat findBestSeat() {

        //STARTS WITH CENTER MIDDLE AS MOST OPTIMAL SEAT
        //    STAGE
        //000000X0000000
        //00000000000000
        //00000000000000
        //00000000000000
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0,seats[0].length/2});
        while(!q.isEmpty()) {
            int[] cur = q.poll();

            if(seats[cur[0]][cur[1]] == false)
            {
                //markSeatUnavailable(cur[0], cur[1]);
                return new Seat(cur[0], cur[1]);
            }

            if((cur[0] + 1) < this.seats.length) {
                q.offer(new int[] {cur[0]+1, cur[1]});
            }
            if((cur[1]+1) < this.seats[0].length)
            {
                q.offer(new int[] {cur[0], cur[1]+1});
            }
            if((cur[1]-1) >= 0)
            {
                q.offer(new int[] {cur[0], cur[1]-1});
            }
        }
        return null;
    }

    public SeatHold getSeatHoldByID(UUID uuid) {
        return this.holds.get(uuid);
    }

    public void printReservedSeats() {
        for(int i = 0; i < this.seats.length; i++) {
            for(int j = 0; j < this.seats[i].length; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.print("\n");
        }
    }


}
