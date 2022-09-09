package com.hape.furniture.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@TableName("category")
@Data
//家具种类实体
public class Category extends Model<Category> {
    @TableId(type = IdType.AUTO)
    private Integer cid;//种类id
    private String cname;//种类名称
    @TableField("english_name")
    private String englishName;//种类英文名称
}
