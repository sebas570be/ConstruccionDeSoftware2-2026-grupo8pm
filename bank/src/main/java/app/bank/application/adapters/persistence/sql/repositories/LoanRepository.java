package app.bank.application.adapters.persistence.sql.repositories;

import app.bank.application.adapters.persistence.sql.entities.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
    boolean existsById(long id);
    LoanEntity findLoanById(long id);
}
