package app.bank.domain.ports;

import app.bank.domain.models.Transfer;

public interface TransferPort {
    public boolean existsById(long id);
    public void save(Transfer transfer);
    public Transfer findById(long id);
}
