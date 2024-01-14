package com.st003.ticketing.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(TicketsController.class)
public class TicketsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser
    void getTickets() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/tickets"))
            .andExpect(status().isOk());
    }
}
