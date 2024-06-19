package com.picpay.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picpay.challenge.model.Common;

@Repository
public interface CommonUserRepository extends JpaRepository<Common, Long> {
    
}
