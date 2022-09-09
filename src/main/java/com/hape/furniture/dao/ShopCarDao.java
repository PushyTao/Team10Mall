package com.hape.furniture.dao;

import com.hape.furniture.pojo.ShopCar;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ShopCarDao {
    int insert(ShopCar shopCar);
    List<ShopCar> findByUid(int uid);
    List<ShopCar> findByFid(int fid);
    boolean delete(int sid);
}
