package app.bank.application.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BankAccountRequest {
    @NotBlank(message = "El número de cuenta es obligatorio")
    private String accountNumber;
    @NotBlank(message = "El tipo de cuenta es obligatorio")
    private String accountType;
    @NotBlank(message = "El ID del titular es obligatorio")
    private String ownerId;
    @NotNull(message = "El saldo inicial es obligatorio")
    private BigDecimal currentBalance;
    @NotBlank(message = "La moneda es obligatoria")
    private String currency;
}
