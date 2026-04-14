package app.bank.domain.services;

import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.BusinessException;
import app.bank.domain.models.Loan;
import app.bank.domain.models.User;
import app.bank.domain.models.enums.Role;
import app.bank.domain.ports.LoanPort;

import java.math.BigDecimal;

@Service
public class ApproveLoan {

    private final LoanPort loanPort;

    public ApproveLoan(LoanPort loanPort) {
        this.loanPort = loanPort;
    }

    public void approveLoan(long loanId, User analyst, BigDecimal approvedAmount, BigDecimal interestRate) throws BusinessException {
        Loan loan = loanPort.findById(loanId);
        if (loan == null) {
            throw new BusinessException("No existe el préstamo");
        }
        if (!analyst.getRole().equals(Role.INTERNAL_ANALYST)) {
            throw new BusinessException("Solo el analista interno puede aprobar préstamos");
        }
        loan.approve(analyst, approvedAmount, interestRate);
        loanPort.save(loan);
    }

    public void rejectLoan(long loanId, User analyst) throws BusinessException {
        Loan loan = loanPort.findById(loanId);
        if (loan == null) {
            throw new BusinessException("No existe el préstamo");
        }
        if (!analyst.getRole().equals(Role.INTERNAL_ANALYST)) {
            throw new BusinessException("Solo el analista interno puede rechazar préstamos");
        }
        loan.reject(analyst);
        loanPort.save(loan);
    }
}