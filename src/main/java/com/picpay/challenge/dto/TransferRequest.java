package com.picpay.challenge.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransferRequest {
    @NotNull
    private Double value;
    @NotNull
    private Long payer;
    @NotNull
    private Long payee;
}
