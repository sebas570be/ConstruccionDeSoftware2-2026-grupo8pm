package app.bank.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.bank.domain.exceptions.NotFoundException;
import app.bank.domain.models.Loan;
import app.bank.domain.ports.LoanPort;

@Service
public class FindLoan {

    @Autowired
    private LoanPort loanPort;

    public FindLoan(LoanPort loanPort) {
        this.loanPort = loanPort;
    }

    public Loan findById(long id) throws NotFoundException {
        Loan loan = loanPort.findById(id);
        if (loan == null) {
            throw new NotFoundException("No existe un préstamo con ese ID");
        }
        return loan;
    }
}