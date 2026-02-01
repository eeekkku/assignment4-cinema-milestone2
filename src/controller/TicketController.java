package controller;

import model.Ticket;
import service.TicketService;

import java.util.List;

public class TicketController {

    private final TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

    public Ticket sell(Ticket ticket) {
        return service.sellTicket(ticket);
    }


    public List<Ticket> list() {
        return service.getAllTickets();
    }
}
