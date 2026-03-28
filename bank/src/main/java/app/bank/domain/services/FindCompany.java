package app.bank.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.BusinessException;
import app.bank.domain.models.Company;
import app.bank.domain.ports.CompanyPort;

@Service
public class FindCompany {

    @Autowired
    private CompanyPort companyPort;

    public FindCompany(CompanyPort companyPort) {
        this.companyPort = companyPort;
    }

    public Company findByNit(String nit) throws BusinessException {
        Company company = companyPort.findByNit(nit);
        if (company == null) {
            throw new BusinessException("No existe una empresa con ese NIT");
        }
        return company;
    }
}
