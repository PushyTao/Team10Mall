package com.hape.furniture.config;

import com.hape.furniture.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private FileUtil fileUtil;

    /**
     * 静态资源映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        fileUtil.setResourcePath();
        System.out.println(fileUtil.getResourcePath());
        registry.addResourceHandler("/img/**").addResourceLocations("file:"+fileUtil.getResourcePath()+"static/img/");
    }

    /**
     * 跨域映射
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").
                allowedOriginPatterns("*").
                allowCredentials(true).
                allowedMethods("GET","POST","PUT","DELETE").
                maxAge(3600);
    }
}
