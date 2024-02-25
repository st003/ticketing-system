package st003.ticketing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import st003.ticketing.security.AuthenticationUtils;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String getLogin() {
        return AuthenticationUtils.checkForAuthenticatedRedirect("login");
    }
}
