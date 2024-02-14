package st003.ticketing.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import st003.ticketing.data.Role;
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

        Iterable<AppUser> appUsers = repo.findAllByRoles(Role.ROLE_AGENT, Role.ROLE_ADMIN);
        model.addAttribute("appUsers", appUsers);

        return "admin/agents";
    }
}
