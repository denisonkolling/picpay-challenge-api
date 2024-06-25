package com.picpay.challenge.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.picpay.challenge.dto.TransferRequest;
import com.picpay.challenge.dto.TransferResponse;
import com.picpay.challenge.model.User;
import com.picpay.challenge.model.UserType;
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
    public TransferResponse createTransfer(TransferRequest transferRequest) {

        User payer = checkUser(transferRequest.getPayer(), "Payer");

        if (payer.getUserType() == UserType.LOJISTA) {
            throw new RuntimeException("Shopkeeper can NOT make transfer");
        }

        if (payer.getAccountBalance() < transferRequest.getValue()) {
            throw new RuntimeException("Payer has no found to transfer the amout: $" + transferRequest.getValue());
        }

        User payee = checkUser(transferRequest.getPayee(), "Payee");

        Transfer transfer = new Transfer();
        BeanUtils.copyProperties(transferRequest, transfer);
        transferRepository.save(transfer);

        double newPayerAccountBalance = payer.getAccountBalance() - transferRequest.getValue();
        payer.setAccountBalance(newPayerAccountBalance);
        userRepository.save(payer);

        double newPayeeAccountBalance = transferRequest.getValue() + payee.getAccountBalance();
        payee.setAccountBalance(newPayeeAccountBalance);
        userRepository.save(payee);

        TransferResponse transferResponse = new TransferResponse();
        BeanUtils.copyProperties(transfer, transferResponse);

        return transferResponse;
    }

    User checkUser(Long userId, String userType) {

        User userDB = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(userType + " not found with ID: " + userId));

        return userDB;
    }

}
