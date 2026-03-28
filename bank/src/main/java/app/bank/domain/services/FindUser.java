package app.bank.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.BusinessException;
import app.bank.domain.models.User;
import app.bank.domain.ports.UserPort;

@Service
public class FindUser {

    @Autowired
    private UserPort userPort;

    public FindUser(UserPort userPort) {
        this.userPort = userPort;
    }

    public User findByIdentificationNumber(String identificationNumber) throws BusinessException {
        User user = userPort.findByIdentificationNumber(identificationNumber);
        if (user == null) {
            throw new BusinessException("No existe un usuario con ese número de identificación");
        }
        return user;
    }
}
