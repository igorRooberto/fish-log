package com.igor.fishLog.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterInputDto(@NotBlank String email, @NotBlank String password) {
}
