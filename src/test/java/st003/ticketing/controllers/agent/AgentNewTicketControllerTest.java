package st003.ticketing.controllers.agent;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class AgentNewTicketControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(roles = {"AGENT"})
    void getAgentNewTicket() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/agent/new-ticket"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"CUSTOMER"})
    void getAgentNewTicketCustomerRole() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/agent/new-ticket"))
            .andExpect(status().isForbidden());
    }
}
