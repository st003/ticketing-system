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
public class AgentTicketsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(roles = {"AGENT"})
    void getAgentTicketsAgentRole() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/agent/tickets"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getAgentTicketsAdminRole() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/agent/tickets"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"CUSTOMER"})
    void getAgentTicketsCustomerRole() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/agent/tickets"))
            .andExpect(status().isForbidden());
    }
}
