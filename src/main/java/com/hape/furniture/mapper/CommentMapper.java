package com.hape.furniture.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hape.furniture.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    @Select({"SELECT * FROM comment WHERE fid = #{fid}"})
    List<Comment> findDelByFid(int fid);
    @Select({"SELECT * FROM comment WHERE del = 1"})
    List<Comment> findDel();
    @Update({"UPDATE COMMENT SET del = 0 WHERE cid = #{cid}"})
    boolean renew(int cid);
}
