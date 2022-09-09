package com.hape.furniture.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.stereotype.Component;

@TableName("fo_record")
@Component
@Data
public class FoRecord extends Model<FoRecord> {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer fid;
    private Integer oid;
    private Integer count;
    private float total;
    @TableLogic
    private Integer del;
}
