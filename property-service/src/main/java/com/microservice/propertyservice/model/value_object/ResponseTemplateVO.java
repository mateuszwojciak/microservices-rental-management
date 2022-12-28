package com.microservice.propertyservice.model.value_object;

import com.microservice.propertyservice.model.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
    private Property property;
    private Tenant tenant;
}
