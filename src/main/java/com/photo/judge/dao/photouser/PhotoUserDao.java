package com.photo.judge.dao.photouser;

import com.github.yulichang.base.MPJBaseMapper;
import com.photo.judge.model.entity.photouser.PhotoUser;
import com.photo.judge.model.entity.photouser.PhotoUserExtend;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PhotoUserDao extends MPJBaseMapper<PhotoUserExtend> {
	PhotoUser findUserById(String userId);

	PhotoUser findUserByUserName(String userName);

	List<String> selectUserNames();
}
