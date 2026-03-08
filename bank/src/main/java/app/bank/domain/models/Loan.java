package app.bank.domain.models;

import app.bank.domain.models.enums.LoanStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
public class Loan {
    private long id;
    private String loanType;
    private String clientId;
    private double requestedAmount;
    private double approvedAmount;
    private double interestRate;
    private int termMonths;
    private LoanStatus status;
    private Date approvalDate;
    private Date disbursementDate;
    private BankAccount disbursementAccount;
    private User analystApprover;
}

