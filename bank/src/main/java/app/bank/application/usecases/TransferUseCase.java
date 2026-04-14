package app.bank.application.usecases;

import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.BusinessException;
import app.bank.domain.models.Transfer;
import app.bank.domain.models.User;
import app.bank.domain.services.ApproveTransfer;
import app.bank.domain.services.CreateTransfer;
import app.bank.domain.services.FindTransfer;

@Service
public class TransferUseCase {

    private final CreateTransfer createTransfer;
    private final FindTransfer findTransfer;
    private final ApproveTransfer approveTransfer;

    public TransferUseCase(CreateTransfer createTransfer,
                           FindTransfer findTransfer,
                           ApproveTransfer approveTransfer) {
        this.createTransfer = createTransfer;
        this.findTransfer = findTransfer;
        this.approveTransfer = approveTransfer;
    }

    public void createTransfer(Transfer transfer) throws BusinessException {
        createTransfer.createTransfer(transfer);
    }

    public Transfer findById(long id) throws BusinessException {
        return findTransfer.findById(id);
    }

    public void approveTransfer(long transferId, User supervisor) throws BusinessException {
        approveTransfer.approveTransfer(transferId, supervisor);
    }

    public void rejectTransfer(long transferId, User supervisor) throws BusinessException {
        approveTransfer.rejectTransfer(transferId, supervisor);
    }
}
