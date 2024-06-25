package com.picpay.challenge.service;

import com.picpay.challenge.dto.UserRequest;
import com.picpay.challenge.dto.UserResponse;

public interface UserService {

    UserResponse createUser(UserRequest user);

}
