package st003.ticketing.controllers.agent;

import java.time.Instant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import st003.ticketing.data.TicketStatus;
import st003.ticketing.data.entities.Ticket;
import st003.ticketing.data.repositories.TicketRepository;

@Controller
public class AgentNewTicketController {

    private TicketRepository repo;

    public AgentNewTicketController(TicketRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/agent/new-ticket")
    public String getAgentNewTicket() {
        return "agent/new-ticket";
    }

    @PostMapping("/agent/new-ticket")
    public String postAgentNewTicket(@ModelAttribute Ticket ticket) {

        // TODO - placeholder values. Create a new ticket service
        ticket.setNumber("0000001");
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setOpenDate(Instant.now());

        repo.save(ticket);

        return "redirect:/agent/tickets";
    }
}
