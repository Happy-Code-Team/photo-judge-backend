package com.photo.judge.dao.user;

import com.github.yulichang.base.MPJBaseMapper;
import com.photo.judge.model.entity.user.User;
import com.photo.judge.model.entity.user.UserExtend;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户Dao
 */
@Mapper
public interface UserDao extends MPJBaseMapper<UserExtend> {

	//根据主键id查询
	User findUserById(String id);
	//根据用户编码查询
	User findUserByUserCode(String userCode);
	//获取所有用户编码
	List<String> selectUserNames();

}
