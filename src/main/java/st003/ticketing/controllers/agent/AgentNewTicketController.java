package st003.ticketing.controllers.agent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AgentNewTicketController {

    @RequestMapping("/agent/new-ticket")
    public String getAgentNewTicket() {
        return "agent/new-ticket";
    }
}
