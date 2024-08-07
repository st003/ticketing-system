package st003.ticketing.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import st003.ticketing.data.Role;
import st003.ticketing.data.entities.AppUser;
import st003.ticketing.data.repositories.AppUserRepository;

@Service
public class AppUserService {

    private AppUserRepository repo;

    public AppUserService(AppUserRepository repo) {
        this.repo = repo;
    }

    /**
     * Service wrapper method for saving an AppUser
     *
     * @param  appuser An instance of AppUser
     * @return         An instance of AppUser
     */
    public AppUser saveAppUser(AppUser appuser) {
        return repo.save(appuser);
    }

    /**
     * Returns all AppUsers who's roles are AGENT or ADMIN
     *
     * @return An Iterable of AppUser objects
     */
    public Iterable<AppUser> getAllAgents() {
        return repo.findByRoleIn(Role.AGENT, Role.ADMIN);
    }

    /**
     * Attempts to locate an AppUser by id. If id is not provided, or
     * no AppUser is found in the database, return an empty AppUser object.
     *
     * @param  id An Optional which may contain a String representing the id
     * @return    An instance of AppUser
     */
    public AppUser getExistingOrEmptyAppUser(Optional<String> id) {
        if (id.isPresent()) {
            try {
                Long appUserId = Long.parseLong(id.get());
                Optional<AppUser> foundAppUser = repo.findById(appUserId);
                if (foundAppUser.isPresent()) return foundAppUser.get();

            } catch (NumberFormatException e) {
                return new AppUser();
            }
        }
        return new AppUser();
    }

    /**
     * Checks if an email is already in user by a different AppUser
     *
     * @param  appUser An instance of AppUser
     * @return         A boolean
     */
    public boolean appUserEmailIsTaken(AppUser appUser) {
        Optional<AppUser> result = repo.findByEmail(appUser.getEmail());
        if (result.isPresent()) {
            // you may re-submit an email when updating an AppUser
            return (appUser.getId() != result.get().getId());
        }
        return false;
    }
}
