package st003.ticketing.controllers.admin;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import st003.ticketing.data.Role;
import st003.ticketing.data.entities.AppUser;
import st003.ticketing.data.repositories.AppUserRepository;

@Controller
public class AdminAgentController {

    private AppUserRepository repo;

    public AdminAgentController(AppUserRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/admin/agent")
    public String getAdminAgent(Model model) {

        model.addAttribute("appUser", new AppUser());

        return "admin/agent";
    }

    @PostMapping("/admin/agent")
    public String postAdminAgent(@ModelAttribute AppUser appUser, @RequestParam String password, @RequestParam Role role, Model model) {

        // check if email is taken
        Optional<AppUser> emailTaken = repo.findByEmail(appUser.getEmail());
        if (emailTaken.isPresent()) {
            model.addAttribute("error", "An account with this email already exists");

        } else {
            appUser.setPassword(password);
            appUser.setRole(role);
            repo.save(appUser);

            model.addAttribute("success", "New agent added successfully");
        }

        return "admin/agent";
    }
}
