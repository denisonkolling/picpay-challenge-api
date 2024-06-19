package com.picpay.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picpay.challenge.model.Shopkeeper;

@Repository
public interface ShopkeeperRepository extends JpaRepository<Shopkeeper, Long> {
    
}
