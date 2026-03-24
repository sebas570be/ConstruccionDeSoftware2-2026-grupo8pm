package app.bank.domain.ports;

import app.bank.domain.models.User;

public interface UserPort {
    public boolean existsByIdentificationNumber(String identificationNumber);
    public boolean existsByUsername(String username);
    public void save(User user);
    public User findByIdentificationNumber(String identificationNumber);
}
