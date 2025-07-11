package com.photo.judge.model.entity.userinfo;

import com.baomidou.mybatisplus.annotation.*;
import com.photo.judge.common.annotation.Desc;
import com.photo.judge.common.model.param.PageParam;
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
public class UserInfo extends PageParam implements Serializable {

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

    @TableField(value = "creater", fill = FieldFill.INSERT)
    @Desc("创建人 ")
    private String creater;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @Desc("创建时间")
    private String createTime;

    @TableField(value = "updater", fill = FieldFill.UPDATE)
    @Desc("修改人")
    private String updater;

    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    @Desc("修改时间")
    private String updateTime;

    @TableField(value = "ts", fill = FieldFill.INSERT_UPDATE)
    @Desc("时间戳")
    private String ts;

}