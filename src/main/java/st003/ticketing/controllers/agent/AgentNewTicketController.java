package st003.ticketing.controllers.agent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import st003.ticketing.data.entities.Ticket;
import st003.ticketing.services.NewTicketService;

@Controller
public class AgentNewTicketController {

    private NewTicketService srv;

    public AgentNewTicketController(NewTicketService srv) {
        this.srv = srv;
    }

    @GetMapping("/agent/new-ticket")
    public String getAgentNewTicket() {
        return "agent/new-ticket";
    }

    @PostMapping("/agent/new-ticket")
    public String postAgentNewTicket(@ModelAttribute Ticket ticket) {
        srv.openNewTicket(ticket);
        return "redirect:/agent/tickets";
    }
}
