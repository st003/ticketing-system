package st003.ticketing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import st003.ticketing.security.AuthenticationUtils;

@Controller
public class RegisterController {

    @RequestMapping("/register")
    public String getRegister() {
        if (AuthenticationUtils.isAuthenticated()) return "redirect:/";
        return "register";
    }
}
