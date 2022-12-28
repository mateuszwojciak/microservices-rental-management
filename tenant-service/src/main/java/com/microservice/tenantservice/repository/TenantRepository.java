package com.microservice.tenantservice.repository;

import com.microservice.tenantservice.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
    List<Tenant> findAllByFirstName(String firstName);
}
