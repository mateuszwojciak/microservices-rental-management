package com.microservice.tenantservice.repository;

import com.microservice.tenantservice.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Long> {

}
