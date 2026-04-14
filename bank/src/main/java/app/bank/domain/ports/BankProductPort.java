package app.bank.domain.ports;

import app.bank.domain.models.BankProduct;

public interface BankProductPort {
    public void save(BankProduct bankProduct);
    public BankProduct findByProductCode(String productCode);
}

