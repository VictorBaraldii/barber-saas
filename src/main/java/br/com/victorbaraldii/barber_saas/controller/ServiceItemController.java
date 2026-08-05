package br.com.victorbaraldii.barber_saas.controller;

import br.com.victorbaraldii.barber_saas.dto.request.ServiceItemRequest;
import br.com.victorbaraldii.barber_saas.dto.request.response.ServiceItemResponse;
import br.com.victorbaraldii.barber_saas.entity.ServiceItem;
import br.com.victorbaraldii.barber_saas.repository.ServiceItemRepository;
import br.com.victorbaraldii.barber_saas.service.ServiceItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceItemController {

    private final ServiceItemService service;

    public ServiceItemController(ServiceItemService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ServiceItemResponse>> findAll() {

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceItemResponse> findById(@PathVariable Long id) {

        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<ServiceItemResponse>> findByCompany(@PathVariable Long companyId) {

        return ResponseEntity.ok(service.findByCompany(companyId));
    }

    @PostMapping
    public ResponseEntity<ServiceItemResponse> save(@RequestBody ServiceItemRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceItemResponse> update(@PathVariable Long id, @Valid @RequestBody ServiceItemRequest request) {

        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
