package com.photo.judge.service.userinfo;

import com.photo.judge.common.model.myservice.MyService;
import com.photo.judge.common.response.Response;
import com.photo.judge.model.entity.userinfo.UserInfoExtend;

/**
 * 用户信息表service
 */
public interface UserInfoService extends MyService<UserInfoExtend> {
    // 查询当前 userCode 是否存在
    Boolean checkUserCodeIsExist(String userCode);

    // 用户注册
    Response userRegister(UserInfoExtend userInfoExtend);

    // 用户登录
    Response userLogin(UserInfoExtend userInfoExtend);

}