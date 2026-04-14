
package app.bank.application.adapters.persistence.sql.entities;

import jakarta.persistence.Entity;
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
@Table(name = "bank_accounts")
public class BankAccountEntity {
    @Id
    @Column(name = "account_number", nullable = false)
    private String accountNumber;
    @Column(name = "account_type", nullable = false)
    private String accountType;
    @Column(name = "owner_id", nullable = false)
    private String ownerId;
    @Column(name = "current_balance", nullable = false)
    private BigDecimal currentBalance;
    @Column(name = "currency", nullable = false)
    private String currency;
    @Column(name = "status")
    private String status;
    @Column(name = "opening_date", nullable = false)
    private Date openingDate;
}
