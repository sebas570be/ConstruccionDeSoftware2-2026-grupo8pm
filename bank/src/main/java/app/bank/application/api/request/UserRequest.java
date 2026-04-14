
package app.bank.application.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class UserRequest {
    @NotBlank(message = "El número de identificación es obligatorio")
    private String identificationNumber;
    @NotBlank(message = "El nombre completo es obligatorio")
    private String fullName;
    @Email(message = "El correo debe tener un formato válido")
    @NotBlank(message = "El correo es obligatorio")
    private String email;
    @NotBlank(message = "El teléfono es obligatorio")
    private String phone;
    private String address;
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private Date birthDate;
    @NotBlank(message = "El username es obligatorio")
    private String username;
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
    @NotBlank(message = "El rol es obligatorio")
    private String role;
    private String companyNit;
}
