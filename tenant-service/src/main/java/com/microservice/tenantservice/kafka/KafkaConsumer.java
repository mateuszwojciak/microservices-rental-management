package com.microservice.tenantservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.tenantservice.model.Tenant;
import com.microservice.tenantservice.service.TenantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    private static final String createTopic = "${topic.name.create}";
    private static final String deleteTopic = "${topic.name.delete}";

    private final ObjectMapper objectMapper;
    private final TenantService tenantService;

    @Autowired
    public KafkaConsumer(ObjectMapper objectMapper, TenantService tenantService) {
        this.objectMapper = objectMapper;
        this.tenantService = tenantService;
    }

    @KafkaListener(topics = createTopic)
    public void consumeCreateMessage(String message) throws JsonProcessingException {
        log.info("Message for new rental consumed {}", message);
        Rental rental = objectMapper.readValue(message, Rental.class);

        Tenant tenant = tenantService.getTenantById(rental.getTenantId());
        tenant.setRentAgreementId(rental.getId());
        tenantService.updateTenant(tenant);
    }

    @KafkaListener(topics = deleteTopic)
    public void consumeDeleteRentalIdMessage(String message) throws JsonProcessingException {
        log.info("Message for delete rental id consumed {}", message);
        Rental rental = objectMapper.readValue(message, Rental.class);

        Tenant tenant = tenantService.getTenantById(rental.getTenantId());
        tenant.setRentAgreementId(null);
        tenantService.updateTenant(tenant);
    }
}
