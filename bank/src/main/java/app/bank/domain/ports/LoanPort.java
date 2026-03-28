package app.bank.domain.ports;

import app.bank.domain.models.Loan;

public interface LoanPort {
    public boolean existsById(long id);
    public void save(Loan loan);
    public Loan findById(long id);
}

