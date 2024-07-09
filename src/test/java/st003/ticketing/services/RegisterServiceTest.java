package st003.ticketing.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import st003.ticketing.data.Role;
import st003.ticketing.data.entities.AppUser;
import st003.ticketing.data.repositories.AppUserRepository;

@ExtendWith(SpringExtension.class)
public class RegisterServiceTest {

    @MockBean
    private AppUserRepository appUserRepository;

    @Test
    void emailIsTakenAppUserFound() {

        String email = "email@example.com";
        AppUser au   = new AppUser(email);

        when(appUserRepository.findByEmail(email)).thenReturn(Optional.of(au));

        // TODO - can this be made an injected dependency of the test class?
        RegisterService srv = new RegisterService(appUserRepository);

        assertTrue(srv.emailIsTaken(au));
    }

    @Test
    void registerNewCustomerSuccess() {

        AppUser au = new AppUser();

        when(appUserRepository.save(au)).thenReturn(au);

        // TODO - can this be made an injected dependency of the test class?
        RegisterService srv = new RegisterService(appUserRepository);
        AppUser actual = srv.registerNewCustomer(au, "password");

        assertNotNull(actual);
        assertNotNull(actual.getPasswordHash());
        assertEquals(Role.CUSTOMER, actual.getRole());
    }
}
