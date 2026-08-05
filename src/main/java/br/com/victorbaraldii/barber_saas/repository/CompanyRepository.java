package br.com.victorbaraldii.barber_saas.repository;

import br.com.victorbaraldii.barber_saas.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByEmail(String email);
}
