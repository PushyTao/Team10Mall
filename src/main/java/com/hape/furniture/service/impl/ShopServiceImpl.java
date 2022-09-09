package com.hape.furniture.service.impl;

import com.hape.furniture.dao.CategoryDao;
import com.hape.furniture.dao.FurnitureDao;
import com.hape.furniture.dao.ImageDao;
import com.hape.furniture.dao.ShopCarDao;
import com.hape.furniture.mapper.FurnitureMapper;
import com.hape.furniture.mapper.ImageMapper;
import com.hape.furniture.pojo.Category;
import com.hape.furniture.pojo.Furniture;
import com.hape.furniture.pojo.Image;
import com.hape.furniture.pojo.ShopCar;
import com.hape.furniture.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
@Transactional
public class ShopServiceImpl implements ShopCarService {
    @Autowired
    private ShopCarDao shopCarDao;
    @Autowired
    private FurnitureDao furnitureDao;
    @Autowired
    private ShopCar shopCar;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ImageDao imageDao;
    @Autowired
    private FurnitureMapper furnitureMapper;
    @Autowired
    private ImageMapper imageMapper;

    /**
     * 添加购物车
     * @param count
     * @param uid
     * @param fid
     * @return
     */
    @Override
    public boolean addShopCar(int count,int uid,int fid) {
        shopCar.setCount(count);
        shopCar.setUid(uid);
        shopCar.setFid(fid);
        int sid = shopCarDao.insert(shopCar);
        if(sid!=0){
            return true;
        }
        return false;
    }

    /**
     * 根据用户id查询购物车
     * @param uid
     * @return
     */
    @Override
    public List<ShopCar> findByUid(int uid) {
        List<ShopCar> shopCars = shopCarDao.findByUid(uid);
        for (ShopCar shopCar : shopCars) {
//            Furniture furniture = furnitureDao.findByFid(shopCar.getFid());
            Furniture furniture = furnitureMapper.findByFidAndDel(shopCar.getFid());
            Category category = categoryDao.findByCid(furniture.getCid());
//            Image image = imageDao.findByFid(furniture.getFid());
            Image image = imageMapper.findByFid(furniture.getFid());
            furniture.setCategory(category);
            furniture.setImage(image);
            shopCar.setFurniture(furniture);
        }
        return shopCars;
    }

    /**
     * 根据id删除
     * @param sids
     * @return
     */
    @Override
    public boolean deleteBySid(int[] sids) {
        for (int sid : sids) {
            boolean flag = shopCarDao.delete(sid);
            if(!flag){
                //回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return false;
            }
        }
        return true;
    }
}
