package com.hape.furniture.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hape.furniture.pojo.Image;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ImageMapper extends BaseMapper<Image> {
    @Select({"SELECT * FROM IMAGE WHERE FID = #{fid}"})
    Image findByFid(int fid);
    @Update({"UPDATE IMAGE SET del = 0 WHERE iiD = #{iid}"})
    boolean renew(int iid);
}
