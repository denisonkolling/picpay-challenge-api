package com.picpay.challenge.service.impl;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse createUser(UserRequest dataRequest) {

        User user = new User();
        BeanUtils.copyProperties(dataRequest, user);

        user.setAccountBalance(0D);
        user.setCreatedAt(Instant.now());

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
    public void updateUser(UserRequest user, Long userId) {

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        if (existingUser != null) {
            copyNonNullProperties(user, existingUser);
            existingUser.setUpdatedAt(Instant.now());
            userRepository.save(existingUser);

            logger.info("User with ID: {} has been updated at {}", existingUser.getId(), existingUser.getUpdatedAt());
        }

    }

    private void copyNonNullProperties(UserRequest source, User target) {
        if (source.getName() != null) {
            target.setName(source.getName());
        }
        if (source.getEmail() != null) {
            target.setEmail(source.getEmail());
        }
        if (source.getPassword() != null) {
            target.setPassword(source.getPassword());
        }
        if (source.getCompanyName() != null) {
            target.setCompanyName(source.getCompanyName());
        }

    }

    @Override
    public void updateUserAccountBalance(User user) {
        user.setUpdatedAt(Instant.now());
        userRepository.save(user);

        logger.info("User with ID: {} account balance has been updated at {}", user.getId(), user.getDeletedAt());
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        user.setDeleted(true);
        user.setDeletedAt(Instant.now());

        userRepository.save(user);

        logger.info("User with ID: {} has been logically deleted at {}", userId, user.getDeletedAt());
    }
}
