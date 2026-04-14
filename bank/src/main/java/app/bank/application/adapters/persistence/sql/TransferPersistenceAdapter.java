package app.bank.application.adapters.persistence.sql;

import app.bank.domain.models.Transfer;
import app.bank.domain.models.enums.TransferStatus;
import app.bank.domain.ports.TransferPort;
import app.bank.application.adapters.persistence.sql.entities.TransferEntity;
import app.bank.application.adapters.persistence.sql.repositories.TransferRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferPersistenceAdapter implements TransferPort {

    private final TransferRepository repository;

    public TransferPersistenceAdapter(TransferRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsById(long id) {
        return repository.existsById(id);
    }

    @Override
    public void save(Transfer transfer) {
        repository.save(toEntity(transfer));
    }

    @Override
    public Transfer findById(long id) {
        return toModel(repository.findTransferById(id));
    }

    private TransferEntity toEntity(Transfer transfer) {
        TransferEntity e = new TransferEntity();
        e.setOriginAccount(transfer.getOriginAccount() != null ? transfer.getOriginAccount().getAccountNumber() : null);
        e.setDestinationAccount(transfer.getDestinationAccount() != null ? transfer.getDestinationAccount().getAccountNumber() : null);
        e.setAmount(transfer.getAmount());
        e.setCreationDate(transfer.getCreationDate());
        e.setApprovalDate(transfer.getApprovalDate());
        e.setStatus(transfer.getStatus() != null ? transfer.getStatus().toString() : null);
        e.setCreatorUserId(transfer.getCreatorUser() != null ? transfer.getCreatorUser().getId() : null);
        e.setApproverUserId(transfer.getApproverUser() != null ? transfer.getApproverUser().getId() : null);
        return e;
    }

    private Transfer toModel(TransferEntity e) {
        if (e == null) return null;
        Transfer transfer = new Transfer();
        transfer.setId(e.getId());
        transfer.setAmount(e.getAmount());
        transfer.setCreationDate(e.getCreationDate());
        transfer.setApprovalDate(e.getApprovalDate());
        transfer.setStatus(e.getStatus() != null ? TransferStatus.valueOf(e.getStatus()) : null);
        return transfer;
    }
}
