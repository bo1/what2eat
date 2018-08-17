package com.viola.what2eat.ws;


import com.viola.what2eat.business.ShopPicker;
import com.viola.what2eat.model.Shop;
import com.viola.what2eat.repo.ShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class What2eatWebService {

    @Autowired
    private ShopRepo shopRepo;

    @Autowired
    private ShopPicker shopPicker;

    @RequestMapping("/getShops")
    public List<Shop> getShops() {
        return (List<Shop>) shopRepo.findAll();
    }

    @RequestMapping("/delShopId")
    public String delShopId(@RequestParam(value = "id") long id) {
        Optional<Shop> o = shopRepo.findById(id);
        if(o.isPresent()) {
            shopRepo.delete(o.get());
            return "Deletion successfully done";
        } else {
            return "Shop ID does not exist";
        }
    }

    @RequestMapping("/delShopName")
    public String delShopId(@RequestParam(value = "name") String name) {
        Optional<Shop> o = shopRepo.findByName(name);
        if(o.isPresent()) {
            shopRepo.delete(o.get());
            return "Deletion successfully done";
        } else {
            return "Shop ID does not exist";
        }
    }

    @RequestMapping("/addShop")
    public String addShop(@RequestParam(value = "name") String name) {

        Optional<Shop> o = shopRepo.findByName(name);
        if(o.isPresent()) {
            return "Shop " + name + " already exists";
        } else {
            Shop s = new Shop(name);
            shopRepo.save(s);
            return "Shop added successfully";
        }

    }

    @RequestMapping("/what2eat")
    public String addShop() {
        String pick = shopPicker.randomPick();
        if(pick == null) {
            return "Hmm 你还没有加入喜欢的店呢~";
        } else {
            return "决定了！今天就吃 [" + pick + "] 吧! *^_^* ~";
        }
    }

}
