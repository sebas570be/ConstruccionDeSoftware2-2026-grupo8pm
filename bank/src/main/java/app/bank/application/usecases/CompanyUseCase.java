package app.bank.application.usecases;

import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.BusinessException;
import app.bank.domain.models.Company;
import app.bank.domain.services.CreateCompany;
import app.bank.domain.services.FindCompany;
import app.bank.domain.services.UpdateCompany;

@Service
public class CompanyUseCase {

    private final CreateCompany createCompany;
    private final FindCompany findCompany;
    private final UpdateCompany updateCompany;

    public CompanyUseCase(CreateCompany createCompany,
                          FindCompany findCompany,
                          UpdateCompany updateCompany) {
        this.createCompany = createCompany;
        this.findCompany = findCompany;
        this.updateCompany = updateCompany;
    }

    public void createCompany(Company company) throws BusinessException {
        createCompany.createCompany(company);
    }

    public Company findByNit(String nit) throws BusinessException {
        return findCompany.findByNit(nit);
    }

    public void updateCompany(Company company) throws BusinessException {
        updateCompany.updateCompany(company);
    }
}
