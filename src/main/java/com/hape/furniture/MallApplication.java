package com.hape.furniture;

import com.hape.furniture.dao.CategoryDao;
import com.hape.furniture.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MallApplication implements CommandLineRunner {
    @Autowired
    private FileUtil fileUtil;
    @Autowired
    private CategoryDao categoryDao;

    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }

    /**
     * 创建img图片基本目录
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        fileUtil.createImgBaseDir(categoryDao.findAll());
    }
}
