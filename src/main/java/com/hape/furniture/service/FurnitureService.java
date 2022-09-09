package com.hape.furniture.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hape.furniture.pojo.Furniture;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface FurnitureService {
    IPage<Furniture> findAllBySearch(int currentPage,String search);
    IPage<Furniture> findByCategory(int currentPage,int cid);
    List<Furniture> findByCount(int count);
    List<Furniture> findDel();
    Furniture findByFid(int fid);
    void addTempPicture(MultipartFile file,String name);
    void add(MultipartFile[] files,MultipartFile coverFile,Furniture furniture) throws IOException;
    void update(MultipartFile cover,MultipartFile one,MultipartFile two,MultipartFile three,MultipartFile four,Furniture furniture) throws IOException;
    boolean del(int fid);
    boolean renew(int fid);
}
