package com.picpay.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picpay.challenge.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
