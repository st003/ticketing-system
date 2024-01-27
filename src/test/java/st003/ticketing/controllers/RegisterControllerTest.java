package st003.ticketing.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@WebMvcTest(controllers = RegisterController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class RegisterControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getRegister() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/register"))
            .andExpect(status().isOk());
    }
}
