package com.photo.judge.service.user;

import com.photo.judge.dao.user.UserDao;
import com.photo.judge.model.entity.user.UserExtend;
import com.photo.judge.service.myservice.MyServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户service实现类
 */
@Service
public class UserServiceImpl extends MyServiceImpl<UserDao, UserExtend> implements UserService {

	//根据用户编码查询
	@Override
	public UserExtend selectByUserCode(String userCode) {
		return this.lambdaQuery().eq(UserExtend::getUserCode, userCode).one();
	}

	//判断用户编码是否存在
	@Override
	public Boolean checkUserCodeExist(String userCode) {
		return this.lambdaQuery().eq(UserExtend::getUserCode, userCode).exists();
	}

}
