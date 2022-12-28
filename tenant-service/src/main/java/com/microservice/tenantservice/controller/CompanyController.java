package com.microservice.tenantservice.controller;

import com.microservice.tenantservice.exception.InvalidInputException;
import com.microservice.tenantservice.exception.ResourceNotFoundException;
import com.microservice.tenantservice.model.Company;
import com.microservice.tenantservice.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/all")
    public List<Company> getAllTenants() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/info/{id}")
    public Company getCompanyById(@PathVariable("id") Long companyId) {
        Company company = companyService.getCompanyById(companyId);
        if (company == null)
            throw new ResourceNotFoundException("Company with id " + companyId + " not found");

        return company;
    }

    @GetMapping("name/{name}")
    public List<Company> getCompaniesByName(@PathVariable("name") String name) {
        return companyService.getCompaniesByCompanyName(name);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCompany(@RequestBody Company company) throws InvalidInputException {
        if (company.getIdentificationNumber() == null || company.getCompanyName() == null
                || company.getIdentificationNumber().toCharArray().length <= 7)
            throw new InvalidInputException("Identification number and company name are required fields.");

        companyService.createCompany(company);
    }

    @PutMapping("/edit/{id}")
    public void updateCompany(@PathVariable("id") Long id, @RequestBody Company company) throws InvalidInputException {
        company.setId(id);
        if (company.getIdentificationNumber() == null || company.getCompanyName() == null
                || company.getIdentificationNumber().toCharArray().length <= 7)
            throw new InvalidInputException("Identification number and company name are required fields.");

        companyService.updateCompany(company);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompany(id);
    }
}
