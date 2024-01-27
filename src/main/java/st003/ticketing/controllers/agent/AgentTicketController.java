package st003.ticketing.controllers.agent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AgentTicketController {

    @RequestMapping("/agent/ticket")
    public String getAgentTicket() {
        return "agent/ticket";
    }
}
