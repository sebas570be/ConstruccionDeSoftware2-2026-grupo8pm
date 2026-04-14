package app.bank.application.api.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record TransferResponse(
        long id,
        String originAccount,
        String destinationAccount,
        BigDecimal amount,
        Timestamp creationDate,
        Timestamp approvalDate,
        String status
) {}