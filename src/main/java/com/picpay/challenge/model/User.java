package com.picpay.challenge.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 255)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false, length = 20)
    private UserType userType;

    @Column(name = "cpf_cnpj", nullable = false, length = 20, unique = true)
    private String cpfCnpj;

    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "senha", nullable = false, length = 255)
    private String password;

    @Column(name = "razao_social")
    private String companyName;

    @Column(name = "saldo_conta")
    private Double accountBalance;
}
