package app.bank.application.adapters.persistence.mongodb;

import app.bank.domain.models.BitacoraEntry;
import app.bank.domain.ports.BitacoraEntryPort;
import app.bank.application.adapters.persistence.mongodb.documents.BitacoraDocument;
import app.bank.application.adapters.persistence.mongodb.repositories.BitacoraRepository;
import org.springframework.stereotype.Service;

@Service
public class BitacoraPersistenceAdapter implements BitacoraEntryPort {

    private final BitacoraRepository repository;

    public BitacoraPersistenceAdapter(BitacoraRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(BitacoraEntry entry) {
        repository.save(toDocument(entry));
    }

    @Override
    public BitacoraEntry findById(String id) {
        return repository.findById(id).map(this::toModel).orElse(null);
    }

    private BitacoraDocument toDocument(BitacoraEntry entry) {
        BitacoraDocument doc = new BitacoraDocument();
        doc.setId(entry.getId());
        doc.setOperationType(entry.getOperationType());
        doc.setOperationDateTime(entry.getOperationDateTime());
        doc.setUserId(entry.getUser() != null ? entry.getUser().getId() : null);
        doc.setUserRole(entry.getUser() != null && entry.getUser().getRole() != null ? entry.getUser().getRole().toString() : null);
        doc.setAffectedProductId(entry.getAffectedProductId());
        doc.setDetailData(entry.getDetailData());
        return doc;
    }

    private BitacoraEntry toModel(BitacoraDocument doc) {
        if (doc == null) return null;
        BitacoraEntry entry = new BitacoraEntry();
        entry.setId(doc.getId());
        entry.setOperationType(doc.getOperationType());
        entry.setOperationDateTime(doc.getOperationDateTime());
        entry.setAffectedProductId(doc.getAffectedProductId());
        entry.setDetailData(doc.getDetailData());
        return entry;
    }
}

