package com.photo.judge;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 启动类
 */
@SpringBootApplication
@MapperScan("com.photo.judge.dao")
@EnableCaching
public class PhotoJudgeBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoJudgeBackendApplication.class, args);
    }

}
