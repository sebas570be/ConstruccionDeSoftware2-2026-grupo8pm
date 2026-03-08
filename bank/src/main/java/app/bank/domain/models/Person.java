package app.bank.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
public abstract class Person {
    private long id;
    private String fullName;
    private String identificationNumber;
    private String email;
    private String phone;
    private String address;
    private Date birthDate;
}
