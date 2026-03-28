package app.bank.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.BusinessException;
import app.bank.domain.models.NaturalClient;
import app.bank.domain.ports.NaturalClientPort;

@Service
public class FindNaturalClient {

    @Autowired
    private NaturalClientPort naturalClientPort;

    public FindNaturalClient(NaturalClientPort naturalClientPort) {
        this.naturalClientPort = naturalClientPort;
    }

    public NaturalClient findByIdentificationNumber(String identificationNumber) throws BusinessException {
        NaturalClient client = naturalClientPort.findByIdentificationNumber(identificationNumber);
        if (client == null) {
            throw new BusinessException("No existe un cliente con ese número de identificación");
        }
        return client;
    }
}
