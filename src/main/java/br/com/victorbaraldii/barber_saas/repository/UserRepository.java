package br.com.victorbaraldii.barber_saas.repository;

import br.com.victorbaraldii.barber_saas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findByCompanyId(Long companyId);

    boolean existsByEmail(String email);
}
