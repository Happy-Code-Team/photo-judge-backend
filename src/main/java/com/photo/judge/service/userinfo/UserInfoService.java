package com.photo.judge.service.userinfo;

import com.photo.judge.common.response.Response;
import com.photo.judge.model.entity.userinfo.UserInfoExtend;
import com.photo.judge.service.myservice.MyService;

/**
 * 用户信息表service
 */
public interface UserInfoService extends MyService<UserInfoExtend> {
	// 查询当前 userCode 是否存在
	Boolean checkUserCodeIsExist(String userCode);

	Response userRegister(UserInfoExtend userInfoExtend);

	Response userLogin(UserInfoExtend userInfoExtend);

}