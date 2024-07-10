package st003.ticketing.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import st003.ticketing.data.entities.AppUser;
import st003.ticketing.data.repositories.AppUserRepository;

@ExtendWith(SpringExtension.class)
public class AppUserServiceTest {

    @MockBean
    private AppUserRepository appUserRepository;

    private AppUserService srv;

    @BeforeEach
    void setup() {
        this.srv = new AppUserService(appUserRepository);
    }

    @Test
    void getExistingOrEmptyAppUserSuccessfullyFoundAppUser() {
        AppUser found = new AppUser();
        found.setId(1L);
        when(appUserRepository.findById(1L)).thenReturn(Optional.of(found));

        AppUser result = srv.getExistingOrEmptyAppUser(Optional.of("1"));

        assertAll(
            () -> assertNotNull(result),
            () -> assertEquals(1, result.getId())
        );
    }

    @Test
    void getExistingOrEmptyAppUserReturningEmptyAppUser() {
        AppUser result = srv.getExistingOrEmptyAppUser(Optional.empty());

        assertAll(
            () -> assertNotNull(result),
            () -> assertNull(result.getId(), "AppUser's id should be null")
        );
    }
}
