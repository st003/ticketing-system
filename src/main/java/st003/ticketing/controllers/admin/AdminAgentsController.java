package st003.ticketing.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import st003.ticketing.data.entities.AppUser;
import st003.ticketing.services.AppUserService;

@Controller
public class AdminAgentsController {

    private AppUserService srv;

    public AdminAgentsController(AppUserService srv) {
        this.srv = srv;
    }

    @GetMapping("/admin/agents")
    public String getAdminAgents(Model model) {
        Iterable<AppUser> appUsers = srv.getAllAgents();
        model.addAttribute("appUsers", appUsers);
        return "admin/agents";
    }
}
