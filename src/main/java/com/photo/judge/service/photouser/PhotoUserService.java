package com.photo.judge.service.photouser;

import com.photo.judge.model.entity.photouser.PhotoUser;
import com.photo.judge.model.entity.photouser.PhotoUserExtend;
import com.photo.judge.service.myservice.MyService;

/**
 * 用户信息service
 */
public interface PhotoUserService extends MyService<PhotoUserExtend> {
	PhotoUser findUserByUserName(String userName);

	Boolean checkUserNameExist(String userName);
}
