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

    public boolean emailIsTaken(AppUser appUser) {
        Optional<AppUser> result = repo.findByEmail(appUser.getEmail());
        return result.isPresent();
    }

    // TODO - should this have a return value?
    public void registerNewCustomer(AppUser appUser, String plaintextPassword) {

        appUser.setPassword(plaintextPassword);
        appUser.setRole(Role.CUSTOMER);

        repo.save(appUser);
    }
}
