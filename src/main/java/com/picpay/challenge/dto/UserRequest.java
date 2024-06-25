package com.picpay.challenge.dto;

import com.picpay.challenge.model.UserType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String cpfCnpj;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotNull
    private UserType userType;
    private String companyName;
}