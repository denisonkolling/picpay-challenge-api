package com.picpay.challenge.service;

import com.picpay.challenge.dto.UserRequest;
import com.picpay.challenge.dto.UserResponse;
import com.picpay.challenge.model.User;

public interface UserService {

    UserResponse createUser(UserRequest user);

    User findUserById(Long userId, String userType);

    void updateUser(User user);


}
