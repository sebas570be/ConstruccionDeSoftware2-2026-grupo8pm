package app.bank.application.api.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.bank.application.api.request.TransferRequest;
import app.bank.application.api.response.TransferResponse;
import app.bank.application.usecases.TransferUseCase;
import app.bank.application.usecases.UserUseCase;
import app.bank.domain.models.BankAccount;
import app.bank.domain.models.Transfer;
import app.bank.domain.models.User;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    private final TransferUseCase transferUseCase;
    private final UserUseCase userUseCase;

    public TransferController(TransferUseCase transferUseCase, UserUseCase userUseCase) {
        this.transferUseCase = transferUseCase;
        this.userUseCase = userUseCase;
    }

    @PostMapping
    public ResponseEntity<TransferResponse> create(@Valid @RequestBody TransferRequest request) {
        Transfer transfer = toModel(request);
        transferUseCase.createTransfer(transfer);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(transfer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferResponse> findById(@PathVariable long id) {
        Transfer transfer = transferUseCase.findById(id);
        return ResponseEntity.ok(toResponse(transfer));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<Void> approve(@PathVariable long id,
                                         @RequestParam String supervisorId) {
        User supervisor = userUseCase.findByIdentificationNumber(supervisorId);
        transferUseCase.approveTransfer(id, supervisor);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<Void> reject(@PathVariable long id,
                                        @RequestParam String supervisorId) {
        User supervisor = userUseCase.findByIdentificationNumber(supervisorId);
        transferUseCase.rejectTransfer(id, supervisor);
        return ResponseEntity.ok().build();
    }

    private static Transfer toModel(TransferRequest req) {
        Transfer transfer = new Transfer();
        BankAccount origin = new BankAccount();
        origin.setAccountNumber(req.getOriginAccountNumber());
        transfer.setOriginAccount(origin);
        BankAccount destination = new BankAccount();
        destination.setAccountNumber(req.getDestinationAccountNumber());
        transfer.setDestinationAccount(destination);
        transfer.setAmount(req.getAmount());
        User creator = new User();
        creator.setIdentificationNumber(req.getCreatorUserId());
        transfer.setCreatorUser(creator);
        return transfer;
    }

    private static TransferResponse toResponse(Transfer transfer) {
        return new TransferResponse(
                transfer.getId(),
                transfer.getOriginAccount() != null ? transfer.getOriginAccount().getAccountNumber() : null,
                transfer.getDestinationAccount() != null ? transfer.getDestinationAccount().getAccountNumber() : null,
                transfer.getAmount(),
                transfer.getCreationDate(),
                transfer.getApprovalDate(),
                transfer.getStatus() != null ? transfer.getStatus().toString() : null
        );
    }
}