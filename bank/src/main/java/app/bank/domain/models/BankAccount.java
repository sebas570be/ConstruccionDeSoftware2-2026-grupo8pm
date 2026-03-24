package app.bank.domain.models;

import app.bank.domain.models.enums.AccountStatus;
import app.bank.domain.models.enums.AccountType;
import app.bank.domain.models.enums.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
public class BankAccount {
    private String accountNumber;
    private AccountType accountType;
    private String ownerId;
    private BigDecimal currentBalance;
    private Currency currency;
    private AccountStatus status;
    private Date openingDate;
}
