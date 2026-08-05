package br.com.victorbaraldii.barber_saas.dto.request.response;

import br.com.victorbaraldii.barber_saas.enums.LeadStatus;

public class LeadResponse {

    private Long id;
    private String fullName;
    private String phone;
    private LeadStatus status;
    private Long companyId;
    private String companyName;

    public LeadResponse() {

    }

    public LeadResponse(Long id, String fullName, String phone, LeadStatus status, Long companyId, String companyName) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.status = status;
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public LeadStatus getStatus() {
        return status;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }
}
