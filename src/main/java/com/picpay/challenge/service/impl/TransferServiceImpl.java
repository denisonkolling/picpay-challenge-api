package com.picpay.challenge.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.picpay.challenge.dto.TransferRequest;
import com.picpay.challenge.dto.TransferResponse;
import com.picpay.challenge.model.User;
import com.picpay.challenge.model.UserType;
import com.picpay.challenge.model.Transfer;
import com.picpay.challenge.repository.TransferRepository;
import com.picpay.challenge.service.AuthorizationService;
import com.picpay.challenge.service.TransferService;
import com.picpay.challenge.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;
    private final UserService userService;
    private final AuthorizationService authorizationService;

    public TransferServiceImpl(TransferRepository transferRepository, UserService userService,
            AuthorizationService authorizationService) {
        this.transferRepository = transferRepository;
        this.userService = userService;
        this.authorizationService = authorizationService;
    }

    @Override
    @Transactional
    public TransferResponse createTransfer(TransferRequest transferRequest) {

        User payer = userService.findUserById(transferRequest.getPayer(), "Payer");

        if (payer.getUserType() == UserType.LOJISTA) {
            throw new RuntimeException("Shopkeeper can NOT make transfer");
        }

        if (payer.getAccountBalance() < transferRequest.getValue()) {
            throw new RuntimeException("Payer has no found to transfer the amout: $" + transferRequest.getValue());
        }

        User payee = userService.findUserById(transferRequest.getPayee(), "Payee");

        Transfer transfer = new Transfer();
        BeanUtils.copyProperties(transferRequest, transfer);
        transferRepository.save(transfer);

        double newPayerAccountBalance = payer.getAccountBalance() - transferRequest.getValue();
        payer.setAccountBalance(newPayerAccountBalance);
        userService.updateUser(payer);

        double newPayeeAccountBalance = transferRequest.getValue() + payee.getAccountBalance();
        payee.setAccountBalance(newPayeeAccountBalance);
        userService.updateUser(payee);

        TransferResponse transferResponse = new TransferResponse();
        BeanUtils.copyProperties(transfer, transferResponse);

        authorizationService.getAuthorization();

        return transferResponse;
    }

}
