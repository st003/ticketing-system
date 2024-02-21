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

@Controller
public class AdminAgentController {

    private AppUserRepository repo;

    public AdminAgentController(AppUserRepository repo) {
        this.repo = repo;
    }

    @GetMapping(value = {"/admin/agent", "/admin/agent/{id}"})
    public String getAdminAgent(@PathVariable(required = false) Optional<String> id, Model model) {

        AppUser u = new AppUser();
        if (id.isPresent()) {
            Optional<AppUser> foundAppUser = repo.findById(Long.parseLong(id.get()));
            if (foundAppUser.isPresent()) u = foundAppUser.get();
        }

        model.addAttribute("appUser", u);
        return "admin/agent";
    }

    @PostMapping("/admin/agent")
    public String postAdminAgent(@ModelAttribute AppUser appUser, @RequestParam(required = false) Optional<String> password, Model model) {

        // check if email is taken
        Optional<AppUser> emailTaken = repo.findByEmail(appUser.getEmail());
        if (emailTaken.isPresent()) {
            // when trying to create or update with an in-use email
            if (appUser.getId() == null || appUser.getId() != emailTaken.get().getId()) {
                model.addAttribute("error", "An account with this email already exists");
                return "admin/agent";
            }
        }

        if (password.isPresent()) appUser.setPassword(password.get());
        repo.save(appUser);

        model.addAttribute("success", "Your changes have been saved");
        return "admin/agent";
    }
}
