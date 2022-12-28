package com.microservice.propertyservice.model.value_object;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Tenant {
    private String type;
    private Long id;
    private String identificationNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String companyName;
    private String legalForm;
    private String taxId;
}