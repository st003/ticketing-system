package st003.ticketing.data.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import st003.ticketing.data.entities.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

    @Query("SELECT ap FROM AppUser ap WHERE ap.role IN :roles")
    Iterable<AppUser> findByRoleIn(@Param("roles") int... roles);
}
