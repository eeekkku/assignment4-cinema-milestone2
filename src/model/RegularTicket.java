package model;

public class RegularTicket extends Ticket {

    public RegularTicket(int sessionId, int seatNumber, String customerName) {
        super(sessionId, seatNumber, customerName);
    }

    @Override
    public double calculatePrice() {
        return 2200;
    }

}
