package app.bank.application.adapters.persistence.sql.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bank_products")
public class BankProductEntity {
    @Id
    @Column(name = "product_code", nullable = false)
    private String productCode;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "category")
    private String category;
    @Column(name = "requires_approval")
    private boolean requiresApproval;
}
