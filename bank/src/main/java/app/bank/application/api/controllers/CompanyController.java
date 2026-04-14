package app.bank.application.api.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.bank.application.api.request.CompanyRequest;
import app.bank.application.api.response.CompanyResponse;
import app.bank.application.usecases.CompanyUseCase;
import app.bank.domain.models.Company;
import app.bank.domain.models.NaturalClient;
import app.bank.domain.models.enums.UserStatus;

@RestController
@RequestMapping("/clients/company")
public class CompanyController {

    private final CompanyUseCase companyUseCase;

    public CompanyController(CompanyUseCase companyUseCase) {
        this.companyUseCase = companyUseCase;
    }

    @PostMapping
    public ResponseEntity<CompanyResponse> create(@Valid @RequestBody CompanyRequest request) {
        Company company = toModel(request);
        companyUseCase.createCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(company));
    }

    @GetMapping("/{nit}")
    public ResponseEntity<CompanyResponse> findByNit(@PathVariable String nit) {
        Company company = companyUseCase.findByNit(nit);
        return ResponseEntity.ok(toResponse(company));
    }

    @PutMapping("/{nit}")
    public ResponseEntity<CompanyResponse> update(@PathVariable String nit,
                                                   @Valid @RequestBody CompanyRequest request) {
        request.setNit(nit);
        Company company = toModel(request);
        companyUseCase.updateCompany(company);
        return ResponseEntity.ok(toResponse(company));
    }

    private static Company toModel(CompanyRequest req) {
        Company company = new Company();
        company.setCompanyName(req.getCompanyName());
        company.setNit(req.getNit());
        company.setEmail(req.getEmail());
        company.setPhone(req.getPhone());
        company.setAddress(req.getAddress());
        company.setUsername(req.getUsername());
        company.setPassword(req.getPassword());
        company.setStatus(UserStatus.ACTIVE);
        NaturalClient legalRep = new NaturalClient();
        legalRep.setIdentificationNumber(req.getLegalRepresentativeId());
        company.setLegalRepresentative(legalRep);
        return company;
    }

    private static CompanyResponse toResponse(Company company) {
        return new CompanyResponse(
                company.getId(),
                company.getCompanyName(),
                company.getNit(),
                company.getEmail(),
                company.getPhone(),
                company.getAddress(),
                company.getUsername(),
                company.getStatus() != null ? company.getStatus().toString() : null,
                company.getLegalRepresentative() != null ? company.getLegalRepresentative().getIdentificationNumber() : null
        );
    }
}
