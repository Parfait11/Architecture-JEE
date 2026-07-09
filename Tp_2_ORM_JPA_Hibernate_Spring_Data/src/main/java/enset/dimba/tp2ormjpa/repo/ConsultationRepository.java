package enset.dimba.tp2ormjpa.repo;

import enset.dimba.tp2ormjpa.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
