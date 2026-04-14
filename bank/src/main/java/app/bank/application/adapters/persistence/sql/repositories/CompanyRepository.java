package app.bank.application.adapters.persistence.sql.repositories;

import app.bank.application.adapters.persistence.sql.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    boolean existsByNit(String nit);
    boolean existsByUsername(String username);
    CompanyEntity findByNit(String nit);
}
