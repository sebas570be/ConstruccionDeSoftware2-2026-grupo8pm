package app.bank.application.usecases;

import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.BusinessException;
import app.bank.domain.models.User;
import app.bank.domain.services.CreateUser;
import app.bank.domain.services.FindUser;
import app.bank.domain.services.UpdateUser;

@Service
public class UserUseCase {

    private final CreateUser createUser;
    private final FindUser findUser;
    private final UpdateUser updateUser;

    public UserUseCase(CreateUser createUser,
                       FindUser findUser,
                       UpdateUser updateUser) {
        this.createUser = createUser;
        this.findUser = findUser;
        this.updateUser = updateUser;
    }

    public void createUser(User user) throws BusinessException {
        createUser.createUser(user);
    }

    public User findByIdentificationNumber(String identificationNumber) throws BusinessException {
        return findUser.findByIdentificationNumber(identificationNumber);
    }

    public void updateUser(User user) throws BusinessException {
        updateUser.updateUser(user);
    }
}
