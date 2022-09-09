package com.hape.furniture.service.impl;

import com.hape.furniture.dao.CategoryDao;
import com.hape.furniture.pojo.Category;
import com.hape.furniture.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;
    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
