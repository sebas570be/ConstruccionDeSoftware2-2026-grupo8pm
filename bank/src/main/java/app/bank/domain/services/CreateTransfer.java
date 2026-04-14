package app.bank.domain.services;

import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.BusinessException;
import app.bank.domain.models.Transfer;
import app.bank.domain.models.enums.AccountStatus;
import app.bank.domain.models.enums.TransferStatus;
import app.bank.domain.ports.TransferPort;
import app.bank.domain.ports.BankAccountPort;

import java.sql.Timestamp;

@Service
public class CreateTransfer {

    private final TransferPort transferPort;
    private final BankAccountPort bankAccountPort;

    public CreateTransfer(TransferPort transferPort, BankAccountPort bankAccountPort) {
        this.transferPort = transferPort;
        this.bankAccountPort = bankAccountPort;
    }

    public void createTransfer(Transfer transfer) throws BusinessException {
        if (!bankAccountPort.existsByAccountNumber(transfer.getOriginAccount().getAccountNumber())) {
            throw new BusinessException("La cuenta origen no existe");
        }
        if (!bankAccountPort.existsByAccountNumber(transfer.getDestinationAccount().getAccountNumber())) {
            throw new BusinessException("La cuenta destino no existe");
        }
        if (transfer.getOriginAccount().getStatus() == AccountStatus.BLOCKED ||
            transfer.getOriginAccount().getStatus() == AccountStatus.CANCELLED) {
            throw new BusinessException("La cuenta origen no está activa");
        }
        transfer.validateAmount();
        transfer.setCreationDate(new Timestamp(System.currentTimeMillis()));
        transfer.setStatus(TransferStatus.PENDING_APPROVAL);
        transferPort.save(transfer);
    }
}
