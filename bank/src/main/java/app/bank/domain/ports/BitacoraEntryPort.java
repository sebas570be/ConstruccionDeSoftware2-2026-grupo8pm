package app.bank.domain.ports;

import app.bank.domain.models.BitacoraEntry;

public interface BitacoraEntryPort {
    public void save(BitacoraEntry bitacoraEntry);
    public BitacoraEntry findById(String id);
}
