package br.com.victorbaraldii.barber_saas.dto.request.response;

import br.com.victorbaraldii.barber_saas.dto.request.RegisterRequest;

public class RegisterResponse {

    private Long id;
    private String fullName;
    private String email;

    public RegisterResponse() {

    }

    public RegisterResponse(Long id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }
}
