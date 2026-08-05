package br.com.victorbaraldii.barber_saas.mapper;

import br.com.victorbaraldii.barber_saas.dto.request.CompanyRequest;
import br.com.victorbaraldii.barber_saas.dto.request.response.CompanyResponse;
import br.com.victorbaraldii.barber_saas.entity.Company;

public class CompanyMapper {

    private CompanyMapper() {
    }

    public static Company toEntity(CompanyRequest request) {

        Company company = new Company();

        company.setName(request.getName());
        company.setEmail(request.getEmail());
        company.setPhone(request.getPhone());

        return company;
    }

    public static CompanyResponse toResponse(Company company) {

        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getEmail(),
                company.getPhone()
        );
    }

}