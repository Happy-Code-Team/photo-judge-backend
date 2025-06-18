package com.photo.judge.controller.cartest;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.photo.judge.model.entity.cartest.CarTestExtend;
import com.photo.judge.service.cartest.CarTestService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class CarTestController {

	@Resource
	private CarTestService carTestService;

	/** 测试 @SneakyThrows 注解的返回信息(统一异常处理)**/
	@RequestMapping(value = "/cartest/list")
	@ResponseBody
	public List<CarTestExtend> list() {
		List<CarTestExtend> list = carTestService.list();
		return list;
	}

	/** 测试 @SneakyThrows 注解的返回信息(统一异常处理)**/
	@RequestMapping(value = "/cartest/selectByIdList")
	@ResponseBody
	public List<CarTestExtend> selectByIdList(@RequestBody List<String> idList) {
		MPJLambdaWrapper<CarTestExtend> wrapper = new MPJLambdaWrapper<>();
		wrapper.in(CarTestExtend::getId, idList);
		List<CarTestExtend> list1 = carTestService.list(wrapper);

		List<CarTestExtend> list2 = carTestService.lambdaQuery().in(CarTestExtend::getId, idList).list();
		return list1;
	}

}
