package com.hape.furniture.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hape.furniture.pojo.ShopCar;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ShopCarMapper extends BaseMapper<ShopCar> {
}
