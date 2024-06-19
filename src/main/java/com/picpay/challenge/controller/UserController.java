package com.picpay.challenge.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpay.challenge.model.Common;
import com.picpay.challenge.service.CommonUserService;

@RestController
@RequestMapping("/user")

public class UserController {

    private final CommonUserService userService;

    public UserController(CommonUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody Common user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }
}
