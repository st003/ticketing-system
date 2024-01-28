package st003.ticketing.controllers.customer;

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
public class CustomerNewTicketControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(roles = {"CUSTOMER"})
    void getNewTicketCustomerRole() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/new-ticket"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"AGENT"})
    void getNewTicketAgentRole() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/new-ticket"))
            .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getNewTicketAdminRole() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/new-ticket"))
            .andExpect(status().isForbidden());
    }
}
