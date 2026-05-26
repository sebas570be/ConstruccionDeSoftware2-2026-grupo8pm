package app.bank.domain.services;

import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.BusinessException;
import app.bank.domain.models.BankAccount;
import app.bank.domain.models.Loan;
import app.bank.domain.models.User;
import app.bank.domain.models.enums.Role;
import app.bank.domain.ports.BankAccountPort;
import app.bank.domain.ports.LoanPort;

@Service
public class DisburseLoan {

    private final LoanPort loanPort;
    private final BankAccountPort bankAccountPort;

    public DisburseLoan(LoanPort loanPort, BankAccountPort bankAccountPort) {
        this.loanPort = loanPort;
        this.bankAccountPort = bankAccountPort;
    }

    public void disburseLoan(long loanId, User analyst) throws BusinessException {
        Loan loan = loanPort.findById(loanId);
        if (loan == null) {
            throw new BusinessException("No existe el préstamo");
        }
        if (!analyst.getRole().equals(Role.INTERNAL_ANALYST)) {
            throw new BusinessException("Solo el analista interno puede desembolsar préstamos");
        }
        String accountNumber = loan.getDisbursementAccountNumber();
        if (accountNumber == null) {
            throw new BusinessException("No hay cuenta de desembolso definida");
        }
        BankAccount account = bankAccountPort.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new BusinessException("La cuenta destino no existe");
        }
        loan.disburse(account);
        loanPort.save(loan);
        bankAccountPort.save(account);
    }
}