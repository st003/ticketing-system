package st003.ticketing.services;

import java.time.Instant;

import org.springframework.stereotype.Service;

import st003.ticketing.data.TicketStatus;
import st003.ticketing.data.entities.Ticket;
import st003.ticketing.data.repositories.TicketRepository;

@Service
public class TicketService {

    private TicketRepository repo;

    public TicketService(TicketRepository repo) {
        this.repo = repo;
    }

    /**
     * Creates a new Ticket with a unqiue ticket number, a status of open,
     * and a open date of the current timestamp.
     *
     * @param  ticket An instance of a Ticket
     * @return        A copy of the newly created Ticket
     */
    public Ticket openNewTicket(Ticket ticket) {

        ticket.setNumber(generateNewTickerNumber());
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setOpenDate(Instant.now());

        // TODO - how should we handle a possible illegal argument exception?
        return repo.save(ticket);
    }

    /**
     * Generates a unique ticket number using the Unix epoch. Only the
     * right-most 10-digits are used.
     *
     * @return A String
     */
    public String generateNewTickerNumber() {

        Long epochMilli = Instant.now().toEpochMilli();
        String asString = epochMilli.toString();
        String number   = asString.substring(asString.length() - 10);

        return number;
    }
}
