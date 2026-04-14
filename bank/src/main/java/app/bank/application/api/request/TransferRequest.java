package app.bank.application.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransferRequest {
    @NotBlank(message = "La cuenta origen es obligatoria")
    private String originAccountNumber;
    @NotBlank(message = "La cuenta destino es obligatoria")
    private String destinationAccountNumber;
    @NotNull(message = "El monto es obligatorio")
    private BigDecimal amount;
    @NotBlank(message = "El ID del usuario creador es obligatorio")
    private String creatorUserId;
}
