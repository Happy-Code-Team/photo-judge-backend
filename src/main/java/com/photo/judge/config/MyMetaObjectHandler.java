package com.photo.judge.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * mybatis-plus元数据处理器
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void insertFill(MetaObject metaObject) {
        String creater = getCurrentUser();//创建人
        String time = LocalDateTime.now().format(formatter);//获取时间戳

        //使用fillStrategy：仅当字段为空时赋值
        this.fillStrategy(metaObject, "creater", creater);//创建人
        this.fillStrategy(metaObject, "createTime", time);//创建时间
        this.fillStrategy(metaObject, "ts", time);//时间戳
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String updater = getCurrentUser();//修改人
        String time = LocalDateTime.now().format(formatter);//获取时间戳

        //使用fillStrategy：仅当字段为空时赋值
        this.fillStrategy(metaObject, "updater", updater);//修改人
        this.fillStrategy(metaObject, "updateTime", time);//修改时间
        this.fillStrategy(metaObject, "ts", time);//时间戳
    }


    // 获取当前用户的逻辑（示例）
    private String getCurrentUser() {
        return "admin"; // 示例用户名
    }
}