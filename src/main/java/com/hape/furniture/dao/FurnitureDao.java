package com.hape.furniture.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hape.furniture.pojo.Furniture;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FurnitureDao {
    IPage<Furniture> findAllBySearch(int currentPage,int pageSize,String search);
    IPage<Furniture> findByCategory(int currentPage,int pageSize,int cid);
    List<Furniture> findAll();
    Furniture findByFid(int fid);
    Furniture findByFidAndDel(int fid);
    int insert(Furniture furniture);
    boolean update(Furniture furniture);
    boolean del(int fid);
}
