package app.bank.application.adapters.persistence.sql.repositories;

import app.bank.application.adapters.persistence.sql.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByIdentificationNumber(String identificationNumber);
    boolean existsByUsername(String username);
    UserEntity findByIdentificationNumber(String identificationNumber);
    UserEntity findByUsername(String username);
}
