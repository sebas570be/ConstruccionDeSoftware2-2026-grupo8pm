package app.bank.application.adapters.persistence.sql;

import app.bank.domain.models.BankProduct;
import app.bank.domain.models.enums.ProductCategory;
import app.bank.domain.ports.BankProductPort;
import app.bank.application.adapters.persistence.sql.entities.BankProductEntity;
import app.bank.application.adapters.persistence.sql.repositories.BankProductRepository;
import org.springframework.stereotype.Service;

@Service
public class BankProductPersistenceAdapter implements BankProductPort {

    private final BankProductRepository repository;

    public BankProductPersistenceAdapter(BankProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(BankProduct bankProduct) {
        repository.save(toEntity(bankProduct));
    }

    @Override
    public BankProduct findByProductCode(String productCode) {
        return toModel(repository.findByProductCode(productCode));
    }

    private BankProductEntity toEntity(BankProduct product) {
        BankProductEntity e = new BankProductEntity();
        e.setProductCode(product.getProductCode());
        e.setProductName(product.getProductName());
        e.setCategory(product.getCategory() != null ? product.getCategory().toString() : null);
        e.setRequiresApproval(product.isRequiresApproval());
        return e;
    }

    private BankProduct toModel(BankProductEntity e) {
        if (e == null) return null;
        BankProduct product = new BankProduct();
        product.setProductCode(e.getProductCode());
        product.setProductName(e.getProductName());
        product.setCategory(e.getCategory() != null ? ProductCategory.valueOf(e.getCategory()) : null);
        product.setRequiresApproval(e.isRequiresApproval());
        return product;
    }
}

