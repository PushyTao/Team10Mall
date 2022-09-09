package com.hape.furniture.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hape.furniture.pojo.Comment;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommentDao {
    int insert(Comment comment);
    IPage<Comment> findAll(int currentPage,int pageSize);
    List<Comment> findByFid(int fid);
    List<Comment> findDelByFid(int fid);
    List<Comment> findParentByUid(int uid);
    List<Comment> findByParentId(int parentId);
    Comment findById(int cid);
    boolean del(int cid);
    boolean update(Comment comment);
}
