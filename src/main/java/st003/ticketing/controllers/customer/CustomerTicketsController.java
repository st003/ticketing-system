package st003.ticketing.controllers.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerTicketsController {

    @RequestMapping("/tickets")
    public String getTickets() {
        return "customer/tickets";
    }
}
