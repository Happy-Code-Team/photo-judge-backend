package com.photo.judge.dao.user;

import com.photo.judge.dao.mydao.MyDao;
import com.photo.judge.model.entity.user.UserExtend;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表Dao
 */
@Mapper
public interface UserDao extends MyDao<UserExtend> {

}
