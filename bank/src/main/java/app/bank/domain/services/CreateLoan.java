package app.bank.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.BusinessException;
import app.bank.domain.models.Loan;
import app.bank.domain.models.enums.LoanStatus;
import app.bank.domain.ports.LoanPort;
import app.bank.domain.ports.NaturalClientPort;
import app.bank.domain.ports.CompanyPort;

@Service
public class CreateLoan {

    @Autowired
    private LoanPort loanPort;
    @Autowired
    private NaturalClientPort naturalClientPort;
    @Autowired
    private CompanyPort companyPort;

    public CreateLoan(LoanPort loanPort, NaturalClientPort naturalClientPort, CompanyPort companyPort) {
        this.loanPort = loanPort;
        this.naturalClientPort = naturalClientPort;
        this.companyPort = companyPort;
    }

    public void createLoan(Loan loan) throws BusinessException {
        if (!naturalClientPort.existsByIdentificationNumber(loan.getClientId()) &&
            !companyPort.existsByNit(loan.getClientId())) {
            throw new BusinessException("El cliente solicitante no existe en el sistema");
        }
        loan.validateRequestedAmount();
        loan.setStatus(LoanStatus.IN_STUDY);
        loanPort.save(loan);
    }
}
