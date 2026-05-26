package app.bank.application.adapters.persistence.sql;

import app.bank.domain.models.Loan;
import app.bank.domain.models.enums.LoanStatus;
import app.bank.domain.models.enums.LoanType;
import app.bank.domain.ports.LoanPort;
import app.bank.application.adapters.persistence.sql.entities.LoanEntity;
import app.bank.application.adapters.persistence.sql.repositories.LoanRepository;
import org.springframework.stereotype.Service;

@Service
public class LoanPersistenceAdapter implements LoanPort {

    private final LoanRepository repository;

    public LoanPersistenceAdapter(LoanRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsById(long id) {
        return repository.existsById(id);
    }

    @Override
    public void save(Loan loan) {
        LoanEntity existing = repository.findLoanById(loan.getId());
        if (existing != null) {
            existing.setLoanType(loan.getLoanType() != null ? loan.getLoanType().toString() : null);
            existing.setClientId(loan.getClientId());
            existing.setRequestedAmount(loan.getRequestedAmount());
            existing.setApprovedAmount(loan.getApprovedAmount());
            existing.setInterestRate(loan.getInterestRate());
            existing.setTermMonths(loan.getTermMonths());
            existing.setStatus(loan.getStatus() != null ? loan.getStatus().toString() : null);
            existing.setApprovalDate(loan.getApprovalDate());
            existing.setDisbursementDate(loan.getDisbursementDate());
            existing.setDisbursementAccountNumber(loan.getDisbursementAccountNumber() != null ? loan.getDisbursementAccountNumber() : loan.getDisbursementAccount() != null ? loan.getDisbursementAccount().getAccountNumber() : null);
            existing.setAnalystApproverId(loan.getAnalystApprover() != null ? loan.getAnalystApprover().getId() : null);
            repository.save(existing);
        } else {
            repository.save(toEntity(loan));
        }
    }

    @Override
    public Loan findById(long id) {
        return toModel(repository.findLoanById(id));
    }

    private LoanEntity toEntity(Loan loan) {
        LoanEntity e = new LoanEntity();
        e.setLoanType(loan.getLoanType() != null ? loan.getLoanType().toString() : null);
        e.setClientId(loan.getClientId());
        e.setRequestedAmount(loan.getRequestedAmount());
        e.setApprovedAmount(loan.getApprovedAmount());
        e.setInterestRate(loan.getInterestRate());
        e.setTermMonths(loan.getTermMonths());
        e.setStatus(loan.getStatus() != null ? loan.getStatus().toString() : null);
        e.setApprovalDate(loan.getApprovalDate());
        e.setDisbursementDate(loan.getDisbursementDate());
        e.setDisbursementAccountNumber(loan.getDisbursementAccount() != null ? loan.getDisbursementAccount().getAccountNumber() : null);
        e.setAnalystApproverId(loan.getAnalystApprover() != null ? loan.getAnalystApprover().getId() : null);
        return e;
    }

    private Loan toModel(LoanEntity e) {
        if (e == null) return null;
        Loan loan = new Loan();
        loan.setId(e.getId());
        loan.setLoanType(e.getLoanType() != null ? LoanType.valueOf(e.getLoanType()) : null);
        loan.setClientId(e.getClientId());
        loan.setRequestedAmount(e.getRequestedAmount());
        loan.setApprovedAmount(e.getApprovedAmount());
        loan.setInterestRate(e.getInterestRate());
        loan.setTermMonths(e.getTermMonths());
        loan.setStatus(e.getStatus() != null ? LoanStatus.valueOf(e.getStatus()) : null);
        loan.setApprovalDate(e.getApprovalDate());
        loan.setDisbursementDate(e.getDisbursementDate());
        loan.setDisbursementAccountNumber(e.getDisbursementAccountNumber());
        return loan;
    }
}
