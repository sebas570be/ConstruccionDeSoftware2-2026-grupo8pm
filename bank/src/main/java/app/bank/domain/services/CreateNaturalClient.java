package app.bank.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.BusinessException;
import app.bank.domain.models.NaturalClient;
import app.bank.domain.ports.NaturalClientPort;

@Service
public class CreateNaturalClient {

    @Autowired
    private NaturalClientPort naturalClientPort;

    public CreateNaturalClient(NaturalClientPort naturalClientPort) {
        this.naturalClientPort = naturalClientPort;
    }

    public void createNaturalClient(NaturalClient naturalClient) throws BusinessException {
        if (naturalClientPort.existsByIdentificationNumber(naturalClient.getIdentificationNumber())) {
            throw new BusinessException("Ya existe un cliente con ese número de identificación");
        }
        if (naturalClientPort.existsByUsername(naturalClient.getUsername())) {
            throw new BusinessException("Ya existe un cliente con ese username");
        }
        naturalClientPort.save(naturalClient);
    }
}
