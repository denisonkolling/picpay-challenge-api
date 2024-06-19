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
@DiscriminatorValue("lojista")
public class ShopkeeperUser extends User {
    @NotBlank
    @Column(name = "cnpj", unique = true)
    private String cnpj;
    @NotBlank
    private String razaoSocial;
}
