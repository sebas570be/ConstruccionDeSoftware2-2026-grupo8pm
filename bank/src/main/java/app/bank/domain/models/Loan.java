package app.bank.domain.models;

import app.bank.domain.models.enums.LoanStatus;
import app.bank.domain.models.enums.LoanType;
import app.bank.domain.models.enums.AccountStatus;
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

    public void approve(User analyst, BigDecimal approvedAmount, BigDecimal interestRate) {
        if (this.status != LoanStatus.IN_STUDY)
            throw new BusinessException("Solo se puede aprobar un préstamo en estudio");
        this.status = LoanStatus.APPROVED;
        this.analystApprover = analyst;
        this.approvedAmount = approvedAmount;
        this.interestRate = interestRate;
        this.approvalDate = new Date(System.currentTimeMillis());
    }

    public void reject(User analyst) {
        if (this.status != LoanStatus.IN_STUDY)
            throw new BusinessException("Solo se puede rechazar un préstamo en estudio");
        this.status = LoanStatus.REJECTED;
        this.analystApprover = analyst;
    }

    public void disburse(BankAccount account) {
        if (this.status != LoanStatus.APPROVED)
            throw new BusinessException("Solo se puede desembolsar un préstamo aprobado");
        if (account == null || account.getStatus() != AccountStatus.ACTIVE)
            throw new BusinessException("La cuenta destino debe estar activa");
        if (this.approvedAmount.compareTo(BigDecimal.ZERO) <= 0)
            throw new BusinessException("El monto aprobado debe ser mayor a cero");
        this.status = LoanStatus.DISBURSED;
        this.disbursementAccount = account;
        this.disbursementDate = new Date(System.currentTimeMillis());
        account.setCurrentBalance(account.getCurrentBalance().add(this.approvedAmount));
    }
}
