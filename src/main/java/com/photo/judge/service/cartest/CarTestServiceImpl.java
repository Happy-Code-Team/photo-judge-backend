package com.photo.judge.service.cartest;

import com.photo.judge.dao.cartest.CarTestDao;
import com.photo.judge.model.entity.cartest.CarTestExtend;
import com.photo.judge.service.myservice.MyServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用来测试的，后续会删掉serviceImpl
 */
@Service
public class CarTestServiceImpl extends MyServiceImpl<CarTestDao, CarTestExtend> implements CarTestService {

}
