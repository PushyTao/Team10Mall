package com.hape.furniture.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hape.furniture.dao.FurnitureDao;
import com.hape.furniture.mapper.FurnitureMapper;
import com.hape.furniture.pojo.Furniture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class FurnitureDaoImpl implements FurnitureDao {
    @Autowired
    private Furniture furniture;
    @Autowired
    private FurnitureMapper furnitureMapper;

    /**
     * 根据内容和当前页数模糊搜索
     * @param search
     * @return
     */
    @Override
    public IPage<Furniture> findAllBySearch(int currentPage, int pageSize, String search) {
        QueryWrapper<Furniture> wrapper = new QueryWrapper<>();
        wrapper.like("fname",search);
        Page<Furniture> page = new Page<>(currentPage,pageSize);
        return furnitureMapper.selectPage(page,wrapper);
    }

    /**
     * 根据种类查询
     * @param currentPage
     * @param cid
     * @return
     */
    @Override
    public IPage<Furniture> findByCategory(int currentPage,int pageSize, int cid) {
        QueryWrapper<Furniture> wrapper = new QueryWrapper<>();
        wrapper.eq("cid",cid);
        Page<Furniture> page = new Page<>(currentPage, pageSize);
        return furnitureMapper.selectPage(page,wrapper);
    }

    /**
     * 查询所有家具
     * @return
     */
    @Override
    public List<Furniture> findAll() {
        return furniture.selectAll();
    }

    /**
     * 根据id查询家具
     * @param fid
     * @return
     */
    @Override
    public Furniture findByFid(int fid) {
        furniture.setFid(fid);
        return furniture.selectById();
    }

    @Override
    public Furniture findByFidAndDel(int fid) {
        return furnitureMapper.findByFidAndDel(fid);
    }

    /**
     * 插入
     * @param furniture
     * @return
     */
    @Override
    public int insert(Furniture furniture) {
        furniture.insert();
        return furniture.getFid();
    }

    /**
     * 修改
     * @param furniture
     * @return
     */
    @Override
    public boolean update(Furniture furniture) {
        return furniture.updateById();
    }

    /**
     * 删除
     * @param fid
     * @return
     */
    @Override
    public boolean del(int fid) {
        furniture.setFid(fid);
        return furniture.deleteById();
    }
}
