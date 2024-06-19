package com.picpay.challenge.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpay.challenge.model.ShopkeeperUser;
import com.picpay.challenge.service.ShopkeeperService;

@RestController
@RequestMapping("/shopkeeper")

public class ShopkeeperController {

    private final ShopkeeperService shopkeeperService;

    public ShopkeeperController(ShopkeeperService shopkeeperService) {
        this.shopkeeperService = shopkeeperService;
    }

    @PostMapping
    public ResponseEntity<?> createShopkeeper(@RequestBody ShopkeeperUser shopkeeper) {
        return new ResponseEntity<>(shopkeeperService.createShopkeeper(shopkeeper), HttpStatus.CREATED);
    }

}
