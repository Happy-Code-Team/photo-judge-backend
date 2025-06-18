package com.photo.judge.model.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.photo.judge.common.annotation.Desc;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
@Desc("用户表")
@Accessors(chain = true)//开启链式
@Builder
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Desc("主键id")
    private String id;

    @TableField("user_code")
    @Desc("用户编码")
    private String userCode;

    @TableField("user_name")
    @Desc("用户名称")
    private String userName;

    @TableField("password")
    @Desc("密码")
    private String password;

    @TableField("email")
    @Desc("邮箱")
    private String email;

}
