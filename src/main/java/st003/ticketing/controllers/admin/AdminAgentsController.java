package st003.ticketing.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import st003.ticketing.data.entities.AppUser;
import st003.ticketing.data.repositories.AppUserRepository;

@Controller
public class AdminAgentsController {

    private AppUserRepository repo;

    public AdminAgentsController(AppUserRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/admin/agents")
    public String getAdminAgents(Model model) {

        // TODO - customize query to get only certain types of AppUsers
        Iterable<AppUser> appUsers = repo.findAll();
        model.addAttribute("appUsers", appUsers);

        return "admin/agents";
    }
}
