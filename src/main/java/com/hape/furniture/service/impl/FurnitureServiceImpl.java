package com.hape.furniture.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hape.furniture.dao.*;
import com.hape.furniture.mapper.CommentMapper;
import com.hape.furniture.mapper.FurnitureMapper;
import com.hape.furniture.mapper.ImageMapper;
import com.hape.furniture.pojo.*;
import com.hape.furniture.service.FurnitureService;
import com.hape.furniture.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class FurnitureServiceImpl implements FurnitureService {
    @Autowired
    private FurnitureDao furnitureDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private FileUtil fileUtil;
    @Autowired
    private Image image;
    @Autowired
    private ImageDao imageDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private ShopCarDao shopCarDao;
    @Value("${pageSize.furniture.bySearch}")
    private int pageSizeBySearch;
    @Value("${pageSize.furniture.byCategory}")
    private int pageSizeByCategory;
    @Autowired
    private FurnitureMapper furnitureMapper;
    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private CommentMapper commentMapper;


    /**
     * 搜索
     * @param currentPage
     * @param search
     * @return
     */
    @Override
    public IPage<Furniture> findAllBySearch(int currentPage, String search) {
        IPage<Furniture> page = furnitureDao.findAllBySearch(currentPage, pageSizeBySearch, search);
        for (Furniture furniture : page.getRecords()) {
            Category category = categoryDao.findByCid(furniture.getCid());
            Image image = imageDao.findByFid(furniture.getFid());
            furniture.setCategory(category);
            furniture.setImage(image);
        }
        return page;
    }

    /**
     * 根据种类查询
     * @param currentPage
     * @param cid
     * @return
     */
    @Override
    public IPage<Furniture> findByCategory(int currentPage, int cid) {
        Category category = categoryDao.findByCid(cid);
        IPage<Furniture> page = furnitureDao.findByCategory(currentPage,pageSizeByCategory ,category.getCid());
        for (Furniture f : page.getRecords()) {
            f.setCategory(category);
            Image image = imageDao.findByFid(f.getFid());
            f.setImage(image);
        }
        return page;
    }

    /**
     * 查询所有家具，返回指定数量的家具
     * @param count
     * @return
     */
    @Override
    public List<Furniture> findByCount(int count) {
        ArrayList<Furniture> furnitures = new ArrayList<>();
        List<Furniture> list = furnitureDao.findAll();
        for (int i = 0; i < count; i++) {
            if (i==list.size()){
                break;
            }
            furnitures.add(list.get(i));
        }
        for (Furniture furniture : furnitures) {
            Category c = categoryDao.findByCid(furniture.getCid());
            Image image = imageDao.findByFid(furniture.getFid());
            furniture.setCategory(c);
            furniture.setImage(image);
        }
        return furnitures;
    }

    /**
     * 查询所有已经删除的家具
     * @return
     */
    @Override
    public List<Furniture> findDel() {
        List<Furniture> list = furnitureMapper.findAllDel();
        for (Furniture furniture : list) {
            Category category = categoryDao.findByCid(furniture.getCid());
            Image image = imageMapper.findByFid(furniture.getFid());
            furniture.setCategory(category);
            furniture.setImage(image);
        }
        return list;
    }

    /**
     * 根据id查询家具
     * @param fid
     * @return
     */
    @Override
    public Furniture findByFid(int fid) {
        Furniture furniture = furnitureDao.findByFid(fid);
        Category category = categoryDao.findByCid(furniture.getCid());
        furniture.setCategory(category);
        Image image = imageDao.findByFid(fid);
        furniture.setImage(image);
        return furniture;
    }

    /**
     * 存储图片到temp
     * @param file
     * @param name
     */
    public void addTempPicture(MultipartFile file,String name){
        String filename = file.getOriginalFilename();
        String[] split = filename.split("\\.");
        String suffix = "."+split[split.length-1];
        if(!(suffix.equals(".jpg")||suffix.equals(".jpeg")||suffix.equals(".png"))){
            return;
        }
        String tempPath = fileUtil.getResourcePath()+"/static/img/temp";
        fileUtil.upLoadImg(file,tempPath,name+suffix);
    }

    /**
     * 添加家具
     * @param furniture
     * @return
     */
    @Override
    public void add(MultipartFile[] files,MultipartFile coverFile,Furniture furniture) throws IOException {
        savePicture(files,coverFile,furniture);

        int fid = furnitureDao.insert(furniture);

        image.setFid(fid);
        imageDao.insert(image);
    }

    /**
     * 修改
     * @param furniture
     * @return
     */
    @Override
    public void update(MultipartFile cover,MultipartFile one,MultipartFile two,MultipartFile three,MultipartFile four,Furniture furniture) throws IOException {
        Category category = categoryDao.findByCid(furniture.getCid());
        Image image = imageDao.findByFid(furniture.getFid());
        Furniture oldFurniture = findByFid(furniture.getFid());
        String oldPath = fileUtil.getResourcePath()+"static/img/"+oldFurniture.getCategory().getEnglishName();
        String savePath = fileUtil.getResourcePath()+"static/img/"+category.getEnglishName();
        if(cover!=null){
            String name = updatePicture(cover, new File(oldPath + "/" + image.getCover()), savePath);
            this.image.setCover(name);
        }
        if(one!=null){
            String name = updatePicture(one, new File(oldPath + "/" + image.getOne()), savePath);
            this.image.setOne(name);
        }
        if(two!=null){
            String name = updatePicture(two, new File(oldPath + "/" + image.getTwo()), savePath);
            this.image.setTwo(name);
        }
        if(three!=null){
            String name = updatePicture(three, new File(oldPath + "/" + image.getThree()), savePath);
            this.image.setThree(name);
        }
        if(four!=null){
            String name = updatePicture(four, new File(oldPath + "/" + image.getFour()), savePath);
            this.image.setFour(name);
        }

        furnitureDao.update(furniture);
        int fid = furniture.getFid();
        this.image.setFid(fid);
        this.image.setIid(image.getIid());
        imageDao.update(this.image);
    }

    /**
     * 删除家具
     * @param fid
     * @return
     */
    @Override
    public boolean del(int fid) {
//        Furniture furniture = furnitureDao.findByFid(fid);
//        Category category = categoryDao.findByCid(furniture.getCid());
//        Image image = imageDao.findByFid(fid);
//        String oldPath = fileUtil.getResourcePath()+"static/img/"+category.getEnglishName();
//        //下架商品的图片目录
//        String newPath = fileUtil.getResourcePath()+"static/img/deleted";
//        String cover = image.getCover();
//        String one = image.getOne();
//        String two = image.getTwo();
//        String three = image.getThree();
//        String four = image.getFour();
//        //将商品图片转移到下架目录
//        fileUtil.transfer(oldPath,newPath,cover);
//        fileUtil.transfer(oldPath,newPath,one);
//        fileUtil.transfer(oldPath,newPath,two);
//        fileUtil.transfer(oldPath,newPath,three);
//        fileUtil.transfer(oldPath,newPath,four);
        //删除家具图片
        boolean flag = imageDao.delByIid(imageDao.findByFid(fid).getIid());
        if(!flag){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        List<Comment> list = commentDao.findByFid(fid);
        if(list!=null){
            for (Comment comment : list) {
                //删除对应评论
                flag = commentDao.del(comment.getCid());
                if(!flag){
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return false;
                }
            }
        }
//        List<ShopCar> shopCarList = shopCarDao.findByFid(fid);
//        if(shopCarList!=null){
//            for (ShopCar shopCar : shopCarList) {
//                shopCarDao.delete(shopCar.getSid());
//            }
//        }
        //最后删除家具
        flag = furnitureDao.del(fid);
        if(!flag){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    /**
     * 恢复家具
     * @param fid
     * @return
     */
    public boolean renew(int fid){
        boolean flag;
        //恢复家具
        Furniture furniture = furnitureMapper.findByFidAndDel(fid);
        furniture.setDel(0);
        flag = furnitureMapper.renew(fid);
        if(!flag){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        //恢复图片
        Image image = imageMapper.findByFid(fid);
        image.setDel(0);
        flag = imageMapper.renew(image.getIid());
        if(!flag){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        //恢复评论
        List<Comment> comments = commentMapper.findDelByFid(fid);
        for (Comment comment : comments) {
            comment.setDel(0);
            flag = commentMapper.renew(comment.getCid());
            if(!flag){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return false;
            }
        }
        return true;
    }

    /**
     * 保存图片
     * @param files
     * @param coverFile
     * @param furniture
     * @throws IOException
     */
    private void savePicture(MultipartFile[] files,MultipartFile coverFile,Furniture furniture) throws IOException {
        //封面图片和四张展示图片要存储的文件名
        String cover,one = null,two = null,three = null,four = null;
        Category category = categoryDao.findByCid(furniture.getCid());
        String savePath = fileUtil.getResourcePath()+"static/img/"+category.getEnglishName();
        //存储封面图片
        cover = UUID.randomUUID().toString()+coverFile.getOriginalFilename().substring(coverFile.getOriginalFilename().lastIndexOf("."));
        coverFile.transferTo(new File(savePath+"/"+cover));
        //存储4张展示图片
        for (int i=0;i< files.length;i++) {
            MultipartFile file = files[i];
            //后缀名
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            switch (i){
                case 0: one = UUID.randomUUID().toString()+suffix;
                    file.transferTo(new File(savePath+"/"+one));
                    break;
                case 1: two = UUID.randomUUID().toString()+suffix;
                    file.transferTo(new File(savePath+"/"+two));
                    break;
                case 2: three = UUID.randomUUID().toString()+suffix;
                    file.transferTo(new File(savePath+"/"+three));
                    break;
                case 3: four = UUID.randomUUID().toString()+suffix;
                    file.transferTo(new File(savePath+"/"+four));
                    break;
            }
        }
        image.setCover(cover);
        image.setOne(one);
        image.setTwo(two);
        image.setThree(three);
        image.setFour(four);
    }

    private String updatePicture(MultipartFile file,File oldFile,String savePath) throws IOException {
        oldFile.delete();
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String saveName = UUID.randomUUID().toString() + suffix;
        file.transferTo(new File(savePath+"/"+saveName));
        return saveName;
    }
}
