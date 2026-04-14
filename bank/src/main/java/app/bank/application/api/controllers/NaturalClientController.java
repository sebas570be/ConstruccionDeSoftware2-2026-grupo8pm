package app.bank.application.api.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.bank.application.api.request.NaturalClientRequest;
import app.bank.application.api.response.NaturalClientResponse;
import app.bank.application.usecases.NaturalClientUseCase;
import app.bank.domain.models.NaturalClient;
import app.bank.domain.models.enums.UserStatus;


@RestController
@RequestMapping("/clients/natural")
public class NaturalClientController {

    private final NaturalClientUseCase naturalClientUseCase;

    public NaturalClientController(NaturalClientUseCase naturalClientUseCase) {
        this.naturalClientUseCase = naturalClientUseCase;
    }

    @PostMapping
    public ResponseEntity<NaturalClientResponse> create(@Valid @RequestBody NaturalClientRequest request) {
        NaturalClient client = toModel(request);
        naturalClientUseCase.createNaturalClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(client));
    }

    @GetMapping("/{identificationNumber}")
    public ResponseEntity<NaturalClientResponse> findById(@PathVariable String identificationNumber) {
        NaturalClient client = naturalClientUseCase.findByIdentificationNumber(identificationNumber);
        return ResponseEntity.ok(toResponse(client));
    }

    @PutMapping("/{identificationNumber}")
    public ResponseEntity<NaturalClientResponse> update(@PathVariable String identificationNumber,
                                                         @Valid @RequestBody NaturalClientRequest request) {
        request.setIdentificationNumber(identificationNumber);
        NaturalClient client = toModel(request);
        naturalClientUseCase.updateNaturalClient(client);
        return ResponseEntity.ok(toResponse(client));
    }

    private static NaturalClient toModel(NaturalClientRequest req) {
        NaturalClient client = new NaturalClient();
        client.setIdentificationNumber(req.getIdentificationNumber());
        client.setFullName(req.getFullName());
        client.setEmail(req.getEmail());
        client.setPhone(req.getPhone());
        client.setAddress(req.getAddress());
        client.setBirthDate(req.getBirthDate());
        client.setUsername(req.getUsername());
        client.setPassword(req.getPassword());
        client.setStatus(UserStatus.ACTIVE);
        return client;
    }

    private static NaturalClientResponse toResponse(NaturalClient client) {
        return new NaturalClientResponse(
                client.getId(),
                client.getIdentificationNumber(),
                client.getFullName(),
                client.getEmail(),
                client.getPhone(),
                client.getAddress(),
                client.getBirthDate(),
                client.getUsername(),
                client.getStatus() != null ? client.getStatus().toString() : null
        );
    }
}
