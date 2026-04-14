package app.bank.application.api.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.bank.application.api.request.UserRequest;
import app.bank.application.api.response.UserResponse;
import app.bank.application.usecases.UserUseCase;
import app.bank.domain.models.User;
import app.bank.domain.models.enums.Role;
import app.bank.domain.models.enums.UserStatus;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserUseCase userUseCase;

    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest request) {
        User user = toModel(request);
        userUseCase.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(user));
    }

    @GetMapping("/{identificationNumber}")
    public ResponseEntity<UserResponse> findById(@PathVariable String identificationNumber) {
        User user = userUseCase.findByIdentificationNumber(identificationNumber);
        return ResponseEntity.ok(toResponse(user));
    }

    @PutMapping("/{identificationNumber}")
    public ResponseEntity<UserResponse> update(@PathVariable String identificationNumber,
                                                @Valid @RequestBody UserRequest request) {
        request.setIdentificationNumber(identificationNumber);
        User user = toModel(request);
        userUseCase.updateUser(user);
        return ResponseEntity.ok(toResponse(user));
    }

    private static User toModel(UserRequest req) {
        User user = new User();
        user.setIdentificationNumber(req.getIdentificationNumber());
        user.setFullName(req.getFullName());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        user.setAddress(req.getAddress());
        user.setBirthDate(req.getBirthDate());
        user.setUsername(req.getUsername());
        user.setPassword(req.getPassword());
        user.setRole(req.getRole() != null ? Role.valueOf(req.getRole()) : null);
        user.setStatus(UserStatus.ACTIVE);
        return user;
    }

    private static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getIdentificationNumber(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getBirthDate(),
                user.getUsername(),
                user.getRole() != null ? user.getRole().toString() : null,
                user.getStatus() != null ? user.getStatus().toString() : null
        );
    }
}
