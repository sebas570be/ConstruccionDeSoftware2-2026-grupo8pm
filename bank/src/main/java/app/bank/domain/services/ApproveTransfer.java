package app.bank.domain.services;

import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.BusinessException;
import app.bank.domain.models.BankAccount;
import app.bank.domain.models.Transfer;
import app.bank.domain.models.User;
import app.bank.domain.models.enums.Role;
import app.bank.domain.models.enums.TransferStatus;
import app.bank.domain.ports.BankAccountPort;
import app.bank.domain.ports.TransferPort;

import java.sql.Timestamp;

@Service
public class ApproveTransfer {

    private final TransferPort transferPort;
    private final BankAccountPort bankAccountPort;

    public ApproveTransfer(TransferPort transferPort, BankAccountPort bankAccountPort) {
        this.transferPort = transferPort;
        this.bankAccountPort = bankAccountPort;
    }

    public void approveTransfer(long transferId, User supervisor) throws BusinessException {
        Transfer transfer = transferPort.findById(transferId);
        if (transfer == null) {
            throw new BusinessException("No existe la transferencia");
        }
        if (!supervisor.getRole().equals(Role.COMPANY_SUPERVISOR)) {
            throw new BusinessException("Solo el supervisor de empresa puede aprobar transferencias");
        }
        if (!transfer.getStatus().equals(TransferStatus.PENDING_APPROVAL)) {
            throw new BusinessException("La transferencia no esta en espera de aprobacion");
        }
        BankAccount origin = bankAccountPort.findByAccountNumber(
            transfer.getOriginAccount().getAccountNumber());
        BankAccount destination = bankAccountPort.findByAccountNumber(
            transfer.getDestinationAccount().getAccountNumber());
        if (origin == null) {
            throw new BusinessException("La cuenta origen no existe");
        }
        if (destination == null) {
            throw new BusinessException("La cuenta destino no existe");
        }
        if (origin.getCurrentBalance().compareTo(transfer.getAmount()) < 0) {
            throw new BusinessException("Saldo insuficiente en la cuenta origen");
        }
        origin.setCurrentBalance(origin.getCurrentBalance().subtract(transfer.getAmount()));
        destination.setCurrentBalance(destination.getCurrentBalance().add(transfer.getAmount()));
        transfer.setStatus(TransferStatus.EXECUTED);
        transfer.setApprovalDate(new Timestamp(System.currentTimeMillis()));
        transfer.setApproverUser(supervisor);
        transfer.setOriginAccount(origin);
        transfer.setDestinationAccount(destination);
        transferPort.save(transfer);
        bankAccountPort.save(origin);
        bankAccountPort.save(destination);
    }

    public void rejectTransfer(long transferId, User supervisor) throws BusinessException {
        Transfer transfer = transferPort.findById(transferId);
        if (transfer == null) {
            throw new BusinessException("No existe la transferencia");
        }
        if (!supervisor.getRole().equals(Role.COMPANY_SUPERVISOR)) {
            throw new BusinessException("Solo el supervisor de empresa puede rechazar transferencias");
        }
        if (!transfer.getStatus().equals(TransferStatus.PENDING_APPROVAL)) {
            throw new BusinessException("La transferencia no esta en espera de aprobacion");
        }
        transfer.setStatus(TransferStatus.REJECTED);
        transferPort.save(transfer);
    }
}