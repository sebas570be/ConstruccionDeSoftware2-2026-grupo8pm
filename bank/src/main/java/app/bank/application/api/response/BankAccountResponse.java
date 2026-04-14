package app.bank.application.api.response;

import java.math.BigDecimal;
import java.sql.Date;

public record BankAccountResponse(
        String accountNumber,
        String accountType,
        String ownerId,
        BigDecimal currentBalance,
        String currency,
        String status,
        Date openingDate
) {}