package br.com.victorbaraldii.barber_saas.repository;

import br.com.victorbaraldii.barber_saas.entity.Lead;
import br.com.victorbaraldii.barber_saas.enums.LeadStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeadRepository extends JpaRepository<Lead, Long> {

    List<Lead> findByCompanyId(Long companyId);

    List<Lead> findByCompanyIdAndStatus(Long companyId, LeadStatus status);
}
