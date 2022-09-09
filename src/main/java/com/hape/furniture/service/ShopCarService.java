package com.hape.furniture.service;

import com.hape.furniture.pojo.ShopCar;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShopCarService {
    boolean addShopCar(int count,int uid,int fid);
    List<ShopCar> findByUid(int uid);
    boolean deleteBySid(int[] sids);
}
