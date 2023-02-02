package com.microservice.tenantservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.tenantservice.controller.CompanyController;
import com.microservice.tenantservice.model.Company;
import com.microservice.tenantservice.service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyController.class)
public class CompanyControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    private List<Company> companyList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        Company company1 = new Company(1L, "00-00-01", null, null, "first@company.com.uk", "000-000-001", null, "Yahhoo", "Limited Liability Company", "000111000");
        Company company2 = new Company(2L, "00-00-02", null, null, "second@company.io", "000-000-002", null, "Noodle", "Municipal", "000222000");
        Company company3 = new Company(3L, "00-00-03", "James", "Bond", "last@company.com", "000-000-003", null, "Doodle", "Partnership company", "000333000");

        companyList.add(company1);
        companyList.add(company2);
        companyList.add(company3);
    }

    @Test
    void shouldGetAllCompanies() throws Exception {
        when(companyService.getAllCompanies()).thenReturn(companyList);

        ResultActions response = mockMvc.perform(get("/companies/all"));

        response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", is(companyList.size())));
    }

    @Test
    void shouldGetCompanyById() throws Exception {
        when(companyService.getCompanyById(3L)).thenReturn(companyList.get(2));

        ResultActions response = mockMvc.perform(get("/companies/info/3"));

        response.andExpect(status().isOk()).andDo(print())
                .andExpect(jsonPath("$.id", is(companyList.get(2).getId().intValue())))
                .andExpect(jsonPath("$.companyName", is(companyList.get(2).getCompanyName())))
                .andExpect(jsonPath("$.legalForm", is(companyList.get(2).getLegalForm())));
    }

    @Test
    void shouldGetCompanyByName() throws Exception {
        List<Company> listWithSearchByName = new ArrayList<>();
        listWithSearchByName.add(companyList.get(1));
        when(companyService.getCompaniesByCompanyName("Noodle")).thenReturn(listWithSearchByName);

        ResultActions response = mockMvc.perform(get("/companies/name/Noodle"));

        response.andExpect(status().isOk()).andDo(print())
                .andExpect(jsonPath("$[0].id", is(companyList.get(1).getId().intValue())))
                .andExpect(jsonPath("$[0].companyName", is(companyList.get(1).getCompanyName())))
                .andExpect(jsonPath("$[0].legalForm", is(companyList.get(1).getLegalForm())));
    }

    @Test
    void shouldCreateCompany() throws Exception {
        Company company = new Company(3L, "00-00-07", "James", "Bond", "james.bond@secretservice.com.uk", "000-000-007", null, "Doodle", "LTD", "000111000");
        ObjectMapper objectMapper = new ObjectMapper();
        String companyJson = objectMapper.writeValueAsString(company);

        mockMvc.perform(post("/companies/create")
                        .content(companyJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateCompany() throws Exception {
        Company company = new Company(3L, "00-00-07", "James", "Bond", "james.bond@secretservice.com.uk", "000-000-007", null, "Noodles", "LTD", "000111000");
        ObjectMapper objectMapper = new ObjectMapper();
        String companyJson = objectMapper.writeValueAsString(company);

        mockMvc.perform(put("/companies/edit/3")
                        .content(companyJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteCompany() throws Exception {
        mockMvc.perform(delete("/companies/delete/3"))
                .andExpect(status().isNoContent());
    }
}
