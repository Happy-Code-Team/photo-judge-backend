package com.photo.judge.service.user;

import com.photo.judge.api.user.IUserService;
import com.photo.judge.dao.PhotoUserMapperDao;
import com.photo.judge.entity.user.PhotoUser;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UserService implements IUserService {
	private final PhotoUserMapperDao photoUserMapperDao;

	public UserService(PhotoUserMapperDao photoUserMapper) {
		this.photoUserMapperDao = photoUserMapper;
	}

	@Override
	public PhotoUser findUserByUserName(String userName) {
		return photoUserMapperDao.findUserByUserName(userName);
	}

	@Override
	public Boolean checkUserNameExist(String userName) {
		List<String> userNames = photoUserMapperDao.selectUserNames();
		HashSet<String> userNameSet = new HashSet<>(userNames);
		return userNameSet.contains(userName);
	}
}
