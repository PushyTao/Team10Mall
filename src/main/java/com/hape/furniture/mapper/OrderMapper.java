package com.hape.furniture.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hape.furniture.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import java.util.List;

@Mapper
@Component
public interface OrderMapper extends BaseMapper<Order> {
    @Select({"SELECT * FROM oorder WHERE del = 1"})
    List<Order> findAllDel();
    @Select({"SELECT * FROM oorder WHERE uid = #{uid} ORDER BY o_time DESC"})
    List<Order> findAllByUid(int uid);
    @Select({"SELECT * FROM oorder WHERE uid = #{uid} AND del = 1 ORDER BY o_time DESC"})
    List<Order> findAllDelByUid(int uid);
}
