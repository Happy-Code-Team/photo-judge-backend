package com.photo.judge.service.myservice;

import com.github.yulichang.base.MPJBaseMapper;
import com.github.yulichang.base.MPJBaseServiceImpl;

/**
 * MPJBaseServiceImpl 扩展
 */
public class MyServiceImpl<M extends MPJBaseMapper<T>, T> extends MPJBaseServiceImpl<M, T> implements MyService<T> {

}