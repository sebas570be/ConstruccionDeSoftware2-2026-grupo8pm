package app.bank.application.api.response;

public record CompanyResponse(
        long id,
        String companyName,
        String nit,
        String email,
        String phone,
        String address,
        String username,
        String status,
        String legalRepresentativeId
) {}

