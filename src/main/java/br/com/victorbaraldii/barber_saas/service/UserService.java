package br.com.victorbaraldii.barber_saas.service;

import br.com.victorbaraldii.barber_saas.dto.request.UserRequest;
import br.com.victorbaraldii.barber_saas.dto.request.response.UserResponse;
import br.com.victorbaraldii.barber_saas.entity.Company;
import br.com.victorbaraldii.barber_saas.entity.User;
import br.com.victorbaraldii.barber_saas.exception.BusinessException;
import br.com.victorbaraldii.barber_saas.exception.ResourceNotFoundException;
import br.com.victorbaraldii.barber_saas.mapper.UserMapper;
import br.com.victorbaraldii.barber_saas.repository.CompanyRepository;
import br.com.victorbaraldii.barber_saas.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository,
                       CompanyRepository companyRepository,
                       PasswordEncoder passwordEncoder) {

        this.repository = repository;
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponse> findAll() {

        return repository.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    public UserResponse findById(Long id) {

        return UserMapper.toResponse(findEntityById(id));
    }

    public List<UserResponse> findByCompany(Long companyId) {

        return repository.findByCompanyId(companyId)
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    public UserResponse save(UserRequest request) {

        if (repository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Já existe um usuário com esse e-mail.");
        }

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Empresa não encontrada."));

        User user = UserMapper.toEntity(request, company);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        repository.save(user);

        return UserMapper.toResponse(user);
    }

    public UserResponse update(Long id, UserRequest request) {

        User user = findEntityById(id);

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Empresa não encontrada."));

        user.setCompany(company);

        if (request.getPassword() != null &&
                !request.getPassword().isBlank()) {

            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        repository.save(user);

        return UserMapper.toResponse(user);
    }

    public void delete(Long id) {

        repository.delete(findEntityById(id));
    }

    private User findEntityById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuário não encontrado."));
    }

}