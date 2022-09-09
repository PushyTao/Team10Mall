package com.hape.furniture.controller;

import com.hape.furniture.pojo.ShopCar;
import com.hape.furniture.pojo.User;
import com.hape.furniture.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("shopcar")
public class ShopCarController {
    @Autowired
    private ShopCarService shopCarService;
    @Autowired
    private UserController userController;

    /**
     * 添加购物车
     * @param
     * @param fid
     * @return
     */
    @PostMapping("/addShopCar")
    public boolean addShopCar(int count, int fid,int uid){
        return shopCarService.addShopCar(count, uid, fid);
    }

    /**
     * 查询当前用户购物车
     * @param session
     * @return
     */
    @GetMapping("/findShopCars")
    public List<ShopCar> findShopCars(HttpSession session){
        User user = userController.getUserInfo(session);
        if(user==null){
            return null;
        }
        return shopCarService.findByUid(user.getUid());
    }

    /**
     * 根据id删除购物车
     * @param sids
     * @return
     */
    @DeleteMapping("del")
    public boolean deleteBySid(int[] sids){
        return shopCarService.deleteBySid(sids);
    }
}
