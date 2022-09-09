package com.hape.furniture.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@TableName("user")
//用户实体
public class User extends Model<User> {
    @TableId(type = IdType.AUTO)
    private Integer uid;//用户id
    private String uname;//用户昵称
    private String passwd;//用户密码
    private Integer gender;//用户性别
    private String birthday;//出生日期
    @TableField("phone_num")
    private long phoneNum;//电话号码
    private String city;//居住地
    private String address;//联系地址
    private Integer role;//角色标识
}
