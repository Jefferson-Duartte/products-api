package com.example.springboot.util.validator;

import com.example.springboot.exceptions.InvalidUUIDException;

import java.util.UUID;

public class UUIDValidator {

    public static void validateUUID(String uuid) {
        String id = uuid.toString();
        if (uuid.length() != 36) {
            throw new InvalidUUIDException("UUID with invalid size. Must contain 36 characters");
        }
        try {
            UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidUUIDException("Invalid UUID format.");
        }
    }
}
