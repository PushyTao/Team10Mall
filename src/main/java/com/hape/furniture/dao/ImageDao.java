package com.hape.furniture.dao;

import com.hape.furniture.pojo.Image;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageDao {
    int insert(Image image);
    boolean update(Image image);
    Image findByFid(int fid);
    boolean delByIid(int iid);
    List<Image> findAll();
}
