package app.bank.domain.services;

import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.BusinessException;
import app.bank.domain.models.User;
import app.bank.domain.ports.UserPort;

@Service
public class UpdateUser {

    private final UserPort userPort;

    public UpdateUser(UserPort userPort) {
        this.userPort = userPort;
    }

    public void updateUser(User user) throws BusinessException {
        if (!userPort.existsByIdentificationNumber(user.getIdentificationNumber())) {
            throw new BusinessException("No existe un usuario con ese número de identificación");
        }
        userPort.save(user);
    }
}