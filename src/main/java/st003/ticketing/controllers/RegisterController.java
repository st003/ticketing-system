package st003.ticketing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import st003.ticketing.data.Role;
import st003.ticketing.data.entities.AppUser;
import st003.ticketing.data.repositories.AppUserRepository;
import st003.ticketing.security.AuthenticationUtils;

@Controller
public class RegisterController {

    private AppUserRepository repo;

    public RegisterController(AppUserRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        if (AuthenticationUtils.isAuthenticated()) return "redirect:/";

        model.addAttribute("appUser", new AppUser());

        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute AppUser appUser, @RequestParam String password) {

        // TODO - check for existing AppUser by email

        // password is passed as a @RequestParam because the submitted plaintext value
        // must be run through the hashing logic
        appUser.setPassword(password);
        appUser.setRole(Role.ROLE_CUSTOMER);
        repo.save(appUser);

        return "register-success";
    }
}
