package com.example.springboot.util.validator;

import com.example.springboot.util.validation.ValueValidate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class ValueValidator implements ConstraintValidator<ValueValidate, BigDecimal> {
    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        return value.compareTo(BigDecimal.ZERO) > 0;
    }
}
