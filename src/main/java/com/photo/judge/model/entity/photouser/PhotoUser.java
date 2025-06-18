package com.photo.judge.model.entity.photouser;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.photo.judge.common.annotation.Desc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("photo_user")
@Desc("用户信息表")
public class PhotoUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    @Desc("用户id")
    private String userId;

    @TableField("user_name")
    @Desc("用户名")
    private String userName;

    @TableField("password")
    @Desc("密码")
    private String password;

    @TableField("email")
    @Desc("邮箱")
    private String email;

}
