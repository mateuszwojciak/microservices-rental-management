package com.microservice.tenantservice.repository;

import com.microservice.tenantservice.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findAllCompaniesByCompanyName(String companyName);
}
