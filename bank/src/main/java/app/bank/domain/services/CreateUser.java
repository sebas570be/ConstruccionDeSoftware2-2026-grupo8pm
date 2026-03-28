package app.bank.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.BusinessException;
import app.bank.domain.models.User;
import app.bank.domain.ports.UserPort;

@Service
public class CreateUser {

    @Autowired
    private UserPort userPort;

    public CreateUser(UserPort userPort) {
        this.userPort = userPort;
    }

    public void createUser(User user) throws BusinessException {
        if (userPort.existsByIdentificationNumber(user.getIdentificationNumber())) {
            throw new BusinessException("Ya existe un usuario con ese número de identificación");
        }
        if (userPort.existsByUsername(user.getUsername())) {
            throw new BusinessException("Ya existe un usuario con ese username");
        }
        userPort.save(user);
    }
}
