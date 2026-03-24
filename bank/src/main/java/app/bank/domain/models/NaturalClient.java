package app.bank.domain.models;

import app.bank.domain.models.enums.UserStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class NaturalClient extends Person {
    private String username;
    private String password;
    private UserStatus status;
    private User systemUser;
}
