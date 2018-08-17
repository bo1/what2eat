package com.viola.what2eat.business;

import com.viola.what2eat.model.Shop;
import com.viola.what2eat.repo.ShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ShopPicker {

    @Autowired
    private ShopRepo shopRepo;

    public String randomPick() {

        List<Shop> shops = (List<Shop>) shopRepo.findAll();

        int count = shops.size();

        if(count <= 0) {
            return null;
        }

        Random rand = new Random();
        int n = rand.nextInt(count);

        return shops.get(n).getName();
    }
}
