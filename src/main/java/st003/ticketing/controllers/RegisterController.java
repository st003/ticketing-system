package st003.ticketing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import st003.ticketing.data.entities.AppUser;
import st003.ticketing.security.AuthenticationUtils;
import st003.ticketing.services.RegisterService;

@Controller
public class RegisterController {

    private RegisterService service;

    public RegisterController(RegisterService service) {
        this.service = service;
    }

    @GetMapping("/register")
    public String getRegister(Model model) {

        String templateName = AuthenticationUtils.checkForAuthenticatedRedirect("register");

        // add the model attr if NOT doing a redirect
        if (templateName.equals("register")) model.addAttribute("appUser", new AppUser());

        return templateName;
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute AppUser appUser, @RequestParam String password, Model model) {

        if (service.emailIsTaken(appUser)) {
            model.addAttribute("error", "An account with this email already exists");
            return "register";
        }

        // password is passed as a @RequestParam because the submitted plaintext value
        // must be run through the hashing logic
        service.registerNewCustomer(appUser, password);

        return "register-success";
    }
}
