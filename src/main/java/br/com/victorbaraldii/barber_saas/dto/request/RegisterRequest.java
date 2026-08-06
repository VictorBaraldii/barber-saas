package br.com.victorbaraldii.barber_saas.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank(message = "Nome obrigatório.")
    private String fullName;

    @Email
    @NotBlank(message = "E-mail obrigatório.")
    private String email;

    @Size(min = 6)
    @NotBlank(message = "Senha obrigatória.")
    private String password;

    @NotNull(message = "Empresa obrigatória.")
    private Long companyId;

    public RegisterRequest() {

    }

    public RegisterRequest(String fullName, String email, String password, Long companyId) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.companyId = companyId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
