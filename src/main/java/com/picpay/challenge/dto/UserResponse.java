package com.picpay.challenge.dto;

import java.time.Instant;

import com.picpay.challenge.model.UserType;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String cpfCnpj;
    private String email;
    private UserType userType;
    private String companyName;
     private Instant createdAt;
     private Instant updatedAt;
     private boolean isDeleted;

}
