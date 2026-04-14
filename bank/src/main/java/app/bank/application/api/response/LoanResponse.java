package app.bank.application.api.response;

import java.math.BigDecimal;
import java.sql.Date;

public record LoanResponse(
        long id,
        String loanType,
        String clientId,
        BigDecimal requestedAmount,
        BigDecimal approvedAmount,
        BigDecimal interestRate,
        int termMonths,
        String status,
        Date approvalDate,
        Date disbursementDate
) {}