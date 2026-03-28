package app.bank.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.BusinessException;
import app.bank.domain.models.Transfer;
import app.bank.domain.models.User;
import app.bank.domain.models.enums.Role;
import app.bank.domain.models.enums.TransferStatus;
import app.bank.domain.ports.TransferPort;

import java.sql.Timestamp;

@Service
public class ApproveTransfer {

    @Autowired
    private TransferPort transferPort;

    public ApproveTransfer(TransferPort transferPort) {
        this.transferPort = transferPort;
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
            throw new BusinessException("La transferencia no está en espera de aprobación");
        }
        if (transfer.getOriginAccount().getCurrentBalance().compareTo(transfer.getAmount()) < 0) {
            throw new BusinessException("Saldo insuficiente en la cuenta origen");
        }
        transfer.getOriginAccount().setCurrentBalance(
            transfer.getOriginAccount().getCurrentBalance().subtract(transfer.getAmount()));
        transfer.getDestinationAccount().setCurrentBalance(
            transfer.getDestinationAccount().getCurrentBalance().add(transfer.getAmount()));
        transfer.setStatus(TransferStatus.EXECUTED);
        transfer.setApprovalDate(new Timestamp(System.currentTimeMillis()));
        transfer.setApproverUser(supervisor);
        transferPort.save(transfer);
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
            throw new BusinessException("La transferencia no está en espera de aprobación");
        }
        transfer.setStatus(TransferStatus.REJECTED);
        transferPort.save(transfer);
    }
}
