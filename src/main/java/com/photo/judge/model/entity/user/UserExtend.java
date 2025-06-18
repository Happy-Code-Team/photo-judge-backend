package com.photo.judge.model.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.photo.judge.common.annotation.Desc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
@Desc("用户表#扩展")
public class UserExtend extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private List<String> idList;//主键id集合

}
