package com.hape.furniture.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hape.furniture.dao.*;
import com.hape.furniture.mapper.CommentMapper;
import com.hape.furniture.pojo.*;
import com.hape.furniture.service.CommentService;
import com.hape.furniture.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private FurnitureDao furnitureDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ImageDao imageDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private User user;
    @Value("${pageSize.comment}")
    private int commentPageSize;
    @Autowired
    private CommentMapper commentMapper;

    /**
     * 评论
     * @param comment
     * @return
     */
    @Override
    public boolean discuss(Comment comment) {
        String content = comment.getContent();
        System.out.println(content);
        StringBuilder sb = new StringBuilder(content);
        //将默认最外层<p>标签替换成<span>标签
        sb.replace(0,3,"<span>");
        sb.replace(content.length()-1,content.length()+3,"</span>");
        comment.setContent(sb.toString());
        System.out.println(sb.toString());
        int cid = commentDao.insert(comment);
        if(cid==0){
            return false;
        }
        return true;
    }

    /**
     * 根据家具id查询
     * @param fid
     * @return
     */
    @Override
    public List<Comment> findByFid(int fid) {
        List<Comment> comments = commentDao.findByFid(fid);
        for (Comment comment : comments) {
            User user = userDao.findByUid(comment.getUid());
            user.setBirthday(null);
            user.setPasswd(null);
            user.setGender(null);
            user.setRole(null);
            comment.setUser(user);
            if(comment.getParentid()!=0){
                Comment parentComment = commentDao.findById(comment.getParentid());
                parentComment.setUser(userDao.findByUid(parentComment.getUid()));
                comment.setParentComment(parentComment);
            }
        }
        return comments;
    }

    /**
     * 查询所有评论
     * @param currentPage
     * @return
     */
    @Override
    public IPage<Comment> findAll(int currentPage) {
        IPage<Comment> page = commentDao.findAll(currentPage, commentPageSize);
        List<Comment> records = page.getRecords();
        records = setCommentProperty(records);
        page.setRecords(records);
        return page;
    }

    /**
     * 查询所有回复我的评论
     * @return
     */
    @Override
    public List<Comment> findParent(int uid) {
        List<Comment> parents = commentDao.findParentByUid(uid);
        for (Comment parent : parents) {
            List<Comment> sons = commentDao.findByParentId(parent.getCid());
            for (Comment son : sons) {
                User user = userDao.findByUid(son.getUid());
                user.setPhoneNum(0);
                user.setCity(null);
                user.setAddress(null);
                user.setBirthday(null);
                user.setGender(null);
                user.setPasswd(null);
                user.setRole(null);
                son.setUser(user);
            }
            parent.setSonComment(sons);
        }
        parents = setCommentProperty(parents);
        return parents;
    }

    /**
     * 查询所有被删除的
     * @return
     */
    @Override
    public List<Comment> findDel() {
        List<Comment> list = commentMapper.findDel();
        list = setCommentProperty(list);
        return list;
    }

    /**
     * 删除
     * @param cid
     * @return
     */
    @Override
    public boolean del(int cid) {
        boolean flag;
        List<Comment> sons = commentDao.findByParentId(cid);
        for (Comment son : sons) {
            flag = commentDao.del(son.getCid());
            if(!flag){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return false;
            }
        }
        flag = commentDao.del(cid);
        if(!flag){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    /**
     * 恢复评论
     * @param cid
     * @return
     */
    @Override
    public boolean renew(int cid) {
        boolean flag = commentMapper.renew(cid);
        if(!flag){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    private List<Comment> setCommentProperty(List<Comment> comments){
        for (Comment comment : comments) {
            Furniture furniture = furnitureDao.findByFid(comment.getFid());
            Category category = categoryDao.findByCid(furniture.getCid());
            Image image = imageDao.findByFid(furniture.getFid());
            User user = userDao.findByUid(comment.getUid());
            user.setPhoneNum(0);
            user.setCity(null);
            user.setAddress(null);
            user.setBirthday(null);
            user.setGender(null);
            user.setPasswd(null);
            user.setRole(null);
            furniture.setCategory(category);
            furniture.setImage(image);
            comment.setFurniture(furniture);
            comment.setUser(user);
        }
        return comments;
    }
}
