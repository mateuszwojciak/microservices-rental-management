package com.microservice.tenantservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.tenantservice.controller.TenantController;
import com.microservice.tenantservice.model.Tenant;
import com.microservice.tenantservice.service.TenantService;
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

@WebMvcTest(TenantController.class)
public class TenantControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TenantService tenantService;

    private List<Tenant> tenantList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        tenantList.add(Tenant.builder().id(1L).firstName("John").lastName("Wick").email("john.wick@gmails.com").identificationNumber("00-00-01").phone("000-000-001").build());
        tenantList.add(Tenant.builder().id(2L).firstName("Harry").lastName("Potter").email("harry.potter@yahhoo.com").identificationNumber("00-00-02").phone("000-000-002").build());
        tenantList.add(Tenant.builder().id(3L).firstName("James").lastName("Bond").email("james.bond@secretservice.com.uk").identificationNumber("00-00-07").phone("000-000-007").build());
    }

    @Test
    void shouldGetAllTenants() throws Exception {
        when(tenantService.getAllTenants()).thenReturn(tenantList);

        ResultActions response = mockMvc.perform(get("/tenants/all"));

        response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", is(tenantList.size())));
    }

    @Test
    void shouldGetTenantsById() throws Exception {
        when(tenantService.getTenantById(3L)).thenReturn(tenantList.get(2));

        ResultActions response = mockMvc.perform(get("/tenants/info/3"));

        response.andExpect(status().isOk()).andDo(print())
                .andExpect(jsonPath("$.id", is(tenantList.get(2).getId().intValue())))
                .andExpect(jsonPath("$.firstName", is(tenantList.get(2).getFirstName())))
                .andExpect(jsonPath("$.lastName", is(tenantList.get(2).getLastName())));
    }

    @Test
    void shouldGetTenantByName() throws Exception {
        List<Tenant> listWithSearchByName = new ArrayList<>();
        listWithSearchByName.add(tenantList.get(1));
        when(tenantService.getTenantByName("Harry")).thenReturn(listWithSearchByName);

        ResultActions response = mockMvc.perform(get("/tenants/name/Harry"));

        response.andExpect(status().isOk()).andDo(print())
                .andExpect(jsonPath("$[0].id", is(tenantList.get(1).getId().intValue())))
                .andExpect(jsonPath("$[0].firstName", is(tenantList.get(1).getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(tenantList.get(1).getLastName())));
    }

    @Test
    void shouldCreateTenant() throws Exception {
        Tenant tenant = new Tenant(3L, "00-00-07", "James", "Bond", "james.bond@secretservice.com.uk", "000-000-007", null);
        ObjectMapper objectMapper = new ObjectMapper();
        String tenantJson = objectMapper.writeValueAsString(tenant);

        mockMvc.perform(post("/tenants/create")
                .content(tenantJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateTenant() throws Exception {
        Tenant tenant = new Tenant(3L, "00-00-07", "James", "Bond", "james.bond@secretservice.com.uk", "000-000-007", null);
        ObjectMapper objectMapper = new ObjectMapper();
        String tenantJson = objectMapper.writeValueAsString(tenant);

        mockMvc.perform(put("/tenants/edit/3")
                        .content(tenantJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteTenant() throws Exception {
        mockMvc.perform(delete("/tenants/delete/3"))
                .andExpect(status().isNoContent());
    }
}
