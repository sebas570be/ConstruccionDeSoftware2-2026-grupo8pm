package app.bank.application.api.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.bank.application.api.request.BankAccountRequest;
import app.bank.application.api.response.BankAccountResponse;
import app.bank.application.usecases.BankAccountUseCase;
import app.bank.domain.models.BankAccount;
import app.bank.domain.models.enums.AccountStatus;
import app.bank.domain.models.enums.AccountType;
import app.bank.domain.models.enums.Currency;

import java.sql.Date;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {

    private final BankAccountUseCase bankAccountUseCase;

    public BankAccountController(BankAccountUseCase bankAccountUseCase) {
        this.bankAccountUseCase = bankAccountUseCase;
    }

    @PostMapping
    public ResponseEntity<BankAccountResponse> create(@Valid @RequestBody BankAccountRequest request) {
        BankAccount account = toModel(request);
        bankAccountUseCase.createBankAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(account));
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<BankAccountResponse> findById(@PathVariable String accountNumber) {
        BankAccount account = bankAccountUseCase.findByAccountNumber(accountNumber);
        return ResponseEntity.ok(toResponse(account));
    }

    private static BankAccount toModel(BankAccountRequest req) {
        BankAccount account = new BankAccount();
        account.setAccountNumber(req.getAccountNumber());
        account.setAccountType(req.getAccountType() != null ? AccountType.valueOf(req.getAccountType()) : null);
        account.setOwnerId(req.getOwnerId());
        account.setCurrentBalance(req.getCurrentBalance());
        account.setCurrency(req.getCurrency() != null ? Currency.valueOf(req.getCurrency()) : null);
        account.setStatus(AccountStatus.ACTIVE);
        account.setOpeningDate(new Date(System.currentTimeMillis()));
        return account;
    }

    private static BankAccountResponse toResponse(BankAccount account) {
        return new BankAccountResponse(
                account.getAccountNumber(),
                account.getAccountType() != null ? account.getAccountType().toString() : null,
                account.getOwnerId(),
                account.getCurrentBalance(),
                account.getCurrency() != null ? account.getCurrency().toString() : null,
                account.getStatus() != null ? account.getStatus().toString() : null,
                account.getOpeningDate()
        );
    }
}
