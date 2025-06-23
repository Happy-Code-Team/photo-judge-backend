package com.photo.judge.common.model.mydao;

import com.github.yulichang.base.MPJBaseMapper;

import java.util.List;

//公共Dao
public interface MyDao<T> extends MPJBaseMapper<T> {

    //新增
    void insertXml(T t);
    //批量新增
    void insertBatchXml(List<T> list);
    //根据id删除
    void delByIdXml(String id);
    //根据id集合删除
    void delByIdListXml(List<String> idList);
    //根据条件删除
    void delXml(T t);
    //根据id修改
    void updateByIdXml(T t);
    //根据id查询
    T selectByIdXml(String id);
    //根据条件查询
    List<T> selectXml(T t);

}
