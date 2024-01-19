package com.st003.ticketing.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(controllers = LoginController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class LoginControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getLogin() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/login"))
            .andExpect(status().isOk());
    }
}
