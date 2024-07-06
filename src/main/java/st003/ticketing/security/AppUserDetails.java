package st003.ticketing.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import st003.ticketing.data.Role;
import st003.ticketing.data.entities.AppUser;

public class AppUserDetails implements UserDetails {

    private final AppUser user;

    public AppUserDetails(AppUser user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        String role = Role.getAuthorityName(user.getRole());
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPasswordHash();
    }

    @Override
    public String getUsername() {
        // email and username are synonymous
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO - implement fully
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO - implement fully
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO - implement fully
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO - implement fully
        return true;
    }
}
