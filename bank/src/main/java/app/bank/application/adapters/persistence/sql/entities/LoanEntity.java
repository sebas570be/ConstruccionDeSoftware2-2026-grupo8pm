package app.bank.application.adapters.persistence.sql.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "loans")
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "loan_type")
    private String loanType;
    @Column(name = "client_id", nullable = false)
    private String clientId;
    @Column(name = "requested_amount", nullable = false)
    private BigDecimal requestedAmount;
    @Column(name = "approved_amount")
    private BigDecimal approvedAmount;
    @Column(name = "interest_rate")
    private BigDecimal interestRate;
    @Column(name = "term_months")
    private int termMonths;
    @Column(name = "status")
    private String status;
    @Column(name = "approval_date")
    private Date approvalDate;
    @Column(name = "disbursement_date")
    private Date disbursementDate;
    @Column(name = "disbursement_account_number")
    private String disbursementAccountNumber;
    @Column(name = "analyst_approver_id")
    private Long analystApproverId;
}
