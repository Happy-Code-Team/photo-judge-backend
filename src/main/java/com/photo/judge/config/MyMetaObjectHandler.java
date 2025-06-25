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

        this.strictInsertFill(metaObject, "creater", String.class, creater);//创建人
        this.strictInsertFill(metaObject, "createTime", String.class, time);//创建时间
        this.strictInsertFill(metaObject, "ts", String.class, time);//时间戳
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String updater = getCurrentUser();//修改人
        String time = LocalDateTime.now().format(formatter);//获取时间戳

        this.strictUpdateFill(metaObject, "updater", String.class, updater);//修改人
        this.strictUpdateFill(metaObject, "updateTime", String.class, time);//修改时间
        this.strictUpdateFill(metaObject, "ts", String.class, time);//时间戳
    }


    // 获取当前用户的逻辑（示例）
    private String getCurrentUser() {
        return "admin"; // 示例用户名
    }
}