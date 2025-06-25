package com.photo.judge.model.vo.userinfo;

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
@Desc("用户信息表VO")
@Accessors(chain = true)//开启链式
public class UserInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;//主键
    private String userCode;//用户编码
    private String userName;//用户昵称
    private String userPassword;//用户密码
    private String userAvatar;//用户头像链接

}
