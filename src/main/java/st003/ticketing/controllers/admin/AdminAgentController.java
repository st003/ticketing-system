package st003.ticketing.controllers.admin;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import st003.ticketing.data.entities.AppUser;
import st003.ticketing.data.repositories.AppUserRepository;
import st003.ticketing.services.AppUserService;

@Controller
public class AdminAgentController {

    private AppUserRepository repo;

    private final AppUserService srv;

    public AdminAgentController(AppUserRepository repo, AppUserService srv) {
        this.repo = repo;
        this.srv = srv;
    }

    @GetMapping(value = {"/admin/agent", "/admin/agent/{id}"})
    public String getAdminAgent(@PathVariable(required = false) Optional<String> id, Model model) {
        AppUser u = srv.getExistingOrEmptyAppUser(id);
        model.addAttribute("appUser", u);
        return "admin/agent";
    }

    @PostMapping("/admin/agent")
    public String postAdminAgent(@ModelAttribute AppUser appUser, @RequestParam(required = false) Optional<String> password, Model model) {


        if (srv.appUserEmailIsTaken(appUser)) {
            model.addAttribute("error", "An account with this email already exists");
            return "admin/agent";
        }

        if (password.isPresent()) appUser.setPassword(password.get());
        repo.save(appUser);

        model.addAttribute("success", "Your changes have been saved");
        return "admin/agent";
    }
}
