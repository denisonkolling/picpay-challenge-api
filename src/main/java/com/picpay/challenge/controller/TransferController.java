package com.picpay.challenge.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpay.challenge.dto.TransferRequest;
import com.picpay.challenge.service.TransferService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/transfer")

public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> createTransfer(@RequestBody TransferRequest transfer) {
        return new ResponseEntity<>(transferService.createTransfer(transfer), HttpStatus.CREATED);

    }

}
