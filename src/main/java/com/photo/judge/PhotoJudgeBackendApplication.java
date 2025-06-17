package com.photo.judge;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.photo.judge.dao")
public class PhotoJudgeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoJudgeBackendApplication.class, args);
	}

}
