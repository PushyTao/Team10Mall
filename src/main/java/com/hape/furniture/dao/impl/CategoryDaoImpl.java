package com.hape.furniture.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hape.furniture.dao.CategoryDao;
import com.hape.furniture.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {
    @Autowired
    private Category category;

    /**
     * 根据种类名称查询
     * @param cname
     * @return
     */
    @Override
    public Category findByCname(String cname) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("cname",cname);
        return category.selectOne(wrapper);
    }

    /**
     * 查询所有种类
     * @return
     */
    @Override
    public List<Category> findAll() {
        return category.selectAll();
    }

    /**
     * 根据id查询种类
     * @param cid
     * @return
     */
    @Override
    public Category findByCid(int cid) {
        category.setCid(cid);
        return category.selectById();
    }
}
