package br.com.victorbaraldii.barber_saas.mapper;

import br.com.victorbaraldii.barber_saas.dto.request.ServiceItemRequest;
import br.com.victorbaraldii.barber_saas.dto.request.response.ServiceItemResponse;
import br.com.victorbaraldii.barber_saas.entity.Company;
import br.com.victorbaraldii.barber_saas.entity.ServiceItem;

public class ServiceItemMapper {

    private ServiceItemMapper() {

    }

    public static ServiceItem toEntity(ServiceItemRequest request, Company company) {

        ServiceItem service = new ServiceItem();

        service.setName(request.getName());
        service.setDescription(request.getDescription());
        service.setPrice(request.getPrice());
        service.setDuration(request.getDuration());
        service.setCompany(company);

        return service;
    }

    public static ServiceItemResponse toResponse(ServiceItem service) {

        return new ServiceItemResponse(
                service.getId(),
                service.getName(),
                service.getDescription(),
                service.getPrice(),
                service.getDuration(),
                service.getCompany().getId(),
                service.getCompany().getName()
        );
    }
}
