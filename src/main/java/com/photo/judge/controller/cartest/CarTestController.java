package com.photo.judge.controller.cartest;

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

	/** 根据id集合查询 **/
	@RequestMapping(value = "/cartest/selectByIdList")
	@ResponseBody
	public List<CarTestExtend> selectByIdList(@RequestBody CarTestExtend carTestExtend) {
		return carTestService.lambdaQuery().in(CarTestExtend::getId, carTestExtend.getIdList()).list();
	}

}
