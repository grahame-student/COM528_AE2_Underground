package uk.ac.solent.lunderground.model.dao;

import uk.ac.solent.lunderground.model.dto.Ticket;

public interface TicketDao
{
    String encodeTicket(Ticket ticket);
    boolean validateTicket(String encodedTicket);
}
