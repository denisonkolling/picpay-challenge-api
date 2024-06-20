package com.picpay.challenge.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.picpay.challenge.dto.TransferRequest;
import com.picpay.challenge.dto.TransferResponse;
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
    public TransferResponse createTransfer(TransferRequest data) {

        Transfer transfer = new Transfer();

        BeanUtils.copyProperties(data, transfer);

        transferRepository.save(transfer);

        TransferResponse transferResponse = new TransferResponse();

        BeanUtils.copyProperties(transfer, transferResponse);

        return transferResponse;

    }

}
