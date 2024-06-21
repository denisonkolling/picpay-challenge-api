package com.picpay.challenge.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.picpay.challenge.dto.TransferRequest;
import com.picpay.challenge.dto.TransferResponse;
import com.picpay.challenge.model.CommonUser;
import com.picpay.challenge.model.ShopkeeperUser;
import com.picpay.challenge.model.Transfer;
import com.picpay.challenge.repository.CommonUserRepository;
import com.picpay.challenge.repository.ShopkeeperRepository;
import com.picpay.challenge.repository.TransferRepository;
import com.picpay.challenge.service.TransferService;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;
    private final CommonUserRepository userRepository;
    private final ShopkeeperRepository shopkeeperRepository;

    public TransferServiceImpl(TransferRepository transferRepository, CommonUserRepository userRepository,
            ShopkeeperRepository shopkeeperRepository) {
        this.transferRepository = transferRepository;
        this.userRepository = userRepository;
        this.shopkeeperRepository = shopkeeperRepository;
    }

    @Override
    public TransferResponse createTransfer(TransferRequest data) {
        CommonUser payer = userRepository.findById(data.getPayer())
                .orElseThrow(() -> new RuntimeException("Payer not found with ID: " + data.getPayer()));

        ShopkeeperUser payee = shopkeeperRepository.findById(data.getPayee())
                .orElseThrow(() -> new RuntimeException("Payee not found with ID: " + data.getPayee()));

        Transfer transfer = new Transfer();
        BeanUtils.copyProperties(data, transfer);
        transferRepository.save(transfer);

        double newPayerAccountBalance = payer.getAccountBalance() - data.getValue();
        payer.setAccountBalance(newPayerAccountBalance);
        userRepository.save(payer);

        double newPayeeAccountBalance = data.getValue() + payee.getAccountBalance();
        payee.setAccountBalance(newPayeeAccountBalance);
        shopkeeperRepository.save(payee);

        TransferResponse transferResponse = new TransferResponse();
        BeanUtils.copyProperties(transfer, transferResponse);

        return transferResponse;
    }

}
