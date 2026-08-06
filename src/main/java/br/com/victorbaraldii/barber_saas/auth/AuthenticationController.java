package br.com.victorbaraldii.barber_saas.auth;

import br.com.victorbaraldii.barber_saas.dto.request.LoginRequest;
import br.com.victorbaraldii.barber_saas.dto.request.RegisterRequest;
import br.com.victorbaraldii.barber_saas.dto.request.response.LoginResponse;
import br.com.victorbaraldii.barber_saas.dto.request.response.RegisterResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {

        return ResponseEntity.ok(service.login(request));
    }

}
