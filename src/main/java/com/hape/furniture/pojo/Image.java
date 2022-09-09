package com.hape.furniture.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@TableName("image")
public class Image extends Model<Image> {
    @TableId(type = IdType.AUTO)
    private Integer iid;
    private String cover;
    private String one;
    private String two;
    private String three;
    private String four;
    private Integer fid;
    @TableLogic
    private Integer del;
    @TableField(exist = false)
    private Furniture furniture;
    @TableField(exist = false)
    private byte[] coverByte;
    @TableField(exist = false)
    private byte[] oneByte;
    @TableField(exist = false)
    private byte[] twoByte;
    @TableField(exist = false)
    private byte[] threeByte;
    @TableField(exist = false)
    private byte[] fourByte;
}
