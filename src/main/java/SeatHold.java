import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class SeatHold {
    private ArrayList<Seat> reservedSeats;
    private String customerEmail;
    private UUID uuid;
    private int seatCount;
    private boolean isReserveConfirmed = false;
    private String confirmation;
    private Random random;

    public SeatHold(String customerEmail, ArrayList<Seat> seats) {
        this.customerEmail = customerEmail;
        this.reservedSeats = seats;
        this.uuid = UUID.randomUUID();
        this.seatCount = seats.size();
        this.confirmation = customerEmail + random.nextInt(); //email + random number
    }

    public String getCustomerEmail() {
        return this.customerEmail;
    }

    public ArrayList<Seat> getReservedSeats() {
        return this.reservedSeats;
    }

    public UUID getSeatHoldID() {
        return this.uuid;
    }

    public int getSeatCount() {
        return this.seatCount;
    }

    public void setReserveConfirmed(boolean b) {
        this.isReserveConfirmed = b;
    }

    public boolean getReserveConfirmed() {
        return this.isReserveConfirmed;
    }

    public String getConfirmationCode() {
        return this.confirmation;
    }


}
