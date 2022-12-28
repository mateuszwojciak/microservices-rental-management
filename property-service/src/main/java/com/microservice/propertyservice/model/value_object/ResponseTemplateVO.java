package com.microservice.propertyservice.model.value_object;

import com.microservice.propertyservice.model.Property;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseTemplateVO {
    private Property property;
    private Tenant tenant;
}
