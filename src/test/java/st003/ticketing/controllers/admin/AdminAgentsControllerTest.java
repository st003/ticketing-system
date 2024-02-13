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
public class AdminAgentsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getAdminAgentsAdminRole() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/admin/agents"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"AGENT"})
    void getAdminAgentsAgentRole() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/admin/agents"))
            .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"CUSTOMER"})
    void getAdminAgentsCustomerRole() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/admin/agents"))
            .andExpect(status().isForbidden());
    }
}
