package com.picpay.challenge.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.picpay.challenge.dto.UserRequest;
import com.picpay.challenge.dto.UserResponse;
import com.picpay.challenge.model.User;
import com.picpay.challenge.repository.UserRepository;
import com.picpay.challenge.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse createUser(UserRequest dataRequest) {

        User user = new User();
        BeanUtils.copyProperties(dataRequest, user);

        user.setAccountBalance(0D);

        userRepository.save(user);

        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);

        return userResponse;
    }

    @Override
    public User findUserById(Long userId, String userType) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(userType + " not found with ID: " + userId));
        return user;
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }
}
