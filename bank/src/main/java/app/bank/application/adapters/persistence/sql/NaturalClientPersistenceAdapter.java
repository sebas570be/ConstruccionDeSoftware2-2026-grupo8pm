package app.bank.application.adapters.persistence.sql;

import app.bank.domain.models.NaturalClient;
import app.bank.domain.models.enums.UserStatus;
import app.bank.domain.ports.NaturalClientPort;
import app.bank.application.adapters.persistence.sql.entities.NaturalClientEntity;
import app.bank.application.adapters.persistence.sql.repositories.NaturalClientRepository;
import org.springframework.stereotype.Service;

@Service
public class NaturalClientPersistenceAdapter implements NaturalClientPort {

    private final NaturalClientRepository repository;

    public NaturalClientPersistenceAdapter(NaturalClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsByIdentificationNumber(String identificationNumber) {
        return repository.existsByIdentificationNumber(identificationNumber);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public void save(NaturalClient naturalClient) {
        repository.save(toEntity(naturalClient));
    }

    @Override
    public NaturalClient findByIdentificationNumber(String identificationNumber) {
        return toModel(repository.findByIdentificationNumber(identificationNumber));
    }

    private NaturalClientEntity toEntity(NaturalClient client) {
        NaturalClientEntity e = new NaturalClientEntity();
        e.setFullName(client.getFullName());
        e.setIdentificationNumber(client.getIdentificationNumber());
        e.setPhone(client.getPhone());
        e.setEmail(client.getEmail());
        e.setAddress(client.getAddress());
        e.setBirthDate(client.getBirthDate());
        e.setUsername(client.getUsername());
        e.setPassword(client.getPassword());
        e.setStatus(client.getStatus() != null ? client.getStatus().toString() : null);
        e.setSystemUserId(client.getSystemUser() != null ? client.getSystemUser().getId() : null);
        return e;
    }

    private NaturalClient toModel(NaturalClientEntity e) {
        if (e == null) return null;
        NaturalClient client = new NaturalClient();
        client.setId(e.getId());
        client.setFullName(e.getFullName());
        client.setIdentificationNumber(e.getIdentificationNumber());
        client.setPhone(e.getPhone());
        client.setEmail(e.getEmail());
        client.setAddress(e.getAddress());
        client.setBirthDate(e.getBirthDate());
        client.setUsername(e.getUsername());
        client.setPassword(e.getPassword());
        client.setStatus(e.getStatus() != null ? UserStatus.valueOf(e.getStatus()) : null);
        return client;
    }
}
