package st003.ticketing.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import st003.ticketing.data.entities.AppUser;
import st003.ticketing.data.repositories.AppUserRepository;

@Service
public class AppUserService {

    private AppUserRepository repo;

    public AppUserService(AppUserRepository repo) {
        this.repo = repo;
    }

    /**
     * Service wrapper method for saving 
     *
     * @param  appuser An instance of AppUser
     * @return         An instance of AppUser
     */
    public AppUser saveAppUser(AppUser appuser) {
        return repo.save(appuser);
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
            // TODO - add exception handling for parseLong
            Optional<AppUser> foundAppUser = repo.findById(Long.parseLong(id.get()));
            if (foundAppUser.isPresent()) return foundAppUser.get();
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
