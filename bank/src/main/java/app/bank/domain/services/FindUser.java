package app.bank.domain.services;

import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.NotFoundException;
import app.bank.domain.models.User;
import app.bank.domain.ports.UserPort;

@Service
public class FindUser {

    private final UserPort userPort;

    public FindUser(UserPort userPort) {
        this.userPort = userPort;
    }

    public User findByIdentificationNumber(String identificationNumber) throws NotFoundException {
        User user = userPort.findByIdentificationNumber(identificationNumber);
        if (user == null) {
            throw new NotFoundException("No existe un usuario con ese número de identificación");
        }
        return user;
    }
}