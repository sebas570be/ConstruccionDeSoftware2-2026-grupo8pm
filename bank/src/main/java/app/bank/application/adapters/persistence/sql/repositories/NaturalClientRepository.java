package app.bank.application.adapters.persistence.sql.repositories;

import app.bank.application.adapters.persistence.sql.entities.NaturalClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NaturalClientRepository extends JpaRepository<NaturalClientEntity, Long> {
    boolean existsByIdentificationNumber(String identificationNumber);
    boolean existsByUsername(String username);
    NaturalClientEntity findByIdentificationNumber(String identificationNumber);
}
