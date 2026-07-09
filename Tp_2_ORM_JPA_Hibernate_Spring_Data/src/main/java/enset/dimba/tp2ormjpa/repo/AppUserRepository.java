package enset.dimba.tp2ormjpa.repo;

import enset.dimba.tp2ormjpa.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
