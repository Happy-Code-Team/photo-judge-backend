package com.photo.judge.model.entity.cartest;

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
@TableName("car_test")
@Desc("用来测试的，后续会删掉")
@Accessors(chain = true)//开启链式
public class CarTestExtend extends CarTest implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private List<String> idList;//id主键集合
}
