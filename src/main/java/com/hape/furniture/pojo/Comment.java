package com.hape.furniture.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@TableName("comment")
@Data
//评论实体
public class Comment extends Model<Comment> {
    @TableId(type = IdType.AUTO)
    private Integer cid;//评论id
    private String content;//评论内容
    private String ctime;//评论时间
    private Integer uid;//评论者id
    private Integer parentid;//父评论id
    @TableLogic
    private Integer del;//删除标记
    @TableField(exist = false)
    private User user;//评论者昵称
    @TableField(exist = false)
    private Furniture furniture;//评论的商品
    private Integer fid;//评论商品id
    @TableField(exist = false)
    private Comment parentComment;
    @TableField(exist = false)
    private List<Comment> sonComment;
}
