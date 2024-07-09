package st003.ticketing.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import st003.ticketing.data.TicketStatus;
import st003.ticketing.data.entities.Ticket;
import st003.ticketing.data.repositories.TicketRepository;

@ExtendWith(SpringExtension.class)
public class NewTicketServiceTest {

    @MockBean
    private TicketRepository ticketRepository;

    @Test
    void testGenerateNewTickerNumber() {

        // TODO - can this be made an injected dependency of the test class?
        NewTicketService srv = new NewTicketService(ticketRepository);
        String actual = srv.generateNewTickerNumber();

        assertNotNull(actual);
        assertEquals(10, actual.length());
    }

    @Test
    void testOpenNewTicket() {

        Ticket t = new Ticket();
        when(ticketRepository.save(t)).thenReturn(t);

        // TODO - can this be made an injected dependency of the test class?
        NewTicketService srv = new NewTicketService(ticketRepository);

        Ticket newTicket = srv.openNewTicket(t);

        assertNotNull(newTicket);
        assertNotNull(newTicket.getNumber());
        assertEquals(TicketStatus.OPEN, newTicket.getStatus());
        assertNotNull(newTicket.getOpenDate());
    }
}
