package app.bank.domain.ports;

import app.bank.domain.models.NaturalClient;

public interface NaturalClientPort {
    public boolean existsByIdentificationNumber(String identificationNumber);
    public boolean existsByUsername(String username);
    public void save(NaturalClient naturalClient);
    public NaturalClient findByIdentificationNumber(String identificationNumber);
}
