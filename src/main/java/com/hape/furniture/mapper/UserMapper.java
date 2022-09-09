package com.hape.furniture.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hape.furniture.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {
}
