package st003.ticketing.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Implementation of AuthenticationSuccessHandler for controlling the login redirect behavior
 * based on the AppUser's role.
 */
public class loginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        // default to 404 in case the database is set with an illegal role value
        String redirectURL = "/404";

        for (GrantedAuthority ga : authentication.getAuthorities()) {
            if (ga.getAuthority().equals("ROLE_CUSTOMER")) {
                redirectURL = "/tickets";
                break;
            } else if (ga.getAuthority().equals("ROLE_AGENT") || ga.getAuthority().equals("ROLE_ADMIN")) {
                redirectURL = "/agent/tickets";
                break;
            }
        }

        response.sendRedirect(redirectURL);
    }
}
