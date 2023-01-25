package com.microservice.rentalservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.rentalservice.model.Rental;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaProducer {

    @Value("${topic.name.create}")
    private String kafkaTopicNameCreate;

    @Value("${topic.name.delete}")
    private String kafkaTopicNameDelete;

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducer(ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate) {
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessageCreateRental(Rental rental) throws JsonProcessingException {
        String rentalAsMessage = objectMapper.writeValueAsString(rental);
        kafkaTemplate.send(kafkaTopicNameCreate, rentalAsMessage);

        log.info("Rental object with id {} created. Message sent to kafka queue.", rental.getId());
    }

    public void sendMessageUpdateRental(Rental rental) throws JsonProcessingException {
        String rentalAsMessage = objectMapper.writeValueAsString(rental);
        kafkaTemplate.send(kafkaTopicNameCreate, rentalAsMessage);

        log.info("Rental object with id {} updated. Message sent to kafka queue.", rental.getId());
    }

    public void sendMessageDeleteRental(Rental rental) throws JsonProcessingException {
        String rentalAsMessage = objectMapper.writeValueAsString(rental);
        kafkaTemplate.send(kafkaTopicNameDelete, rentalAsMessage);

        log.info("Rental object with id {} deleted. Message sent to kafka queue.", rental.getId());
    }
}
