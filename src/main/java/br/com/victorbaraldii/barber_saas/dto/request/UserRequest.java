package br.com.victorbaraldii.barber_saas.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRequest {

    @NotBlank(message = "Nome obrigatório.")
    @Size(max = 120)
    private String fullName;

    @NotBlank(message = "E-mail obrigatório.")
    @Email(message = "E-mail inválido.")
    private String email;

    @NotBlank(message = "Senha obrigatória.")
    @Size(min = 6, message = "A senha deve possuir pelo menos 6 caracteres.")
    private String password;

    @NotNull(message = "Empresa obrigatória.")
    private Long companyId;

    public UserRequest() {
    }

    public UserRequest(String fullName,
                       String email,
                       String password,
                       Long companyId) {

        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.companyId = companyId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
