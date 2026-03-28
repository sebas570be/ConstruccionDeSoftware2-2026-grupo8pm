package app.bank.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private LoanPort loanPort;
    @Autowired
    private BankAccountPort bankAccountPort;

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
        BankAccount account = bankAccountPort.findByAccountNumber(
            loan.getDisbursementAccount().getAccountNumber());
        if (account == null) {
            throw new BusinessException("La cuenta destino no existe");
        }
        loan.disburse(account);
        loanPort.save(loan);
        bankAccountPort.save(account);
    }
}
