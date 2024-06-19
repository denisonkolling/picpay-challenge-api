package com.picpay.challenge.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.picpay.challenge.dto.CommonUserRequest;
import com.picpay.challenge.dto.CommonUserResponse;
import com.picpay.challenge.model.CommonUser;
import com.picpay.challenge.repository.CommonUserRepository;
import com.picpay.challenge.service.CommonUserService;

@Service
public class CommonUserServiceImpl implements CommonUserService {

    private final CommonUserRepository userRepository;

    public CommonUserServiceImpl(CommonUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CommonUserResponse createUser(CommonUserRequest dataRequest) {

        CommonUser user = new CommonUser();
        BeanUtils.copyProperties(dataRequest, user);

        userRepository.save(user);

        CommonUserResponse userResponse = new CommonUserResponse();
        BeanUtils.copyProperties(user, userResponse);

        return userResponse;
    }
}
