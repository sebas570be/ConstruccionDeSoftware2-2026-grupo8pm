package app.bank.domain.models;

import app.bank.domain.models.enums.LoanStatus;
import app.bank.domain.models.enums.LoanType;
import app.bank.domain.exceptions.BusinessException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
public class Loan {
    private long id;
    private LoanType loanType;
    private String clientId;
    private BigDecimal requestedAmount;
    private BigDecimal approvedAmount;
    private BigDecimal interestRate;
    private int termMonths;
    private LoanStatus status;
    private Date approvalDate;
    private Date disbursementDate;
    private BankAccount disbursementAccount;
    private User analystApprover;

    public void validateRequestedAmount() {
        if (this.requestedAmount == null || this.requestedAmount.compareTo(BigDecimal.ZERO) <= 0)
            throw new BusinessException("El monto solicitado debe ser mayor a cero");
    }
}
