package com.microservice.tenantservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("company")
public class Company extends Tenant {

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "legal_form")
    private String legalForm;

    @Column(name = "tax_id")
    private String taxId;

    public Company(Long id, String identificationNumber, String firstName, String lastName, String email, String phone, String rentAgreementId, String companyName, String legalForm, String taxId) {
        super(id, identificationNumber, firstName, lastName, email, phone, rentAgreementId);
        this.companyName = companyName;
        this.legalForm = legalForm;
        this.taxId = taxId;
    }
}
