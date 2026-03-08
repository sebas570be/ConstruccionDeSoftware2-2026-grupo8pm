package app.bank.domain.models;

import app.bank.domain.models.enums.TransferStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
public class Transfer {
    private long id;
    private BankAccount originAccount;
    private BankAccount destinationAccount;
    private double amount;
    private Timestamp creationDate;
    private Timestamp approvalDate;
    private TransferStatus status;
    private User creatorUser;
    private User approverUser;
}
