package app.bank.application.usecases;

import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.BusinessException;
import app.bank.domain.models.Loan;
import app.bank.domain.models.User;
import app.bank.domain.services.ApproveLoan;
import app.bank.domain.services.CreateLoan;
import app.bank.domain.services.DisburseLoan;
import app.bank.domain.services.FindLoan;

import java.math.BigDecimal;

@Service
public class LoanUseCase {

    private final CreateLoan createLoan;
    private final FindLoan findLoan;
    private final ApproveLoan approveLoan;
    private final DisburseLoan disburseLoan;

    public LoanUseCase(CreateLoan createLoan,
                       FindLoan findLoan,
                       ApproveLoan approveLoan,
                       DisburseLoan disburseLoan) {
        this.createLoan = createLoan;
        this.findLoan = findLoan;
        this.approveLoan = approveLoan;
        this.disburseLoan = disburseLoan;
    }

    public void createLoan(Loan loan) throws BusinessException {
        createLoan.createLoan(loan);
    }

    public Loan findById(long id) throws BusinessException {
        return findLoan.findById(id);
    }

    public void approveLoan(long loanId, User analyst, BigDecimal approvedAmount, BigDecimal interestRate) throws BusinessException {
        approveLoan.approveLoan(loanId, analyst, approvedAmount, interestRate);
    }

    public void rejectLoan(long loanId, User analyst) throws BusinessException {
        approveLoan.rejectLoan(loanId, analyst);
    }

    public void disburseLoan(long loanId, User analyst) throws BusinessException {
        disburseLoan.disburseLoan(loanId, analyst);
    }
}
