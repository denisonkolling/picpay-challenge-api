package com.picpay.challenge.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.picpay.challenge.dto.TransferRequest;
import com.picpay.challenge.dto.TransferResponse;
import com.picpay.challenge.model.User;
import com.picpay.challenge.model.Transfer;
import com.picpay.challenge.repository.UserRepository;
import com.picpay.challenge.repository.TransferRepository;
import com.picpay.challenge.service.TransferService;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;
    private final UserRepository userRepository;

    public TransferServiceImpl(TransferRepository transferRepository, UserRepository userRepository) {
        this.transferRepository = transferRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TransferResponse createTransfer(TransferRequest data) {

        User payer = userRepository.findById(data.getPayer())
                .orElseThrow(() -> new RuntimeException("Payer not found with ID: " + data.getPayer()));

        User payee = userRepository.findById(data.getPayee())
                .orElseThrow(() -> new RuntimeException("Payee not found with ID: " + data.getPayee()));

        Transfer transfer = new Transfer();
        BeanUtils.copyProperties(data, transfer);
        transferRepository.save(transfer);

        if (payer.getAccountBalance() > data.getValue()) {
            double newPayerAccountBalance = payer.getAccountBalance() - data.getValue();
            payer.setAccountBalance(newPayerAccountBalance);
            userRepository.save(payer);
        } else {
            throw new RuntimeException("Payer has no found to transfer the amout: $" + data.getValue());
        }

        double newPayeeAccountBalance = data.getValue() + payee.getAccountBalance();
        payee.setAccountBalance(newPayeeAccountBalance);
        userRepository.save(payee);

        TransferResponse transferResponse = new TransferResponse();
        BeanUtils.copyProperties(transfer, transferResponse);

        return transferResponse;
    }

}
