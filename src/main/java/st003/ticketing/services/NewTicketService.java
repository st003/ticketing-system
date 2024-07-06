package st003.ticketing.services;

import java.time.Instant;

import org.springframework.stereotype.Service;

import st003.ticketing.data.TicketStatus;
import st003.ticketing.data.entities.Ticket;
import st003.ticketing.data.repositories.TicketRepository;

@Service
public class NewTicketService {

    private TicketRepository repo;

    public NewTicketService(TicketRepository repo) {
        this.repo = repo;
    }

    // TODOS -
    // (1) should this have a return value?
    // (2) how to test? With repo mock?
    public void openNewTicket(Ticket ticket) {

        ticket.setNumber(generateNewTickerNumber());
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setOpenDate(Instant.now());

        repo.save(ticket);
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
