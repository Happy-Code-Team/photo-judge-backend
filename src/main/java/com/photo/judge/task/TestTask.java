package com.photo.judge.task;

import com.photo.judge.model.entity.quartz.QuartzJob;
import org.springframework.stereotype.Component;

@Component(value = "testTask")
public class TestTask {
	public void execute(QuartzJob job) {
		System.out.println("定时任务执行中: " + job.getJobName());
	}
}
