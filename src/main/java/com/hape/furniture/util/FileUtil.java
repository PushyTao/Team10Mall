package com.hape.furniture.util;

import com.hape.furniture.pojo.Category;
import lombok.Getter;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLDecoder;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtil {
    @Getter
    private String resourcePath;//resource目录位置

    /**
     * 设置resource目录
     */
    public void setResourcePath(){
        try {
            resourcePath = ResourceUtils.getURL("classpath:").getPath();
            resourcePath = URLDecoder.decode(resourcePath,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder(resourcePath);
        sb.replace(0,1,"");
        resourcePath = sb.toString();
    }

    /**
     * 创建img基本目录
     * @param categories 种类
     */
    public void createImgBaseDir(List<Category> categories){
        File file;
        file = new File(resourcePath+"static");
        file.mkdir();
        File baseFile = new File(file.getPath()+"/img");
        baseFile.mkdir();
        file = new File(baseFile.getPath()+"/temp");//临时缓存目录
        file.mkdir();
        file = new File(baseFile.getPath()+"/deleted");//下架商品的图片目录
        file.mkdir();
        for (Category category : categories) {
            String name = category.getEnglishName();
            file = new File(baseFile.getPath() + "/" + name);
            if(file.exists()){
               continue;
            }
            file.mkdir();
        }
    }

    /**
     * 使用UUID将路径下的指定文件重命名
     * @param path
     * @param name
     */
    public String renameByUUID(String path,String name){
        String suffix = ".jpg";
        File file = new File(path+"/"+name+suffix);
        if(!file.exists()){
            suffix = ".png";
            file = new File(path+"/"+name+suffix);
        }
        if(!file.exists()){
            suffix = ".jpeg";
            file = new File(path+"/"+name+suffix);
        }
        File newFile = new File(path+"/"+ UUID.randomUUID().toString()+suffix);
        file.renameTo(newFile);
        return newFile.getName();
    }

    /**
     * 转移到新目录
     * @param oldPath
     * @param newPath
     * @param name
     */
    public void transfer(String oldPath,String newPath,String name){
        File oldFile = new File(oldPath + "/" + name);
        File newFile = new File(newPath + "/" + name);
        oldFile.renameTo(newFile);
    }

    /**
     * 上传文件
     * @param imgFile
     * @param path
     * @return
     */
    public void upLoadImg(MultipartFile imgFile, String path,String name) {
        if(imgFile==null){
            return;
        }
        try {
            imgFile.transferTo(new File(path+"/"+name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileItem createFileItem(String filePath,String fileName){
        String fieldName = "file";
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        FileItem item = factory.createItem(fieldName,"text/plain",false,fileName);
        File newfile = new File(filePath);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        try (FileInputStream fis = new FileInputStream(newfile);
             OutputStream os = item.getOutputStream()) {
            while ((bytesRead = fis.read(buffer, 0, 8192))!= -1)
            {
                os.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * 获取图片字节流
     * @param file
     * @return
     * @throws IOException
     */
    public byte[] getByteImage(File file) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new FileInputStream(file));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", out);
        return out.toByteArray();
    }

    /**
     * 删除对应文件
     * @param path
     * @param name
     */
    public void delByName(String path,String name){
        String suffix = ".jpg";
        File file = new File(path + "/" + name + suffix);
        if(!file.exists()){
            suffix = ".jpeg";
            file = new File(path+"/"+name+suffix);
        }
        if(!file.exists()){
            suffix = ".png";
            file = new File(path+"/"+name+suffix);
        }
        file.delete();
    }

    /**
     * 清空路径下所有文件
     * @param path
     */
    public void clear(String path){
        File file = new File(path);
        File[] files = file.listFiles();
        if(files==null){
            return;
        }
        for (File f : files) {
            f.delete();
        }
    }

    /**
     * 删除指定路径文件
     * @param path
     */
    public void del(String path){
        new File(path).delete();
    }

}
