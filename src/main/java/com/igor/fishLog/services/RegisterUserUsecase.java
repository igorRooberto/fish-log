package com.igor.fishLog.services;

import com.igor.fishLog.domain.entities.User;
import com.igor.fishLog.domain.entities.UserRole;
import com.igor.fishLog.domain.exception.ConflictException;
import com.igor.fishLog.domain.value_objects.Email;
import com.igor.fishLog.domain.value_objects.Password;
import com.igor.fishLog.dto.RegisterInputDto;
import com.igor.fishLog.repository.UserRepository;
import com.igor.fishLog.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegisterUserUsecase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    public void execute(RegisterInputDto registerInputDto){

        Email.validateEmail(registerInputDto.email());
        Password.validatePassword(registerInputDto.password());

        if(userRepository.existsByEmail(registerInputDto.email())){
            throw new ConflictException("Este email já está cadastrado no sistema");
        }

        String encryptedPassword = passwordEncoder.encode(registerInputDto.password());

        UserRole role = userRoleRepository.findByRoleName("ROLE_USER")
                .orElseThrow(() -> new IllegalStateException("ERRO INTERNO: ROLE NÃO ENCONTRADA"));

        User user = User.builder()
                .email(new Email(registerInputDto.email()))
                .password(new Password(encryptedPassword))
                .active(true)
                .roles(Set.of(role))
                .build();

        userRepository.save(user);
    }
}
