package com.picpay.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpay.challenge.model.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    
}
