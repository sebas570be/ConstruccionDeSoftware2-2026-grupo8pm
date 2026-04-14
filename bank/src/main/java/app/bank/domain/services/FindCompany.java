package app.bank.domain.services;

import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.NotFoundException;
import app.bank.domain.models.Company;
import app.bank.domain.ports.CompanyPort;

@Service
public class FindCompany {

    private final CompanyPort companyPort;

    public FindCompany(CompanyPort companyPort) {
        this.companyPort = companyPort;
    }

    public Company findByNit(String nit) throws NotFoundException {
        Company company = companyPort.findByNit(nit);
        if (company == null) {
            throw new NotFoundException("No existe una empresa con ese NIT");
        }
        return company;
    }
}