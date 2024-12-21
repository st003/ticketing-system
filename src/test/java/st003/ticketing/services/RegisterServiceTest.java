package st003.ticketing.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import st003.ticketing.data.Role;
import st003.ticketing.data.entities.AppUser;
import st003.ticketing.data.repositories.AppUserRepository;

@ExtendWith(SpringExtension.class)
public class RegisterServiceTest {

    @MockitoBean
    private AppUserRepository appUserRepository;

    private RegisterService srv;

    @BeforeEach
    void setup() {
        this.srv = new RegisterService(appUserRepository);
    }

    @Test
    void emailIsTakenAppUserFound() {
        String email = "email@example.com";
        AppUser au   = new AppUser(email);
        when(appUserRepository.findByEmail(email)).thenReturn(Optional.of(au));

        assertTrue(srv.emailIsTaken(au));
    }

    @Test
    void registerNewCustomerSuccess() {
        AppUser au = new AppUser();
        when(appUserRepository.save(au)).thenReturn(au);

        AppUser actual = srv.registerNewCustomer(au, "password");

        assertAll(
            () -> assertNotNull(actual),
            () -> assertNotNull(actual.getPasswordHash()),
            () -> assertEquals(Role.CUSTOMER, actual.getRole())
        );
    }
}
