package app.bank.domain.models;

import app.bank.domain.models.enums.ProductCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BankProduct {
    private String productCode;
    private String productName;
    private ProductCategory category;
    private boolean requiresApproval;
}

