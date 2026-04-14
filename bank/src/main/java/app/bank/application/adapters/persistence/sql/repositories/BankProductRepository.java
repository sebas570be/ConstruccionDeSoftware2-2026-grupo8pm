package app.bank.application.adapters.persistence.sql.repositories;

import app.bank.application.adapters.persistence.sql.entities.BankProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankProductRepository extends JpaRepository<BankProductEntity, String> {
    boolean existsByProductCode(String productCode);
    BankProductEntity findByProductCode(String productCode);
}
