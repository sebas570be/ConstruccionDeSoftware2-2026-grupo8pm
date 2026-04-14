package app.bank.application.adapters.persistence.sql;

import app.bank.domain.models.User;
import app.bank.domain.models.enums.Role;
import app.bank.domain.models.enums.UserStatus;
import app.bank.domain.ports.UserPort;
import app.bank.application.adapters.persistence.sql.entities.UserEntity;
import app.bank.application.adapters.persistence.sql.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserPersistenceAdapter implements UserPort {

    private final UserRepository repository;

    public UserPersistenceAdapter(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsByIdentificationNumber(String identificationNumber) {
        return repository.existsByIdentificationNumber(identificationNumber);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public void save(User user) {
        repository.save(toEntity(user));
    }

    @Override
    public User findByIdentificationNumber(String identificationNumber) {
        return toModel(repository.findByIdentificationNumber(identificationNumber));
    }

    private UserEntity toEntity(User user) {
        UserEntity e = new UserEntity();
        e.setFullName(user.getFullName());
        e.setIdentificationNumber(user.getIdentificationNumber());
        e.setPhone(user.getPhone());
        e.setEmail(user.getEmail());
        e.setAddress(user.getAddress());
        e.setBirthDate(user.getBirthDate());
        e.setUsername(user.getUsername());
        e.setPassword(user.getPassword());
        e.setRole(user.getRole() != null ? user.getRole().toString() : null);
        e.setStatus(user.getStatus() != null ? user.getStatus().toString() : null);
        e.setCompanyId(user.getCompany() != null ? user.getCompany().getId() : null);
        return e;
    }

    private User toModel(UserEntity e) {
        if (e == null) return null;
        User user = new User();
        user.setId(e.getId());
        user.setFullName(e.getFullName());
        user.setIdentificationNumber(e.getIdentificationNumber());
        user.setPhone(e.getPhone());
        user.setEmail(e.getEmail());
        user.setAddress(e.getAddress());
        user.setBirthDate(e.getBirthDate());
        user.setUsername(e.getUsername());
        user.setPassword(e.getPassword());
        user.setRole(e.getRole() != null ? Role.valueOf(e.getRole()) : null);
        user.setStatus(e.getStatus() != null ? UserStatus.valueOf(e.getStatus()) : null);
        return user;
    }
}
