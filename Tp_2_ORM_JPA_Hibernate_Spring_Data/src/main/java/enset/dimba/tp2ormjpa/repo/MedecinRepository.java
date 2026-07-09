package enset.dimba.tp2ormjpa.repo;

import enset.dimba.tp2ormjpa.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    Optional<Medecin> findByNom(String nom);
}
