package br.com.victorbaraldii.barber_saas.dto.request;

import br.com.victorbaraldii.barber_saas.enums.LeadStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LeadRequest {

    @NotBlank(message = "Nome obrigatório.")
    @Size(max = 120)
    private String fullName;

    @NotBlank(message = "Telefone obrigatório.")
    @Size(max = 20)
    private String phone;

    @NotNull(message = "Status obrigatório")
    private LeadStatus status;

    @NotNull(message = "Empresa obrigatória.")
    private Long companyId;

    public LeadRequest() {

    }

    public LeadRequest(String fullName, String phone, LeadStatus status, Long companyId) {
        this.fullName = fullName;
        this.phone = phone;
        this.status = status;
        this.companyId = companyId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LeadStatus getStatus() {
        return status;
    }

    public void setStatus(LeadStatus status) {
        this.status = status;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
