package com.hape.furniture.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hape.furniture.dao.ShopCarDao;
import com.hape.furniture.pojo.ShopCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopCarDaoImpl implements ShopCarDao {
    @Autowired
    private ShopCar shopCar;

    /**
     * 插入
     * @param shopCar
     * @return
     */
    @Override
    public int insert(ShopCar shopCar) {
        boolean flag = shopCar.insert();
        if(flag){
            return shopCar.getSid();
        }
        return 0;
    }

    /**
     * 根据用户id查询购物车
     * @param uid
     * @return
     */
    @Override
    public List<ShopCar> findByUid(int uid) {
        QueryWrapper<ShopCar> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid);
        return shopCar.selectList(wrapper);
    }

    /**
     * 查询所有在购物车里的此家具
     * @param fid
     * @return
     */
    @Override
    public List<ShopCar> findByFid(int fid) {
        QueryWrapper<ShopCar> wrapper = new QueryWrapper<>();
        wrapper.eq("fid",fid);
        return shopCar.selectList(wrapper);
    }

    /**
     * 根据id删除
     * @param sid
     * @return
     */
    @Override
    public boolean delete(int sid) {
        shopCar.setSid(sid);
        return shopCar.deleteById();
    }
}
