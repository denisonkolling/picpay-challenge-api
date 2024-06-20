package com.picpay.challenge.service;

import com.picpay.challenge.dto.TransferRequest;
import com.picpay.challenge.dto.TransferResponse;

public interface TransferService {
    TransferResponse createTransfer(TransferRequest transfer);
}
