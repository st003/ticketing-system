package com.st003.ticketing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TicketsController {

    @RequestMapping("/tickets")
    public String getIndex() {
        return "tickets";
    }
}
