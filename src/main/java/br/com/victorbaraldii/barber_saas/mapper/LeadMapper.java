package br.com.victorbaraldii.barber_saas.mapper;

import br.com.victorbaraldii.barber_saas.dto.request.LeadRequest;
import br.com.victorbaraldii.barber_saas.dto.request.response.LeadResponse;
import br.com.victorbaraldii.barber_saas.entity.Company;
import br.com.victorbaraldii.barber_saas.entity.Lead;

public class LeadMapper {

    private LeadMapper() {

    }

    public static Lead toEntity(LeadRequest request, Company company) {

        Lead lead = new Lead();

        lead.setFullName(request.getFullName());
        lead.setPhone(request.getPhone());
        lead.setStatus(request.getStatus());
        lead.setCompany(company);

        return lead;
    }

    public static LeadResponse toResponse(Lead lead) {

        return new LeadResponse(
                lead.getId(),
                lead.getFullName(),
                lead.getPhone(),
                lead.getStatus(),
                lead.getCompany().getId(),
                lead.getCompany().getName()
        );
    }
}
