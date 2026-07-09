package enset.dimba.tp2ormjpa.repo;


import enset.dimba.tp2ormjpa.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    Optional<AppRole> findByRoleName(String roleName);
}
