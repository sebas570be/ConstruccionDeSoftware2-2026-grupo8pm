package app.bank.application.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LoanRequest {
    @NotBlank(message = "El tipo de préstamo es obligatorio")
    private String loanType;
    @NotBlank(message = "El ID del cliente es obligatorio")
    private String clientId;
    @NotNull(message = "El monto solicitado es obligatorio")
    private BigDecimal requestedAmount;
    private int termMonths;
    private String disbursementAccountNumber;
}
