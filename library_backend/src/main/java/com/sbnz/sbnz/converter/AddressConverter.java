package com.sbnz.sbnz.converter;

import com.sbnz.sbnz.model.Address;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import com.fasterxml.jackson.databind.ObjectMapper;

@Converter
public class AddressConverter implements AttributeConverter<Address, String> {

    @Override
    public String convertToDatabaseColumn(Address address) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(address);
        } catch (Exception e) {
            throw new RuntimeException("Error converting address to JSON string", e);
        }
    }

    @Override
    public Address convertToEntityAttribute(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, Address.class);
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON string to address", e);
        }
    }
}