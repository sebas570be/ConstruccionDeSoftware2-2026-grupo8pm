package app.bank.application.adapters.persistence.sql;

import app.bank.domain.models.BankAccount;
import app.bank.domain.models.enums.AccountStatus;
import app.bank.domain.models.enums.AccountType;
import app.bank.domain.models.enums.Currency;
import app.bank.domain.ports.BankAccountPort;
import app.bank.application.adapters.persistence.sql.entities.BankAccountEntity;
import app.bank.application.adapters.persistence.sql.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class BankAccountPersistenceAdapter implements BankAccountPort {

    private final BankAccountRepository repository;

    public BankAccountPersistenceAdapter(BankAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsByAccountNumber(String accountNumber) {
        return repository.existsByAccountNumber(accountNumber);
    }

    @Override
    public void save(BankAccount bankAccount) {
        repository.save(toEntity(bankAccount));
    }

    @Override
    public BankAccount findByAccountNumber(String accountNumber) {
        return toModel(repository.findByAccountNumber(accountNumber));
    }

    private BankAccountEntity toEntity(BankAccount account) {
        BankAccountEntity e = new BankAccountEntity();
        e.setAccountNumber(account.getAccountNumber());
        e.setAccountType(account.getAccountType() != null ? account.getAccountType().toString() : null);
        e.setOwnerId(account.getOwnerId());
        e.setCurrentBalance(account.getCurrentBalance());
        e.setCurrency(account.getCurrency() != null ? account.getCurrency().toString() : null);
        e.setStatus(account.getStatus() != null ? account.getStatus().toString() : null);
        e.setOpeningDate(account.getOpeningDate());
        return e;
    }

    private BankAccount toModel(BankAccountEntity e) {
        if (e == null) return null;
        BankAccount account = new BankAccount();
        account.setAccountNumber(e.getAccountNumber());
        account.setAccountType(e.getAccountType() != null ? AccountType.valueOf(e.getAccountType()) : null);
        account.setOwnerId(e.getOwnerId());
        account.setCurrentBalance(e.getCurrentBalance());
        account.setCurrency(e.getCurrency() != null ? Currency.valueOf(e.getCurrency()) : null);
        account.setStatus(e.getStatus() != null ? AccountStatus.valueOf(e.getStatus()) : null);
        account.setOpeningDate(e.getOpeningDate());
        return account;
    }
}
