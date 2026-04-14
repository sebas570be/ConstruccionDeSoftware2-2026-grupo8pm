package app.bank.application.adapters.persistence.sql;

import app.bank.domain.models.Company;
import app.bank.domain.models.enums.UserStatus;
import app.bank.domain.ports.CompanyPort;
import app.bank.application.adapters.persistence.sql.entities.CompanyEntity;
import app.bank.application.adapters.persistence.sql.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyPersistenceAdapter implements CompanyPort {

    private final CompanyRepository repository;

    public CompanyPersistenceAdapter(CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsByNit(String nit) {
        return repository.existsByNit(nit);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public void save(Company company) {
        repository.save(toEntity(company));
    }

    @Override
    public Company findByNit(String nit) {
        return toModel(repository.findByNit(nit));
    }

    private CompanyEntity toEntity(Company company) {
        CompanyEntity e = new CompanyEntity();
        e.setCompanyName(company.getCompanyName());
        e.setNit(company.getNit());
        e.setEmail(company.getEmail());
        e.setPhone(company.getPhone());
        e.setAddress(company.getAddress());
        e.setUsername(company.getUsername());
        e.setPassword(company.getPassword());
        e.setStatus(company.getStatus() != null ? company.getStatus().toString() : null);
        e.setLegalRepresentativeId(company.getLegalRepresentative() != null ? company.getLegalRepresentative().getId() : null);
        return e;
    }

    private Company toModel(CompanyEntity e) {
        if (e == null) return null;
        Company company = new Company();
        company.setId(e.getId());
        company.setCompanyName(e.getCompanyName());
        company.setNit(e.getNit());
        company.setEmail(e.getEmail());
        company.setPhone(e.getPhone());
        company.setAddress(e.getAddress());
        company.setUsername(e.getUsername());
        company.setPassword(e.getPassword());
        company.setStatus(e.getStatus() != null ? UserStatus.valueOf(e.getStatus()) : null);
        return company;
    }
}