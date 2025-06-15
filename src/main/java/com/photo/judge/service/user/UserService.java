package com.photo.judge.service.user;

import com.photo.judge.api.user.IUserService;
import com.photo.judge.dao.PhotoUserMapperDao;
import com.photo.judge.entity.user.PhotoUser;
import org.springframework.stereotype.Service;

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
}
