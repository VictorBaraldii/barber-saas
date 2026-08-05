package br.com.victorbaraldii.barber_saas.service;

import br.com.victorbaraldii.barber_saas.dto.request.LeadRequest;
import br.com.victorbaraldii.barber_saas.dto.request.response.LeadResponse;
import br.com.victorbaraldii.barber_saas.entity.Company;
import br.com.victorbaraldii.barber_saas.entity.Lead;
import br.com.victorbaraldii.barber_saas.enums.LeadStatus;
import br.com.victorbaraldii.barber_saas.exception.ResourceNotFoundException;
import br.com.victorbaraldii.barber_saas.mapper.LeadMapper;
import br.com.victorbaraldii.barber_saas.repository.CompanyRepository;
import br.com.victorbaraldii.barber_saas.repository.LeadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadService {

    private final LeadRepository repository;

    private final CompanyRepository companyRepository;

    public LeadService(LeadRepository repository, CompanyRepository companyRepository) {
        this.repository = repository;
        this.companyRepository = companyRepository;
    }

    public List<LeadResponse> findAll() {

        return repository.findAll()
                .stream()
                .map(LeadMapper::toResponse)
                .toList();
    }

    public LeadResponse findById(Long id) {

        return  LeadMapper.toResponse(findEntityById(id));
    }

    public List<LeadResponse> findByCompany(Long companyId) {

        return repository.findByCompanyId(companyId)
                .stream()
                .map(LeadMapper::toResponse)
                .toList();
    }

    public List<LeadResponse> findByCompanyAndStatus(Long companyId, LeadStatus status) {

        return repository.findByCompanyIdAndStatus(companyId, status)
                .stream()
                .map(LeadMapper::toResponse)
                .toList();
    }

    public LeadResponse save(LeadRequest request) {
        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada."));

        Lead lead = LeadMapper.toEntity(request, company);

        repository.save(lead);

        return LeadMapper.toResponse(lead);
    }

    public LeadResponse update(Long id, LeadRequest request) {

        Lead lead = findEntityById(id);

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada."));

        lead.setFullName(request.getFullName());
        lead.setPhone(request.getPhone());
        lead.setStatus(request.getStatus());
        lead.setCompany(company);

        repository.save(lead);

        return LeadMapper.toResponse(lead);
    }

    public void delete(Long id) {

        repository.delete(findEntityById(id));
    }

    private Lead findEntityById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Lead não encontrado."));
    }

}



