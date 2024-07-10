package st003.ticketing.data;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RoleTest {

    @Test
    void testGetName() {
        assertAll(
            () -> assertEquals("CUSTOMER", Role.getName(Role.CUSTOMER)),
            () -> assertEquals("AGENT", Role.getName(Role.AGENT)),
            () -> assertEquals("ADMIN", Role.getName(Role.ADMIN)),
            () -> assertEquals("", Role.getName(-1))
        );
    }

    @Test
    void testGetAuthorityName() {
        assertAll(
            () -> assertEquals("ROLE_CUSTOMER", Role.getAuthorityName(Role.CUSTOMER)),
            () -> assertEquals("ROLE_AGENT", Role.getAuthorityName(Role.AGENT)),
            () -> assertEquals("ROLE_ADMIN", Role.getAuthorityName(Role.ADMIN)),
            () -> assertEquals("", Role.getName(-1))
        );
    }
}
