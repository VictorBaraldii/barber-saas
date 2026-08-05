package br.com.victorbaraldii.barber_saas.controller;

import br.com.victorbaraldii.barber_saas.dto.request.LeadRequest;
import br.com.victorbaraldii.barber_saas.dto.request.response.LeadResponse;
import br.com.victorbaraldii.barber_saas.enums.LeadStatus;
import br.com.victorbaraldii.barber_saas.service.LeadService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leads")
public class LeadController {

    private final LeadService service;

    public LeadController(LeadService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<LeadResponse>> findAll() {

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeadResponse> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<LeadResponse>> findByCompany(
            @PathVariable Long companyId) {

        return ResponseEntity.ok(service.findByCompany(companyId));
    }

    @GetMapping("/company/{companyId}/status/{status}")
    public ResponseEntity<List<LeadResponse>> findByCompanyAndStatus(
            @PathVariable Long companyId,
            @PathVariable LeadStatus status) {

        return ResponseEntity.ok(
                service.findByCompanyAndStatus(companyId, status)
        );
    }

    @PostMapping
    public ResponseEntity<LeadResponse> save(
            @Valid @RequestBody LeadRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeadResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody LeadRequest request) {

        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}