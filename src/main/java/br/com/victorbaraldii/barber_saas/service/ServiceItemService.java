package br.com.victorbaraldii.barber_saas.service;

import br.com.victorbaraldii.barber_saas.dto.request.ServiceItemRequest;
import br.com.victorbaraldii.barber_saas.dto.request.response.ServiceItemResponse;
import br.com.victorbaraldii.barber_saas.entity.Company;
import br.com.victorbaraldii.barber_saas.entity.ServiceItem;
import br.com.victorbaraldii.barber_saas.mapper.ServiceItemMapper;
import br.com.victorbaraldii.barber_saas.repository.CompanyRepository;
import br.com.victorbaraldii.barber_saas.repository.ServiceItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceItemService {

    private final ServiceItemRepository repository;

    private final CompanyRepository companyRepository;

    public ServiceItemService(ServiceItemRepository repository, CompanyRepository companyRepository) {
        this.repository = repository;
        this.companyRepository = companyRepository;
    }

    public List<ServiceItemResponse> findAll() {

        return repository.findAll()
                .stream()
                .map(ServiceItemMapper::toResponse)
                .toList();
    }

    public ServiceItemResponse findById(Long id) {

        return ServiceItemMapper.toResponse(findEntityById(id));
    }

    public List<ServiceItemResponse> findByCompany(Long companyId) {

        return repository.findByCompanyId(companyId)
                .stream()
                .map(ServiceItemMapper::toResponse)
                .toList();
    }

    public ServiceItemResponse save(ServiceItemRequest request) {

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada."));

        ServiceItem serviceItem = ServiceItemMapper.toEntity(request, company);

        repository.save(serviceItem);

        return ServiceItemMapper.toResponse(serviceItem);
    }

    public ServiceItemResponse update(Long id, ServiceItemRequest request) {

        ServiceItem serviceItem = findEntityById(id);

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada."));

        serviceItem.setName(request.getName());
        serviceItem.setDescription(request.getDescription());
        serviceItem.setPrice(request.getPrice());
        serviceItem.setDuration(request.getDuration());
        serviceItem.setCompany(company);

        repository.save(serviceItem);

        return ServiceItemMapper.toResponse(serviceItem);
    }

    public void delete(Long id) {

        repository.delete(findEntityById(id));
    }

    private ServiceItem findEntityById(Long id) {

        return repository.findById(id).orElseThrow(() -> new RuntimeException("Serviço não encontrado."));
    }
}
