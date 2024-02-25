package st003.ticketing.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Authentication utilities
 */
public class AuthenticationUtils {

    /**
     * Checks if the current AppUser is authenticated and not anonymous
     *
     * @return A boolean
     */
    public static boolean isAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth instanceof AnonymousAuthenticationToken) {
            return false;
        }
        return auth.isAuthenticated();
    }

    /**
     * Redirects authenticated AppUsers to their "home" page. For use on non-authenticated
     * controllers that shouldn't be accessible by an authenticated AppUser.
     *
     * @param targeTemplateName The Thymeleaf template name for the controller
     * @return                  The template name or a redirect String for the AppUser's "home" page
     */
    public static String checkForAuthenticatedRedirect(String targeTemplateName) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth instanceof AnonymousAuthenticationToken) {
            return targeTemplateName;
        }

        if (auth.isAuthenticated()) {
            for (GrantedAuthority ga : auth.getAuthorities()) {
                if (ga.getAuthority().equals("ROLE_CUSTOMER")) {
                    return "redirect:/tickets";

                } else if (ga.getAuthority().equals("ROLE_AGENT") || ga.getAuthority().equals("ROLE_ADMIN")) {
                    return "redirect:/agent/tickets";
                }
            }
        }

        return targeTemplateName;
    }
}
