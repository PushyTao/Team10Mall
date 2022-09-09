package com.hape.furniture.controller;

import com.hape.furniture.pojo.Category;
import com.hape.furniture.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 查询所有种类
     * @return
     */
    @GetMapping("/findAll")
    public List<Category> findAll(){
        return categoryService.findAll();
    }
}
