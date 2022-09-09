package com.hape.furniture.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@TableName("oorder")
@Data
public class Order extends Model<Order> {
    @TableId(type = IdType.AUTO)
    private Integer oid;//主键id
    private String oNum;//订单编号
    private String oName;//真实姓名
    private Long phoneNum;//手机号码
    private String recAddr;//收获地址
    private String oTime;//下单时间
    private float total;//订单总金额
    @TableField("deliver_status")
    private Integer deliverStatus;//发货标记 0为未发货,1为已发货
    @TableField("rec_status")
    private Integer recStatus;//确认收货标记 0为未确认,1为已确认
    private Integer uid;//用户id
    @TableLogic
    private Integer del;//逻辑删除标记
    @TableField(exist = false)
    private User user;//用户
    @TableField(exist = false)
    private List<Furniture> furniture;//订单中包含的商品
}
