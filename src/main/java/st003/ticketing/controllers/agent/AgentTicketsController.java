package st003.ticketing.controllers.agent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import st003.ticketing.data.entities.Ticket;
import st003.ticketing.data.repositories.TicketRepository;

@Controller
public class AgentTicketsController {

    private TicketRepository repo;

    public AgentTicketsController(TicketRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/agent/tickets")
    public String getAgentTickets(Model model) {

        Iterable<Ticket> tickets = repo.findAll();
        model.addAttribute("tickets", tickets);

        return "agent/tickets";
    }
}
