package br.com.victorbaraldii.barber_saas.service;

import br.com.victorbaraldii.barber_saas.dto.request.CompanyRequest;
import br.com.victorbaraldii.barber_saas.dto.request.response.CompanyResponse;
import br.com.victorbaraldii.barber_saas.entity.Company;
import br.com.victorbaraldii.barber_saas.exception.BusinessException;
import br.com.victorbaraldii.barber_saas.exception.ResourceNotFoundException;
import br.com.victorbaraldii.barber_saas.mapper.CompanyMapper;
import br.com.victorbaraldii.barber_saas.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public List<CompanyResponse> findAll() {

        return repository.findAll()
                .stream()
                .map(CompanyMapper::toResponse)
                .toList();
    }

    public CompanyResponse findById(Long id) {

        Company company = findEntityById(id);

        return CompanyMapper.toResponse(company);
    }

    public CompanyResponse save(CompanyRequest request) {

        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new BusinessException("Já existe uma empresa cadastrada com esse e-mail.");
        }

        Company company = CompanyMapper.toEntity(request);

        repository.save(company);

        return CompanyMapper.toResponse(company);
    }

    public CompanyResponse update(Long id, CompanyRequest request) {

        Company company = findEntityById(id);

        company.setName(request.getName());
        company.setEmail(request.getEmail());
        company.setPhone(request.getPhone());

        repository.save(company);

        return CompanyMapper.toResponse(company);
    }

    public void delete(Long id) {

        Company company = findEntityById(id);

        repository.delete(company);
    }

    private Company findEntityById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Empresa não encontrada."));
    }

}