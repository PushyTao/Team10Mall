package com.hape.furniture.dao;

import com.hape.furniture.pojo.Category;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryDao {
    Category findByCname(String cname);
    List<Category> findAll();
    Category findByCid(int cid);
}
