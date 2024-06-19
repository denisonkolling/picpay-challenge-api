package com.picpay.challenge.service;

import com.picpay.challenge.dto.CommonUserRequest;
import com.picpay.challenge.dto.CommonUserResponse;

public interface CommonUserService {

    CommonUserResponse createUser(CommonUserRequest user);

}
