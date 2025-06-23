package com.photo.judge.service.userinfo;

import cn.hutool.core.date.DateUtil;
import com.photo.judge.common.model.myservice.MyServiceImpl;
import com.photo.judge.common.response.Response;
import com.photo.judge.dao.userinfo.UserInfoDao;
import com.photo.judge.model.entity.userinfo.UserInfoExtend;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 用户信息表service实现类
 */
@Service
public class UserInfoServiceImpl extends MyServiceImpl<UserInfoDao, UserInfoExtend> implements UserInfoService {

	@Override
	public Boolean checkUserCodeIsExist(String userCode) {
		return -this.lambdaQuery().eq(UserInfoExtend::getUserCode, userCode).count() != 0L;
	}

	@Override
	public Response userRegister(UserInfoExtend userInfoExtend) {
		userInfoExtend.setTs(DateUtil.now());
		this.save(userInfoExtend);
		return Response.success(userInfoExtend);
	}

	@Override
	public Response userLogin(UserInfoExtend userInfoExtend) {
		UserInfoExtend existingUser = this.lambdaQuery()
				.eq(UserInfoExtend::getUserCode, userInfoExtend.getUserCode())
				.one();
		if (Objects.isNull(existingUser)) {
			return Response.fail(500, "该用户没未注册，请先注册");
		}

		if (!Objects.equals(existingUser.getUserPassword(), userInfoExtend.getUserPassword())) {
			return Response.fail(500, "密码错误，请重新输入");
		}
		return Response.success(existingUser);
	}
}