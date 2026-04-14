package app.bank.domain.services;

import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.BusinessException;
import app.bank.domain.models.Company;
import app.bank.domain.ports.CompanyPort;

@Service
public class UpdateCompany {

    private final CompanyPort companyPort;

    public UpdateCompany(CompanyPort companyPort) {
        this.companyPort = companyPort;
    }

    public void updateCompany(Company company) throws BusinessException {
        if (!companyPort.existsByNit(company.getNit())) {
            throw new BusinessException("No existe una empresa con ese NIT");
        }
        companyPort.save(company);
    }
}