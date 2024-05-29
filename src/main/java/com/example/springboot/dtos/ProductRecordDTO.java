package com.example.springboot.dtos;

import com.example.springboot.util.validation.NameValidate;
import com.example.springboot.util.validation.ValueValidate;

import java.math.BigDecimal;

public record ProductRecordDTO(@NameValidate String name, @ValueValidate BigDecimal value) {
}