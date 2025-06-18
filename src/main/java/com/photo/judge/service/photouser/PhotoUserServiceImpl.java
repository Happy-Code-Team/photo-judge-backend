package com.photo.judge.service.photouser;

import com.photo.judge.dao.photouser.PhotoUserDao;
import com.photo.judge.model.entity.photouser.PhotoUser;
import com.photo.judge.model.entity.photouser.PhotoUserExtend;
import com.photo.judge.service.myservice.MyServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class PhotoUserServiceImpl extends MyServiceImpl<PhotoUserDao, PhotoUserExtend> implements PhotoUserService {

	private final PhotoUserDao photoUserDao;

	public PhotoUserServiceImpl(PhotoUserDao photoUserDao) {
		this.photoUserDao = photoUserDao;
	}

	//根据用户名查询
	@Override
	public PhotoUser findUserByUserName(String userName) {
		return photoUserDao.findUserByUserName(userName);
	}

	//检查用户名是否存在
	@Override
	public Boolean checkUserNameExist(String userName) {
		List<String> userNames = photoUserDao.selectUserNames();
		HashSet<String> userNameSet = new HashSet<>(userNames);
		return userNameSet.contains(userName);
	}
}
