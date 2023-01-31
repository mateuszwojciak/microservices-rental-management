package com.microservice.tenantservice.kafka;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
