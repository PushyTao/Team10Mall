package com.hape.furniture.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hape.furniture.pojo.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    boolean discuss(Comment comment);
    List<Comment> findByFid(int fid);
    IPage<Comment> findAll(int currentPage);
    List<Comment> findParent(int uid);
    List<Comment> findDel();
    boolean del(int cid);
    boolean renew(int cid);
}
