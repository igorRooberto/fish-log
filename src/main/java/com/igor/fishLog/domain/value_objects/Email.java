package com.igor.fishLog.domain.value_objects;

import com.igor.fishLog.domain.exception.BadRequestException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Email {

    @Column(name = "email", nullable = false, unique = true)
    private final String value;

    public Email(String value) {
        this.value = value;
    }

    public static void validateEmail(String value){
        if(value == null || !value.contains("@gmail.com")){
            throw new BadRequestException("Email inválido");
        }
    }

    public String getValue() {
        return value;
    }
}
