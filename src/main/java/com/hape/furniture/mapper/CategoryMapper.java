package com.hape.furniture.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hape.furniture.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
