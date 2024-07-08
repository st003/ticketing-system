package st003.ticketing.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import st003.ticketing.data.entities.AppUser;
import st003.ticketing.services.RegisterService;

@WebMvcTest(controllers = RegisterController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class RegisterControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RegisterService registerService;

    @Test
    void getRegister() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/register"))
            .andExpect(status().isOk());
    }

    @Test
    void postRegister() throws Exception {

        when(registerService.emailIsTaken(null)).thenReturn(false);

        mvc.perform(MockMvcRequestBuilders.post("/register")
                    .flashAttr("appUser", new AppUser("test"))
                    .param("password", "password")
                    .with(csrf())
                ).andExpect(status().isOk());
    }
}
