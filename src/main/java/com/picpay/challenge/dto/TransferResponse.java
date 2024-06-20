package com.picpay.challenge.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransferResponse {
    @NotNull
    private Double value;
    @NotNull
    private Long payee;
}
