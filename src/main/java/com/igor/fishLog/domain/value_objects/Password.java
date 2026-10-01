package com.igor.fishLog.domain.value_objects;

import com.igor.fishLog.domain.exception.BadRequestException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Password {

    @Column(name = "password", nullable = false)
    private final String value;

    public Password(String value) {
        this.value = value;
    }

    public static void validatePassword(String value) {
        if (value.length() < 8) {
            throw new BadRequestException("A senha deve ter no minimo 8 caracteres");
        }

        if (!value.matches(".*[A-Z].*")) {
            throw new BadRequestException("A senha deve ter 1 caractere maisculo");
        }

        if (!value.matches(".*[^a-zA-Z0-9].*")) {
            throw new BadRequestException("A senha deve ter no mininmo 1 caractere especial");
        }
    }

    public String getValue () {
            return value;
    }
}


