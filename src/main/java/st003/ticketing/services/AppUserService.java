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
}
