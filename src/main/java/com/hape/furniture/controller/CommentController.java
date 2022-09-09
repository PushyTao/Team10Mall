package com.hape.furniture.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hape.furniture.pojo.Comment;
import com.hape.furniture.pojo.Furniture;
import com.hape.furniture.pojo.User;
import com.hape.furniture.service.CommentService;
import com.hape.furniture.service.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private FurnitureService furnitureService;
    @Autowired
    private UserController userController;

    /**
     * 评论
     * @param comment
     * @return
     */
    @PostMapping("insert")
    public boolean discuss(Comment comment,String replayCid){
        if(replayCid!=null){
            comment.setParentid(Integer.parseInt(replayCid));
        }
        return commentService.discuss(comment);
    }

    /**
     * 根据家具id查询
     * @param fid
     * @return
     */
    @GetMapping("/findByFid/{fid}")
    public List<Comment> findCommentByFid(@PathVariable("fid") int fid){
        return commentService.findByFid(fid);
    }

    /**
     * 查询所有评论
     * @param currentPage
     * @return
     */
    @GetMapping("/findAll/{currentPage}")
    public IPage<Comment> findAll(@PathVariable("currentPage")int currentPage){
        return commentService.findAll(currentPage);
    }

    /**
     * 查询所有回复我的评论
     * @return
     */
    @GetMapping("/findParent")
    public List<Comment> findByParentId(HttpSession session){
        User user = userController.getUserInfo(session);
        return commentService.findParent(user.getUid());
    }

    /**
     * 查询所有删除的评论
     * @return
     */
    @GetMapping("/findDel")
    public List<Comment> findDel(){
        return commentService.findDel();
    }

    /**
     * 恢复评论
     * @param cid
     * @return
     */
    @PutMapping("renew/{cid}")
    public boolean renew(@PathVariable("cid") int cid){
        return commentService.renew(cid);
    }

    /**
     * 删除评论
     * @param cid
     * @return
     */
    @DeleteMapping("/del/{cid}")
    public boolean del(@PathVariable("cid") int cid){
        return commentService.del(cid);
    }
}
