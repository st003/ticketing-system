package st003.ticketing.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import st003.ticketing.data.repositories.TicketRepository;

@ExtendWith(SpringExtension.class)
public class NewTicketServiceTest {

    @MockBean
    private TicketRepository ticketRepository;

    @Test
    void testGenerateNewTickerNumber() {

        NewTicketService service = new NewTicketService(ticketRepository);

        String actual = service.generateNewTickerNumber();

        assertNotNull(actual);
        assertEquals(10, actual.length());
    }
}
