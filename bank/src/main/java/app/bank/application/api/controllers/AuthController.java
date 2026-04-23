package app.bank.application.api.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import app.bank.application.api.request.LoginRequest;
import app.bank.application.api.response.LoginResponse;
import app.bank.domain.models.User;
import app.bank.domain.ports.UserPort;
import app.bank.infrastructure.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserPort userPort;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          UserPort userPort) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userPort = userPort;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userPort.findByUsername(userDetails.getUsername());
        String token = jwtUtil.generateToken(
                user.getIdentificationNumber(),
                user.getUsername(),
                user.getRole().name()
        );
        return ResponseEntity.ok(new LoginResponse(token, user.getUsername(), user.getRole().name()));
    }
}
