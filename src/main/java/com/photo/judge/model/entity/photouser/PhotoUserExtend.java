package com.photo.judge.model.entity.photouser;

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
@TableName("photo_user")
@Desc("用户信息表#扩展")
public class PhotoUserExtend extends PhotoUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private List<String> userIdList;//用户id集合

}
