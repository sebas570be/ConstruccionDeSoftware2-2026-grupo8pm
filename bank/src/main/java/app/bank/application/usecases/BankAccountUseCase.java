package app.bank.application.usecases;

import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.BusinessException;
import app.bank.domain.models.BankAccount;
import app.bank.domain.services.CreateBankAccount;
import app.bank.domain.services.FindBankAccount;
import app.bank.domain.services.UpdateBankAccount;

@Service
public class BankAccountUseCase {

    private final CreateBankAccount createBankAccount;
    private final FindBankAccount findBankAccount;
    private final UpdateBankAccount updateBankAccount;

    public BankAccountUseCase(CreateBankAccount createBankAccount,
                               FindBankAccount findBankAccount,
                               UpdateBankAccount updateBankAccount) {
        this.createBankAccount = createBankAccount;
        this.findBankAccount = findBankAccount;
        this.updateBankAccount = updateBankAccount;
    }

    public void createBankAccount(BankAccount bankAccount) throws BusinessException {
        createBankAccount.createBankAccount(bankAccount);
    }

    public BankAccount findByAccountNumber(String accountNumber) throws BusinessException {
        return findBankAccount.findByAccountNumber(accountNumber);
    }

    public void updateBankAccount(BankAccount bankAccount) throws BusinessException {
        updateBankAccount.updateBankAccount(bankAccount);
    }
}
