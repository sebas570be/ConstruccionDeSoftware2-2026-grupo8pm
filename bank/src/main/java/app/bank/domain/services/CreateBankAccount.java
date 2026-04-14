package app.bank.domain.services;

import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.BusinessException;
import app.bank.domain.models.BankAccount;
import app.bank.domain.ports.BankAccountPort;
import app.bank.domain.ports.NaturalClientPort;
import app.bank.domain.ports.CompanyPort;

@Service
public class CreateBankAccount {

    private final BankAccountPort bankAccountPort;
    private final NaturalClientPort naturalClientPort;
    private final CompanyPort companyPort;

    public CreateBankAccount(BankAccountPort bankAccountPort, NaturalClientPort naturalClientPort, CompanyPort companyPort) {
        this.bankAccountPort = bankAccountPort;
        this.naturalClientPort = naturalClientPort;
        this.companyPort = companyPort;
    }

    public void createBankAccount(BankAccount bankAccount) throws BusinessException {
        if (bankAccountPort.existsByAccountNumber(bankAccount.getAccountNumber())) {
            throw new BusinessException("Ya existe una cuenta con ese número");
        }
        if (!naturalClientPort.existsByIdentificationNumber(bankAccount.getOwnerId()) &&
            !companyPort.existsByNit(bankAccount.getOwnerId())) {
            throw new BusinessException("El titular de la cuenta no existe en el sistema");
        }
        bankAccountPort.save(bankAccount);
    }
}