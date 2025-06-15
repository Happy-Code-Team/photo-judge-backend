package com.photo.judge.api.user;

import com.photo.judge.entity.user.PhotoUser;

public interface IUserService {
	PhotoUser findUserByUserName(String userName);
}
