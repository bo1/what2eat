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
    public String getShops() {

        String shopNames = "";
        List<Shop> shops =  (List<Shop>) shopRepo.findAll();

        for(Shop shop : shops) {
            shopNames += "[" + shop.getName() + "]\n";
        }

        return shopNames;
    }

    @RequestMapping("/delShopId")
    public String delShopId(@RequestParam(value = "id") long id) {
        Optional<Shop> o = shopRepo.findById(id);
        if(o.isPresent()) {
            shopRepo.delete(o.get());
            return "搞定搞定";
        } else {
            return "就没有这个店 :)";
        }
    }

    @RequestMapping("/delShopName")
    public String delShopId(@RequestParam(value = "name") String name) {
        Optional<Shop> o = shopRepo.findByName(name);
        if(o.isPresent()) {
            shopRepo.delete(o.get());
            return "干掉辣鸡店：" + name;
        } else {
            return name + "? 不存在的不存在的";
        }
    }

    @RequestMapping("/addShop")
    public String addShop(@RequestParam(value = "name") String name) {

        Optional<Shop> o = shopRepo.findByName(name);
        if(o.isPresent()) {
            return "老哥，" + name + "已经在了呀";
        } else {
            Shop s = new Shop(name);
            shopRepo.save(s);
            return "完全o尼玛蛇皮棒棒8倍镜98k~";
        }

    }

    @RequestMapping(value = {"/what2eat", "/go"})
    public String pickShop() {
        String pick = shopPicker.randomPick();
        if(pick == null) {
            return "Hmm 还没有加入喜欢的店呢~";
        } else {
            return "决定了！今天就吃 [" + pick + "] 吧! *^_^* ~";
        }
    }

    @RequestMapping("/")
    public String welcome() {
        return "你要相信我，这个主页是会有的 ：）";
    }

}
