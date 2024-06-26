package com.picpay.challenge.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.picpay.challenge.dto.AuthorizationResponse;

@FeignClient(name = "AuthorizationClient", url = "https://util.devi.tools/api/v2")
public interface AuthorizationClient {
    
    @GetMapping("/authorize")
    AuthorizationResponse getAuthorization();
}