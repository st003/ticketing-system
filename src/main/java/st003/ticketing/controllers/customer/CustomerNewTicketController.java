package st003.ticketing.controllers.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerNewTicketController {

    @RequestMapping("/new-ticket")
    public String getNewTicket() {
        return "customer/new-ticket";
    }
}
