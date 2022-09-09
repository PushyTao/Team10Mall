package com.hape.furniture.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hape.furniture.pojo.Furniture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface FurnitureMapper extends BaseMapper<Furniture> {
    @Select({"SELECT * FROM FURNITURE WHERE FID = #{fid}"})
    Furniture findByFidAndDel(int fid);
    @Select({"SELECT * FROM FURNITURE WHERE DEL = 1"})
    List<Furniture> findAllDel();
    @Update({"UPDATE FURNITURE SET del = 0 WHERE fid = #{fid}"})
    boolean renew(int fid);
}
