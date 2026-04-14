package app.bank.application.adapters.persistence.sql.repositories;

import app.bank.application.adapters.persistence.sql.entities.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccountEntity, String> {
    boolean existsByAccountNumber(String accountNumber);
    BankAccountEntity findByAccountNumber(String accountNumber);
}
