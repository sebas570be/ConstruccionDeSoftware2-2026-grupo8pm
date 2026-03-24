package app.bank.domain.ports;

import app.bank.domain.models.BankAccount;

public interface BankAccountPort {
    public boolean existsByAccountNumber(String accountNumber);
    public void save(BankAccount bankAccount);
    public BankAccount findByAccountNumber(String accountNumber);
}
