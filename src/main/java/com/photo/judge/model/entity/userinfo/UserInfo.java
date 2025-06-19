package com.photo.judge.model.entity.userinfo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.photo.judge.common.annotation.Desc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_info")
@Desc("用户信息表")
@Accessors(chain = true)//开启链式
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Desc("主键")
    private String id;

    @TableField("user_code")
    @Desc("用户编码")
    private String userCode;

    @TableField("user_name")
    @Desc("用户昵称")
    private String userName;

    @TableField("user_password")
    @Desc("用户密码")
    private String userPassword;

    @TableField("user_avatar")
    @Desc("用户头像链接")
    private String userAvatar;

    @TableField("creater")
    @Desc("创建人 ")
    private String creater;

    @TableField("create_time")
    @Desc("创建时间")
    private String createTime;

    @TableField("updater")
    @Desc("修改人")
    private String updater;

    @TableField("update_time")
    @Desc("修改时间")
    private String updateTime;

}