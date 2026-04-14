package app.bank.domain.services;

import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.NotFoundException;
import app.bank.domain.models.BankAccount;
import app.bank.domain.ports.BankAccountPort;

@Service
public class FindBankAccount {

    private final BankAccountPort bankAccountPort;

    public FindBankAccount(BankAccountPort bankAccountPort) {
        this.bankAccountPort = bankAccountPort;
    }

    public BankAccount findByAccountNumber(String accountNumber) throws NotFoundException {
        BankAccount account = bankAccountPort.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new NotFoundException("No existe una cuenta con ese número");
        }
        return account;
    }
}