package com.hape.furniture.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hape.furniture.dao.ImageDao;
import com.hape.furniture.pojo.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageDaoImpl implements ImageDao {
    @Autowired
    private Image image;

    /**
     * 插入
     * @param image
     * @return
     */
    @Override
    public int insert(Image image) {
        image.insert();
        return image.getIid();
    }

    /**
     * 修改
     * @param image
     * @return
     */
    @Override
    public boolean update(Image image) {
        return image.updateById();
    }

    /**
     * 根据家具id查找
     * @param fid
     * @return
     */
    @Override
    public Image findByFid(int fid) {
        QueryWrapper<Image> wrapper = new QueryWrapper<>();
        wrapper.eq("fid",fid);
        return image.selectOne(wrapper);
    }

    /**
     * 根据id删除
     * @param iid
     * @return
     */
    @Override
    public boolean delByIid(int iid) {
        image.setIid(iid);
        return image.deleteById();
    }

    @Override
    public List<Image> findAll() {
        return image.selectAll();
    }
}
