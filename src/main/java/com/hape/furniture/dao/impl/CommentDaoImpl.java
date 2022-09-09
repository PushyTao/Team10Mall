package com.hape.furniture.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hape.furniture.dao.CommentDao;
import com.hape.furniture.mapper.CommentMapper;
import com.hape.furniture.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {
    @Autowired
    private Comment comment;
    @Autowired
    private CommentMapper commentMapper;

    /**
     * 插入
     * @param comment
     * @return
     */
    @Override
    public int insert(Comment comment) {
        boolean flag = comment.insert();
        if(!flag){
            return 0;
        }
        return comment.getCid();
    }

    /**
     * 查询所有评论
     * @param currentPage
     * @return
     */
    @Override
    public IPage<Comment> findAll(int currentPage,int pageSize) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("ctime");
        Page<Comment> page = new Page<>(currentPage,pageSize);
        return commentMapper.selectPage(page,wrapper);
    }

    /**
     * 根据家具id查询评论
     * @param fid
     * @return
     */
    @Override
    public List<Comment> findByFid(int fid) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("fid",fid).orderByDesc("ctime");
        return comment.selectList(wrapper);
    }

    /**
     * 根据已家具id查询所有评论
     * @param fid
     * @return
     */
    @Override
    public List<Comment> findDelByFid(int fid) {
        return commentMapper.findDelByFid(fid);
    }

    /**
     * 根据parentId查询所有评论
     * @param uid
     * @return
     */
    @Override
    public List<Comment> findParentByUid(int uid) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid).eq("parentid",0);
        return comment.selectList(wrapper);
    }

    @Override
    public List<Comment> findByParentId(int parentId) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("parentid",parentId);
        return comment.selectList(wrapper);
    }

    /**
     * 根据评论id查询
     * @param cid
     * @return
     */
    public Comment findById(int cid){
        comment.setCid(cid);
        return comment.selectById();
    }

    /**
     * 删除
     * @param cid
     * @return
     */
    @Override
    public boolean del(int cid) {
        comment.setCid(cid);
        return comment.deleteById();
    }

    @Override
    public boolean update(Comment comment) {
        return comment.updateById();
    }
}
