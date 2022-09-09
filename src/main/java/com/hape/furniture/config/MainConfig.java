package com.hape.furniture.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.hape.furniture.mapper")
public class MainConfig {
    /**
     * 数据源
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "druid")
    public DruidDataSource druidDataSource(){
        return new DruidDataSource();
    }

    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
