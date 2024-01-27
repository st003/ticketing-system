package st003.ticketing.controllers.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerTicketController {

    @RequestMapping("/ticket")
    public String getTicket() {
        return "customer/ticket";
    }
}
