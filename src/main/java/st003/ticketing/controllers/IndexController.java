package st003.ticketing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import st003.ticketing.security.AuthenticationUtils;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String getIndex() {
        return AuthenticationUtils.checkForAuthenticatedRedirect("index");
    }
}
