package com.photo.judge.dao;

import com.photo.judge.model.entity.user.PhotoUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PhotoUserMapperDao {
	PhotoUser findUserById(String userId);

	PhotoUser findUserByUserName(String userName);

	List<String> selectUserNames();
}
