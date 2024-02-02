package st003.ticketing.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
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
}
