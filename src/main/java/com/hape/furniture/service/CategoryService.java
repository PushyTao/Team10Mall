package com.hape.furniture.service;

import com.hape.furniture.pojo.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> findAll();

}
