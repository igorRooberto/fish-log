package com.igor.fishLog.domain.value_objects;

import com.igor.fishLog.domain.exception.BadRequestException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmailTest {

    @Test
    void shouldAcceptValidEmail(){
        Email.validateEmail("igor@gmail.com");
        Email email = new Email("igor@gmail.com");
        assertEquals("igor@gmail.com", email.getValue());
    }

    @Test
    void shouldRejectNullEmail(){
        BadRequestException exception = assertThrows(BadRequestException.class, () -> Email.validateEmail(null));

        assertEquals("Email inválido", exception.getMessage());
    }

    @Test
    void shouldRejectInvalidEmail(){
        BadRequestException exception = assertThrows(BadRequestException.class, () -> Email.validateEmail("Email Inválido"));

        assertEquals("Email inválido", exception.getMessage());
    }
}
