package com.hape.furniture.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@TableName("furniture")
@Data
//家具实体
public class Furniture extends Model<Furniture> {
    @TableId(type = IdType.AUTO)
    private Integer fid;//商品id
    private String fname;//商品名称
    private float price;//商品价格
    private Integer cid;//商品种类id
    @TableField(exist = false)
    private Integer count;//数量
    @TableField(exist = false)
    private float total;//总价
    @TableLogic
    private Integer del;//删除标记
    @TableField(exist = false)
    private Image image;
    @TableField(exist = false)
    private Category category;
}
