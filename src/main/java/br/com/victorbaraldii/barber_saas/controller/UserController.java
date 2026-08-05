package br.com.victorbaraldii.barber_saas.controller;

import br.com.victorbaraldii.barber_saas.dto.request.UserRequest;
import br.com.victorbaraldii.barber_saas.dto.request.response.UserResponse;
import br.com.victorbaraldii.barber_saas.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {

        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<UserResponse>> findByCompany(
            @PathVariable Long companyId) {

        return ResponseEntity.ok(service.findByCompany(companyId));
    }

    @PostMapping
    public ResponseEntity<UserResponse> save(
            @Valid @RequestBody UserRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UserRequest request) {

        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}