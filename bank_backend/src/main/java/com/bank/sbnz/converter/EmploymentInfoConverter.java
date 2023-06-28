package com.bank.sbnz.converter;

import com.bank.sbnz.model.EmploymentInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class EmploymentInfoConverter implements AttributeConverter<EmploymentInfo, String> {

    @Override
    public String convertToDatabaseColumn(EmploymentInfo eInfo) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(eInfo);
        } catch (Exception e) {
            throw new RuntimeException("Error converting address to JSON string", e);
        }
    }

    @Override
    public EmploymentInfo convertToEntityAttribute(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, EmploymentInfo.class);
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON string to address", e);
        }
    }
}
