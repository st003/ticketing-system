package st003.ticketing.controllers.admin;

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
    @WithMockUser(roles = {"ADMIN"})
    void getAdminConfigAdminRole() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/admin/config"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"AGENT"})
    void getAdminConfigAgentRole() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/admin/config"))
            .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"CUSTOMER"})
    void getAdminConfigCustomerRole() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/admin/config"))
            .andExpect(status().isForbidden());
    }
}
