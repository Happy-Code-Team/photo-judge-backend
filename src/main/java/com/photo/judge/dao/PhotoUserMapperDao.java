package com.photo.judge.dao;

import com.photo.judge.entity.user.PhotoUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PhotoUserMapperDao {
	PhotoUser findUserById(String userId);

	PhotoUser findUserByUserName(String userName);
}
