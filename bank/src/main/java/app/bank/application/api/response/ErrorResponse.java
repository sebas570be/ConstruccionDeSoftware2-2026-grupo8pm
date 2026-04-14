package app.bank.application.api.response;

public record ErrorResponse(
        String message,
        int status
) {}

