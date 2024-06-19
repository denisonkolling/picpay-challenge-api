package com.picpay.challenge.model;

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
@DiscriminatorValue("lojista")
public class Shopkeeper extends User {
    @NotBlank
    private String cnpj;
    @NotBlank
    private String razaoSocial;
}
