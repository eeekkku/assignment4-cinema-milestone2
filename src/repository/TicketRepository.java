package repository;
import model.*;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketRepository implements CrudRepository<Ticket> {
    @Override
    public Ticket save(Ticket ticket) {
        String sql = "INSERT INTO tickets(session_id, seat_number, customer_name, price, ticket_type) VALUES (?, ?, ?, ?, ?) RETURNING id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ticket.getSessionId());
            stmt.setInt(2, ticket.getSeatNumber());
            stmt.setString(3, ticket.getCustomerName());
            stmt.setDouble(4, ticket.calculatePrice());
            stmt.setString(5,
                    ticket instanceof StudentTicket ? "STUDENT" : "REGULAR"
            );

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) ticket.setId(rs.getInt("id"));
            return ticket;

        } catch (SQLException e) {
            throw new RuntimeException("DB error while saving ticket", e);
        }
    }


    public List<Ticket> findAll() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM tickets";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Ticket t = rs.getString("ticket_type").equals("STUDENT")
                        ? new StudentTicket(
                        rs.getInt("session_id"),
                        rs.getInt("seat_number"),
                        rs.getString("customer_name"))
                        : new RegularTicket(
                        rs.getInt("session_id"),
                        rs.getInt("seat_number"),
                        rs.getString("customer_name"));

                t.setId(rs.getInt("id"));
                tickets.add(t);
            }
            return tickets;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isSeatTaken(int sessionId, int seatNumber) throws SQLException {
        String sql = "SELECT COUNT(*) FROM tickets WHERE session_id = ? AND seat_number = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, sessionId);
            stmt.setInt(2, seatNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    @Override public Ticket findById(int id) {
        throw new UnsupportedOperationException();
    }
    @Override public void deleteById(int id) {
        throw new UnsupportedOperationException();
    }

}
