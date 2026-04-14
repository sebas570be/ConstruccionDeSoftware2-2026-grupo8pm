package app.bank.application.api.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.bank.application.api.request.LoanRequest;
import app.bank.application.api.response.LoanResponse;
import app.bank.application.usecases.LoanUseCase;
import app.bank.domain.models.BankAccount;
import app.bank.domain.models.Loan;
import app.bank.domain.models.User;
import app.bank.domain.models.enums.LoanStatus;
import app.bank.domain.models.enums.LoanType;

import java.math.BigDecimal;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanUseCase loanUseCase;

    public LoanController(LoanUseCase loanUseCase) {
        this.loanUseCase = loanUseCase;
    }

    @PostMapping
    public ResponseEntity<LoanResponse> create(@Valid @RequestBody LoanRequest request) {
        Loan loan = toModel(request);
        loanUseCase.createLoan(loan);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(loan));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> findById(@PathVariable long id) {
        Loan loan = loanUseCase.findById(id);
        return ResponseEntity.ok(toResponse(loan));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<Void> approve(@PathVariable long id,
                                         @RequestParam String analystId,
                                         @RequestParam BigDecimal approvedAmount,
                                         @RequestParam BigDecimal interestRate) {
        User analyst = new User();
        analyst.setIdentificationNumber(analystId);
        loanUseCase.approveLoan(id, analyst, approvedAmount, interestRate);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<Void> reject(@PathVariable long id,
                                        @RequestParam String analystId) {
        User analyst = new User();
        analyst.setIdentificationNumber(analystId);
        loanUseCase.rejectLoan(id, analyst);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/disburse")
    public ResponseEntity<Void> disburse(@PathVariable long id,
                                          @RequestParam String analystId) {
        User analyst = new User();
        analyst.setIdentificationNumber(analystId);
        loanUseCase.disburseLoan(id, analyst);
        return ResponseEntity.ok().build();
    }

    private static Loan toModel(LoanRequest req) {
        Loan loan = new Loan();
        loan.setLoanType(req.getLoanType() != null ? LoanType.valueOf(req.getLoanType()) : null);
        loan.setClientId(req.getClientId());
        loan.setRequestedAmount(req.getRequestedAmount());
        loan.setTermMonths(req.getTermMonths());
        loan.setStatus(LoanStatus.IN_STUDY);
        if (req.getDisbursementAccountNumber() != null) {
            BankAccount account = new BankAccount();
            account.setAccountNumber(req.getDisbursementAccountNumber());
            loan.setDisbursementAccount(account);
        }
        return loan;
    }

    private static LoanResponse toResponse(Loan loan) {
        return new LoanResponse(
                loan.getId(),
                loan.getLoanType() != null ? loan.getLoanType().toString() : null,
                loan.getClientId(),
                loan.getRequestedAmount(),
                loan.getApprovedAmount(),
                loan.getInterestRate(),
                loan.getTermMonths(),
                loan.getStatus() != null ? loan.getStatus().toString() : null,
                loan.getApprovalDate(),
                loan.getDisbursementDate()
        );
    }
}
