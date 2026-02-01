import model.RegularTicket;
import model.StudentTicket;
import model.Ticket;
import repository.TicketRepository;
import service.TicketService;
import utils.DatabaseConnection;
import controller.TicketController;
import utils.SortingUtils;
import utils.ReflectionUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            IO.println("Connected to DB successfully!");


            TicketController controller = new TicketController(new TicketService(new TicketRepository()));

            Ticket t1 = new RegularTicket(1, 3, "Aiman");
            Ticket t2 = new StudentTicket(1, 6, "Anvar");

            controller.sell(t1);
            controller.sell(t2);

            List<Ticket> tickets=controller.list();

            SortingUtils.sort(tickets,
                    (a, b) -> Double.compare(a.calculatePrice(), b.calculatePrice()));

            ReflectionUtils.inspect(t1);

            for (Ticket t : tickets) {
                IO.println(
                        t.getCustomerName() + " seat: " + t.getSeatNumber() + " price: " + t.calculatePrice()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
