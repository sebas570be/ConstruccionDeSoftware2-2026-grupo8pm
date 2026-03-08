package app.bank.domain.models;

import app.bank.domain.models.enums.UserStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Company {
    private long id;
    private String companyName;
    private String nit;
    private String email;
    private String phone;
    private String address;
    private String username;
    private String password;
    private NaturalClient legalRepresentative;
    private UserStatus status;
}
