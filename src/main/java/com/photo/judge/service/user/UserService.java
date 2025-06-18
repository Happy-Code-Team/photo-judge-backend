package com.photo.judge.service.user;

import com.photo.judge.model.entity.user.UserExtend;
import com.photo.judge.service.myservice.MyService;

/**
 * 用户service
 */
public interface UserService extends MyService<UserExtend> {

	//根据用户编码查询
	UserExtend selectByUserCode(String userCode);
	//判断用户编码是否存在
	Boolean checkUserCodeExist(String userCode);

}
