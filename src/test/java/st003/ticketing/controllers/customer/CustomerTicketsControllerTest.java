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
public class CustomerTicketsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(roles = {"CUSTOMER"})
    void getTicketsCustomerRole() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/tickets"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"AGENT"})
    void getTicketsAgentRole() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/tickets"))
            .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getTicketsAdminRole() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/tickets"))
            .andExpect(status().isForbidden());
    }
}
