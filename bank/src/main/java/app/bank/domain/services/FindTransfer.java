package app.bank.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.BusinessException;
import app.bank.domain.models.Transfer;
import app.bank.domain.ports.TransferPort;

@Service
public class FindTransfer {

    @Autowired
    private TransferPort transferPort;

    public FindTransfer(TransferPort transferPort) {
        this.transferPort = transferPort;
    }

    public Transfer findById(long id) throws BusinessException {
        Transfer transfer = transferPort.findById(id);
        if (transfer == null) {
            throw new BusinessException("No existe una transferencia con ese ID");
        }
        return transfer;
    }
}
