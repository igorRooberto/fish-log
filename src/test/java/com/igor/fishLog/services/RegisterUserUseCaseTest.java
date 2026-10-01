package com.igor.fishLog.services;

import com.igor.fishLog.domain.entities.User;
import com.igor.fishLog.domain.entities.UserRole;
import com.igor.fishLog.domain.exception.BadRequestException;
import com.igor.fishLog.domain.exception.ConflictException;
import com.igor.fishLog.dto.RegisterInputDto;
import com.igor.fishLog.repository.UserRepository;
import com.igor.fishLog.repository.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class RegisterUserUseCaseTest {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserRoleRepository userRoleRepository;
    private RegisterUserUsecase registerUserUsecase;

    @BeforeEach
    void setUp(){
        userRepository = Mockito.mock(UserRepository.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        userRoleRepository = Mockito.mock(UserRoleRepository.class);
        registerUserUsecase = new RegisterUserUsecase(userRepository, passwordEncoder, userRoleRepository);
    }

    @Test
    void shouldRegisterUserSuccessfully(){
        RegisterInputDto inputDto = new RegisterInputDto("igorEmail@gmail.com", "Igor12345@");
        UserRole role = new UserRole(1L, "ROLE_USER");

        when(userRepository.existsByEmail(inputDto.email())).thenReturn(false);
        when(userRoleRepository.findByRoleName("ROLE_USER")).thenReturn(Optional.of(role));
        when(passwordEncoder.encode(inputDto.password())).thenReturn("Password_Encrypted");

        registerUserUsecase.execute(inputDto);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldThrowBadRequestExceptionWhenEmailIsInvalid(){
        RegisterInputDto inputDto = new RegisterInputDto("igorEmail", "igor12345@");

        BadRequestException exception = assertThrows(BadRequestException.class, () -> registerUserUsecase.execute(inputDto));

        assertEquals("Email inválido", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void shouldThrowBadRequestExceptionWhenPasswordIsInvalid(){
        RegisterInputDto inputDto = new RegisterInputDto("igorEmail@gmail.com", "igor12345@");

        BadRequestException exception = assertThrows(BadRequestException.class, () -> registerUserUsecase.execute(inputDto));

        assertEquals("A senha deve ter 1 caractere maisculo", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }


    @Test
    void shouldThrowConflictExceptionWhenEmailAlreadyExists(){
        RegisterInputDto inputDto = new RegisterInputDto("igorEmail@gmail.com", "Igor12345@");

        when(userRepository.existsByEmail(inputDto.email())).thenReturn(true);

        ConflictException exception = assertThrows(ConflictException.class, () -> registerUserUsecase.execute(inputDto));

        assertEquals("Este email já está cadastrado no sistema", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void shouldThrowIllegalStateExceptionWhenDefaultRoleNotFound(){
        RegisterInputDto inputDto = new RegisterInputDto("igorEmail@gmail.com", "Igor12345@");

        when(userRepository.existsByEmail(inputDto.email())).thenReturn(false);
        when(userRoleRepository.findByRoleName("ROLE_USER")).thenReturn(Optional.empty());
        when(passwordEncoder.encode(inputDto.password())).thenReturn("Password_Encrypted");

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> registerUserUsecase.execute(inputDto));

        assertEquals("ERRO INTERNO: ROLE NÃO ENCONTRADA", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }
}
