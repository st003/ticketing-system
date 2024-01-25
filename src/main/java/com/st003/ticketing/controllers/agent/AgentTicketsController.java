package com.st003.ticketing.controllers.agent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AgentTicketsController {

    @RequestMapping("/agent/tickets")
    public String getIndex() {
        return "agent/tickets";
    }
}
