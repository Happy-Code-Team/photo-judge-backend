package com.photo.judge.model.entity.cartest;

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
@TableName("car_test")
@Desc("用来测试的，后续会删掉")
@Accessors(chain = true)//开启链式
public class CarTest implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Desc("id逐渐")
    private String id;

    @TableField("name")
    @Desc("名称")
    private String name;

    @TableField("price")
    @Desc("价格")
    private String price;

    @TableField("user_id")
    @Desc("用户id主键")
    private String userId;

}
