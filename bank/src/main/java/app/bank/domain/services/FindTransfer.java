package app.bank.domain.services;

import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.NotFoundException;
import app.bank.domain.models.Transfer;
import app.bank.domain.ports.TransferPort;

@Service
public class FindTransfer {

    private final TransferPort transferPort;

    public FindTransfer(TransferPort transferPort) {
        this.transferPort = transferPort;
    }

    public Transfer findById(long id) throws NotFoundException {
        Transfer transfer = transferPort.findById(id);
        if (transfer == null) {
            throw new NotFoundException("No existe una transferencia con ese ID");
        }
        return transfer;
    }
}