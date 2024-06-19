package com.picpay.challenge.service.impl;

import org.springframework.stereotype.Service;

import com.picpay.challenge.model.Transfer;
import com.picpay.challenge.repository.TransferRepository;
import com.picpay.challenge.service.TransferService;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    public TransferServiceImpl(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    public Transfer createTransfer(Transfer transfer) {
        return transferRepository.save(transfer);
    }

}
