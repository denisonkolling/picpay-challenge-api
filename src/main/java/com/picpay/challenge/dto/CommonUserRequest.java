package com.picpay.challenge.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommonUserRequest {
    @NotBlank
    private String fullName;
    @NotBlank
    private String cpf;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotNull
    private Double accountBalance;
}