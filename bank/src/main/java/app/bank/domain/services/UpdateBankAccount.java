package app.bank.domain.services;

import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.BusinessException;
import app.bank.domain.models.BankAccount;
import app.bank.domain.ports.BankAccountPort;

@Service
public class UpdateBankAccount {

    private final BankAccountPort bankAccountPort;

    public UpdateBankAccount(BankAccountPort bankAccountPort) {
        this.bankAccountPort = bankAccountPort;
    }

    public void updateBankAccount(BankAccount bankAccount) throws BusinessException {
        if (!bankAccountPort.existsByAccountNumber(bankAccount.getAccountNumber())) {
            throw new BusinessException("No existe una cuenta con ese número");
        }
        bankAccountPort.save(bankAccount);
    }
}