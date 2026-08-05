package br.com.victorbaraldii.barber_saas.repository;

import br.com.victorbaraldii.barber_saas.entity.ServiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceItemRepository extends JpaRepository<ServiceItem, Long> {

    List<ServiceItem> findByCompanyId(Long companyId);
}
