package model;

public abstract class Ticket implements PricedItem, Validatable<Ticket> {
    protected int id;
    protected int sessionId;
    protected int seatNumber;
    protected String customerName;

    public Ticket(int sessionId, int seatNumber, String customerName) {
        this.sessionId = sessionId;
        this.seatNumber = seatNumber;
        this.customerName = customerName;
    }

    @Override
    public abstract double calculatePrice();

    @Override
    public void validate() {
        Validatable.require(seatNumber>0, "seat number must be positive");
        Validatable.require(customerName!=null, "customer name required");
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getSessionId() { return sessionId; }
    public int getSeatNumber() { return seatNumber; }
    public String getCustomerName() { return customerName; }


}
