package st003.ticketing.controllers.admin;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import st003.ticketing.data.Role;
import st003.ticketing.data.entities.AppUser;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminAgentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getAdminAgentAdminRole() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/admin/agent"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"AGENT"})
    void getAdminAgentAgentRole() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/admin/agent"))
            .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"CUSTOMER"})
    void getAdminAgentCustomerRole() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/admin/agent"))
            .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void postAdminAgent() throws Exception {

        AppUser u = new AppUser("test");
        u.setRole(Role.ROLE_AGENT);

        mvc.perform(MockMvcRequestBuilders.post("/admin/agent")
            .flashAttr("appUser", u)
            .param("password", "password")
            .with(csrf())
        ).andExpect(status().isOk());
    }
}
