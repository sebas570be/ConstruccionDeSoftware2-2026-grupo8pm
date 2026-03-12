package app.bank.domain.models;

import app.bank.domain.models.enums.AccountStatus;
import app.bank.domain.models.enums.AccountType;
import app.bank.domain.models.enums.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
public class BankAccount {
    private String accountNumber;
    private AccountType accountType;
    private String ownerId;
    private double currentBalance;
    private Currency currency;
    private AccountStatus status;
    private Date openingDate;
}
