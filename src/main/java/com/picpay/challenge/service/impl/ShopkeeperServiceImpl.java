package com.picpay.challenge.service.impl;

import org.springframework.stereotype.Service;

import com.picpay.challenge.model.Shopkeeper;
import com.picpay.challenge.repository.ShopkeeperRepository;
import com.picpay.challenge.service.ShopkeeperService;

@Service
public class ShopkeeperServiceImpl implements ShopkeeperService {

    private final ShopkeeperRepository shopkeeperRepository;

    public ShopkeeperServiceImpl(ShopkeeperRepository shopkeeperRepository) {
        this.shopkeeperRepository = shopkeeperRepository;
    }

    @Override
    public Shopkeeper createShopkeeper(Shopkeeper shopkeeper) {
        return shopkeeperRepository.save(shopkeeper);
    }

}
