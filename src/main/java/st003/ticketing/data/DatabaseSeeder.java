package st003.ticketing.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import st003.ticketing.data.entities.AppUser;
import st003.ticketing.data.repositories.AppUserRepository;

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
            AppUser defaultAdmin = new AppUser("admin");
            defaultAdmin.setPassword("password");
            defaultAdmin.setFirstName("Admin");
            defaultAdmin.setLastName("Admin");
            defaultAdmin.setRole(Role.ADMIN);
            repo.save(defaultAdmin);

            // TODO - TEMPORARY - Remove test agent after core design is done
            AppUser agent = new AppUser("agent");
            agent.setPassword("password");
            agent.setRole(Role.AGENT);
            repo.save(agent);

            AppUser customer = new AppUser("customer");
            customer.setPassword("password");
            customer.setRole(Role.CUSTOMER);
            repo.save(customer);
        }
    }
}
