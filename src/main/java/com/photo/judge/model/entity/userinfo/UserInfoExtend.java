package com.photo.judge.model.entity.userinfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.photo.judge.common.annotation.Desc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_info")
@Desc("用户信息表#扩展")
@Accessors(chain = true)//开启链式
public class UserInfoExtend extends UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private List<String> idList;//主键id集合

    @TableField(exist = false)
    private String startTime;//开始时间

    @TableField(exist = false)
    private String endTime;//结束时间
}