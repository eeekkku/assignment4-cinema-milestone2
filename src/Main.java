import model.RegularTicket;
import model.StudentTicket;
import model.Ticket;
import repository.TicketRepository;
import service.TicketService;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

void main() {
    try (Connection conn = DatabaseConnection.getConnection()) {
        IO.println("Connected to DB successfully!");

        TicketRepository repo = new TicketRepository();
        TicketService service = new TicketService(repo);


        Ticket t1 = new RegularTicket(1, 5, "Yerkezhan");
        Ticket t2 = new StudentTicket(1, 2, "Valeriya");

        service.sellTicket(t1);
        service.sellTicket(t2);

        try {
            Ticket t3 = new RegularTicket(1, 5, "Aiman");
            service.sellTicket(t3);
        } catch (IllegalArgumentException e) {
            IO.println("Error: " + e.getMessage());
        }

        List<Ticket> tickets = repo.findAll();
        for (Ticket t : tickets) {
            IO.println(t.getCustomerName() + " bought ticket for seat " + t.getSeatNumber() + ", price: " + t.calculatePrice());
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
