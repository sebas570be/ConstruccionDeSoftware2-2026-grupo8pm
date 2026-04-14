package app.bank.application.usecases;

import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.BusinessException;
import app.bank.domain.models.NaturalClient;
import app.bank.domain.services.CreateNaturalClient;
import app.bank.domain.services.FindNaturalClient;
import app.bank.domain.services.UpdateNaturalClient;

@Service
public class NaturalClientUseCase {

    private final CreateNaturalClient createNaturalClient;
    private final FindNaturalClient findNaturalClient;
    private final UpdateNaturalClient updateNaturalClient;

    public NaturalClientUseCase(CreateNaturalClient createNaturalClient,
                                 FindNaturalClient findNaturalClient,
                                 UpdateNaturalClient updateNaturalClient) {
        this.createNaturalClient = createNaturalClient;
        this.findNaturalClient = findNaturalClient;
        this.updateNaturalClient = updateNaturalClient;
    }

    public void createNaturalClient(NaturalClient naturalClient) throws BusinessException {
        createNaturalClient.createNaturalClient(naturalClient);
    }

    public NaturalClient findByIdentificationNumber(String identificationNumber) throws BusinessException {
        return findNaturalClient.findByIdentificationNumber(identificationNumber);
    }

    public void updateNaturalClient(NaturalClient naturalClient) throws BusinessException {
        updateNaturalClient.updateNaturalClient(naturalClient);
    }
}
