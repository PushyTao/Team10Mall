package com.hape.furniture.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hape.furniture.pojo.Furniture;
import com.hape.furniture.service.FurnitureService;
import com.hape.furniture.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/f")
@CrossOrigin
public class FurnitureController {
    @Autowired
    private FurnitureService furnitureService;
    @Autowired
    private FileUtil fileUtil;
    @Autowired
    private Furniture furniture;

    /**
     * 搜索
     */
    @GetMapping("/findBySearch/{currentPage}")
    public IPage<Furniture> search(@PathVariable("currentPage") int currentPage,String search){
        return furnitureService.findAllBySearch(currentPage,search);
    }

    /**
     * 根据种类查询
     * @param currentPage
     * @param
     * @return
     */
    @GetMapping("/findByCategory/{currentPage}/{cid}")
    public IPage<Furniture> findByCategory(@PathVariable("currentPage") int currentPage,@PathVariable("cid") int cid){
        return furnitureService.findByCategory(currentPage, cid);
    }

    /**
     * 返回指定数量的家具
     * @param count
     * @return
     */
    @GetMapping("/findByCount/{count}")
    public List<Furniture> findAllByCount(@PathVariable("count") int count){
        return furnitureService.findByCount(count);
    }

    /**
     * 根据id查询家具
     * @param fid
     * @return
     */
    @GetMapping("findByFid/{fid}")
    public Furniture detail(@PathVariable("fid") int fid){
        return furnitureService.findByFid(fid);
    }

    /**
     * 查询所有删除的家具
     * @return
     */
    @GetMapping("/findDel")
    public List<Furniture> findDel(){
        return furnitureService.findDel();
    }


    /**
     * 添加家具
     * @param
     * @return
     */
    @PostMapping("/insert")
    public boolean add(MultipartFile[] show,MultipartFile cover,Furniture furniture){
        try {
            furnitureService.add(show,cover, furniture);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 修改
     * @param
     * @return
     */
    @PutMapping("/update")
    public boolean update(MultipartFile cover,MultipartFile one,MultipartFile two,MultipartFile three,MultipartFile four,Furniture furniture) {
        try {
            furnitureService.update(cover, one, two, three, four, furniture);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 恢复家具
     * @param fid
     * @return
     */
    @PutMapping("/renew/{fid}")
    public boolean renew(@PathVariable("fid") int fid){
        return furnitureService.renew(fid);
    }

    /**
     * 删除家具
     * @param fid
     * @return
     */
    @DeleteMapping("/del/{fid}")
    public boolean del(@PathVariable("fid") int fid){
        return furnitureService.del(fid);
    }

}
