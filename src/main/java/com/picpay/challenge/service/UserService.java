package com.picpay.challenge.service;

import com.picpay.challenge.dto.UserRequest;
import com.picpay.challenge.dto.UserResponse;
import com.picpay.challenge.model.User;

public interface UserService {

    UserResponse createUser(UserRequest user);

    User findUserByIdAndType(Long userId, String userType);

    UserResponse findUserById(Long userId);

    void updateUser(UserRequest user, Long id);

    void updateUserAccountBalance(User user);

    void deleteUser(Long userId);

}
