package com.photo.judge.dao.userinfo;

import com.photo.judge.dao.mydao.MyDao;
import com.photo.judge.model.entity.userinfo.UserInfoExtend;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息表Dao
 */
@Mapper
public interface UserInfoDao extends MyDao<UserInfoExtend> {

}