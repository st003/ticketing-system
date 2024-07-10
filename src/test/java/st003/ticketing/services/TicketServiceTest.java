package st003.ticketing.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import st003.ticketing.data.TicketStatus;
import st003.ticketing.data.entities.Ticket;
import st003.ticketing.data.repositories.TicketRepository;

@ExtendWith(SpringExtension.class)
public class TicketServiceTest {

    @MockBean
    private TicketRepository ticketRepository;

    private TicketService srv;

    @BeforeEach
    void setup() {
        this.srv = new TicketService(ticketRepository);
    }

    @Test
    void generateNewTickerNumber() {
        String actual = srv.generateNewTickerNumber();
        assertAll(
            () -> assertNotNull(actual),
            () -> assertEquals(10, actual.length())
        );
    }

    @Test
    void openNewTicketSuccess() {
        Ticket t = new Ticket();
        when(ticketRepository.save(t)).thenReturn(t);

        Ticket newTicket = srv.openNewTicket(t);

        assertAll(
            () -> assertNotNull(newTicket),
            () -> assertNotNull(newTicket.getNumber()),
            () -> assertEquals(TicketStatus.OPEN, newTicket.getStatus()),
            () -> assertNotNull(newTicket.getOpenDate())
        );
    }
}
