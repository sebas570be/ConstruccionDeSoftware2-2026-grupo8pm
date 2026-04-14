package app.bank.domain.services;

import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.BusinessException;
import app.bank.domain.models.NaturalClient;
import app.bank.domain.ports.NaturalClientPort;

@Service
public class UpdateNaturalClient {

    private final NaturalClientPort naturalClientPort;

    public UpdateNaturalClient(NaturalClientPort naturalClientPort) {
        this.naturalClientPort = naturalClientPort;
    }

    public void updateNaturalClient(NaturalClient naturalClient) throws BusinessException {
        if (!naturalClientPort.existsByIdentificationNumber(naturalClient.getIdentificationNumber())) {
            throw new BusinessException("No existe un cliente con ese número de identificación");
        }
        naturalClientPort.save(naturalClient);
    }
}