package com.photo.judge.service.myservice;

import com.github.yulichang.base.MPJBaseMapper;
import com.github.yulichang.base.MPJBaseServiceImpl;

/**
 * 公共service实现类
 */
public class MyServiceImpl<M extends MPJBaseMapper<T>, T> extends MPJBaseServiceImpl<M, T> implements MyService<T> {

}