package app.bank.domain.ports;

import app.bank.domain.models.Company;

public interface CompanyPort {
    public boolean existsByNit(String nit);
    public boolean existsByUsername(String username);
    public void save(Company company);
    public Company findByNit(String nit);
}
