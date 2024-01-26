package com.st003.ticketing.controllers.agent;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(AgentTicketsController.class)
public class AgentTicketsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser
    void getAgentTickets() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/agent/tickets"))
            .andExpect(status().isOk());
    }
}
