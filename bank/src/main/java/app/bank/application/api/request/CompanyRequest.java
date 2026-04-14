package app.bank.application.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyRequest {
    @NotBlank(message = "La razón social es obligatoria")
    private String companyName;
    @NotBlank(message = "El NIT es obligatorio")
    private String nit;
    @Email(message = "El correo debe tener un formato válido")
    @NotBlank(message = "El correo es obligatorio")
    private String email;
    @NotBlank(message = "El teléfono es obligatorio")
    private String phone;
    private String address;
    @NotBlank(message = "El username es obligatorio")
    private String username;
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
    @NotBlank(message = "El NIT del representante legal es obligatorio")
    private String legalRepresentativeId;
}
