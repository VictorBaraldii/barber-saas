package br.com.victorbaraldii.barber_saas.auth;

import br.com.victorbaraldii.barber_saas.dto.request.LoginRequest;
import br.com.victorbaraldii.barber_saas.dto.request.RegisterRequest;
import br.com.victorbaraldii.barber_saas.dto.request.response.LoginResponse;
import br.com.victorbaraldii.barber_saas.dto.request.response.RegisterResponse;
import br.com.victorbaraldii.barber_saas.entity.Company;
import br.com.victorbaraldii.barber_saas.entity.User;
import br.com.victorbaraldii.barber_saas.exception.BusinessException;
import br.com.victorbaraldii.barber_saas.exception.ResourceNotFoundException;
import br.com.victorbaraldii.barber_saas.repository.CompanyRepository;
import br.com.victorbaraldii.barber_saas.repository.UserRepository;
import br.com.victorbaraldii.barber_saas.security.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final CompanyRepository companyRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public AuthenticationService(UserRepository userRepository, CompanyRepository companyRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Já existe um usuário com esse e-mail.");
        }

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada."));

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCompany(company);

        userRepository.save(user);

        return new RegisterResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail()
        );
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException("E-mail ou senha inválidos."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BusinessException("E-mail ou senha inválidos.");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new LoginResponse(token);
    }
}
