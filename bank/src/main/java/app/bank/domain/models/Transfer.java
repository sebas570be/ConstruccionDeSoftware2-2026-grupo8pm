package app.bank.domain.models;

import app.bank.domain.models.enums.TransferStatus;
import app.bank.domain.exceptions.BusinessException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
public class Transfer {
    private long id;
    private BankAccount originAccount;
    private BankAccount destinationAccount;
    private BigDecimal amount;
    private Timestamp creationDate;
    private Timestamp approvalDate;
    private TransferStatus status;
    private User creatorUser;
    private User approverUser;

    public void validateAmount() {
        if (this.amount == null || this.amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new BusinessException("El monto de la transferencia debe ser mayor a cero");
    }
}
