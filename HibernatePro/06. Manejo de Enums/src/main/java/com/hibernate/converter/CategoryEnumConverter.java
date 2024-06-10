package com.hibernate.converter;

import com.hibernate.model.EmployeeCategory;
import jakarta.persistence.AttributeConverter;

public class CategoryEnumConverter implements AttributeConverter<EmployeeCategory, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EmployeeCategory attribute) {
        return switch (attribute) {
            case EmployeeCategory.JUNIOR -> 1;
            case EmployeeCategory.SENIOR -> 2;
            case EmployeeCategory.MANAGER -> 3;
            case EmployeeCategory.C_LEVEL -> 4;
        };
    }

    @Override
    public EmployeeCategory convertToEntityAttribute(Integer dbData) {
        return switch (dbData) {
            case 1 -> EmployeeCategory.JUNIOR;
            case 2 -> EmployeeCategory.SENIOR;
            case 3 -> EmployeeCategory.MANAGER;
            case 4 -> EmployeeCategory.C_LEVEL;
            default -> throw new IllegalStateException("Unexpected value: " + dbData);
        };
    }
}

