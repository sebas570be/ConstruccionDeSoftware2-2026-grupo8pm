package app.bank.domain.services;

import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.BusinessException;
import app.bank.domain.models.Company;
import app.bank.domain.ports.CompanyPort;
import app.bank.domain.ports.NaturalClientPort;

@Service
public class CreateCompany {

    private final CompanyPort companyPort;
    private final NaturalClientPort naturalClientPort;

    public CreateCompany(CompanyPort companyPort, NaturalClientPort naturalClientPort) {
        this.companyPort = companyPort;
        this.naturalClientPort = naturalClientPort;
    }

    public void createCompany(Company company) throws BusinessException {
        if (companyPort.existsByNit(company.getNit())) {
            throw new BusinessException("Ya existe una empresa con ese NIT");
        }
        if (companyPort.existsByUsername(company.getUsername())) {
            throw new BusinessException("Ya existe una empresa con ese username");
        }
        if (!naturalClientPort.existsByIdentificationNumber(company.getLegalRepresentative().getIdentificationNumber())) {
            throw new BusinessException("El representante legal no existe en el sistema");
        }
        companyPort.save(company);
    }
}