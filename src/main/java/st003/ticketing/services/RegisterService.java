package st003.ticketing.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import st003.ticketing.data.Role;
import st003.ticketing.data.entities.AppUser;
import st003.ticketing.data.repositories.AppUserRepository;

@Service
public class RegisterService {

    AppUserRepository repo;

    public RegisterService(AppUserRepository repo) {
        this.repo = repo;
    }

    /**
     * Checks if an AppUser with the given email already exists
     *
     * @param  appUser An instance of AppUser
     * @return         A boolean
     */
    public boolean emailIsTaken(AppUser appUser) {
        Optional<AppUser> result = repo.findByEmail(appUser.getEmail());
        return result.isPresent();
    }

    /**
     * Creates a new AppUser with role "customer".
     *
     * @param  appUser           An instance of AppUser
     * @param  plaintextPassword The new AppUser's password in plaintext
     * @return                   The new AppUser to be created
     */
    public AppUser registerNewCustomer(AppUser appUser, String plaintextPassword) {

        appUser.setPassword(plaintextPassword);
        appUser.setRole(Role.CUSTOMER);

        // TODO - how should we handle a possible illegal argument exception?
        return repo.save(appUser);
    }
}
