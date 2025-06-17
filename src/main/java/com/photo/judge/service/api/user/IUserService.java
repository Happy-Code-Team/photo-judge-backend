package com.photo.judge.service.api.user;

import com.photo.judge.model.entity.user.PhotoUser;

public interface IUserService {
	PhotoUser findUserByUserName(String userName);

	Boolean checkUserNameExist(String userName);
}
