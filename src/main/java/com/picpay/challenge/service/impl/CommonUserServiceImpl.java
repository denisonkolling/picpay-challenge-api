package com.picpay.challenge.service.impl;

import org.springframework.stereotype.Service;

import com.picpay.challenge.model.Common;
import com.picpay.challenge.repository.CommonUserRepository;
import com.picpay.challenge.service.CommonUserService;

@Service
public class CommonUserServiceImpl implements CommonUserService {

    private final CommonUserRepository userRepository;

    public CommonUserServiceImpl(CommonUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Common createUser(Common user) {
       return userRepository.save(user);
    }

}
