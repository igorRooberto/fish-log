package com.igor.fishLog.domain.value_objects;

import com.igor.fishLog.domain.exception.BadRequestException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PasswordTest {

    @Test
    void shouldAcceptValidPassword(){
        Password.validatePassword("Igor12345@");
        Password password = new Password("Igor12345@");
        assertEquals("Igor12345@", password.getValue());
    }

    @Test
    void shouldRejectPasswordWithoutUppercase(){
        BadRequestException exception = assertThrows(BadRequestException.class, () -> Password.validatePassword("igor12345@"));

        assertEquals("A senha deve ter 1 caractere maisculo", exception.getMessage());
    }

    @Test
    void shouldRejectPasswordShorterThanEightCharacters(){
        BadRequestException exception = assertThrows(BadRequestException.class, () -> Password.validatePassword("igor1"));

        assertEquals("A senha deve ter no minimo 8 caracteres", exception.getMessage());
    }

    @Test
    void shouldRejectPasswordWithoutSpecialCharacter(){
        BadRequestException exception = assertThrows(BadRequestException.class, () -> Password.validatePassword("Igor12345"));

        assertEquals("A senha deve ter no mininmo 1 caractere especial", exception.getMessage());
    }

}
