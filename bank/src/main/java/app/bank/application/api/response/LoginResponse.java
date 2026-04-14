package app.bank.application.api.response;

public record LoginResponse(
        String token,
        String username,
        String role
) {}
