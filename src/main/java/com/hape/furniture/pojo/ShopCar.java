package com.hape.furniture.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@TableName("shopcar")
@Data
public class ShopCar extends Model<ShopCar> {
    @TableId(type = IdType.AUTO)
    private Integer sid;//购物车id
    private Integer fid;//商品id
    private Integer uid;//用户id
    private Integer count;//商品数量
    @TableLogic
    private Integer del;//删除标记
    @TableField(exist = false)
    private Furniture furniture;//商品
}
