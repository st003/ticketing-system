package com.st003.ticketing.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.st003.ticketing.data.entities.AppUser;
import com.st003.ticketing.data.repositories.AppUserRepository;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private AppUserRepository repo;

    public DatabaseSeeder(AppUserRepository repo) {
        this.repo = repo;
    }

    /**
     * If the APP_USER table is empty, create the default administrator with an email/password of:
     *
     * Email: "admin"
     * Password: "password"
     *
     * @param args Arguments unused at this time
     */
    @Override
    public void run(String... args) {
        if (!repo.findAll().iterator().hasNext()) {
            AppUser defaultAdmin = new AppUser("admin", "password");
            repo.save(defaultAdmin);
        }
    }
}
