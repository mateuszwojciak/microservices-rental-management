package com.microservice.propertyservice.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@Value
public class Rental {

    @JsonProperty("id")
    Long id;

    @JsonProperty("propertyId")
    Long propertyId;

    @JsonProperty("tenantId")
    Long tenantId;

    @JsonProperty("startDate")
    LocalDate startDate;

    @JsonProperty("endDate")
    LocalDate endDate;

    @JsonProperty("totalAmount")
    BigDecimal totalAmount;

    @JsonProperty("charges")
    Set<RentalCharges> charges;

    @JsonProperty("propertyType")
    String propertyType;

    class RentalCharges {

        @JsonProperty("id")
        private Long id;

        @JsonProperty("rentalId")
        private Long rentalId;

        @JsonProperty("description")
        private String description;

        @JsonProperty("amount")
        private BigDecimal amount;

        public RentalCharges(Long id, Long rentalId, String description, BigDecimal amount) {
            this.id = id;
            this.rentalId = rentalId;
            this.description = description;
            this.amount = amount;
        }
    }
}
