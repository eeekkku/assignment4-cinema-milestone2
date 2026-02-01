package model;

public class StudentTicket extends Ticket {

    public StudentTicket(int sessionId, int seatNumber, String customerName) {
        super(sessionId, seatNumber, customerName);
    }

    @Override
    public double calculatePrice() {
        return 1800;
    }
}
