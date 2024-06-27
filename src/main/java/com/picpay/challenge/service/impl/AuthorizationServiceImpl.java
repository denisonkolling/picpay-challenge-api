package com.picpay.challenge.service.impl;

import org.springframework.stereotype.Service;

import com.picpay.challenge.client.AuthorizationClient;
import com.picpay.challenge.dto.AuthorizationResponse;
import com.picpay.challenge.service.AuthorizationService;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public AuthorizationServiceImpl(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    @Override
    public AuthorizationResponse getAuthorization() {

        AuthorizationResponse authorization = authorizationClient.getAuthorization();

        if (!authorization.getData().isAuthorization()) {
            throw new RuntimeException("Transfer has not been authorized");
        }

        return authorization;
    }

}
