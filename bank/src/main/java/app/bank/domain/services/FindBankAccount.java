package app.bank.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.BusinessException;
import app.bank.domain.models.BankAccount;
import app.bank.domain.ports.BankAccountPort;

@Service
public class FindBankAccount {

    @Autowired
    private BankAccountPort bankAccountPort;

    public FindBankAccount(BankAccountPort bankAccountPort) {
        this.bankAccountPort = bankAccountPort;
    }

    public BankAccount findByAccountNumber(String accountNumber) throws BusinessException {
        BankAccount account = bankAccountPort.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new BusinessException("No existe una cuenta con ese número");
        }
        return account;
    }
}

