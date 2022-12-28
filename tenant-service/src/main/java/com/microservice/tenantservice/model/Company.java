package com.microservice.tenantservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("company")
public class Company extends Tenant {

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "legal_form")
    private String legalForm;

    @Column(name = "tax_id")
    private String taxId;
}
