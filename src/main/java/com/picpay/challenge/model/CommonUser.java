package com.picpay.challenge.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.InheritanceType;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("cliente")
public class CommonUser extends User {
    @NotBlank
    @Column(name = "cpf", unique = true)
    private String cpf;
    @NotBlank
    private String fullName;
}
