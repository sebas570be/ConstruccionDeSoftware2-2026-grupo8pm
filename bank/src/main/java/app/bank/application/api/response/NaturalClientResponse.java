package app.bank.application.api.response;

import java.sql.Date;

public record NaturalClientResponse(
        long id,
        String identificationNumber,
        String fullName,
        String email,
        String phone,
        String address,
        Date birthDate,
        String username,
        String status
) {}
