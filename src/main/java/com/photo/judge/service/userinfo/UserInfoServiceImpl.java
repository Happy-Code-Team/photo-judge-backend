package com.photo.judge.service.userinfo;

import com.photo.judge.common.context.RequestContextHolder;
import com.photo.judge.common.model.myservice.MyServiceImpl;
import com.photo.judge.common.response.Response;
import com.photo.judge.dao.userinfo.UserInfoDao;
import com.photo.judge.model.entity.userinfo.UserInfoExtend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 用户信息表service实现类
 */
@Service
@Slf4j
public class UserInfoServiceImpl extends MyServiceImpl<UserInfoDao, UserInfoExtend> implements UserInfoService {

    //查询当前 userCode 是否存在
    @Override
    public Boolean checkUserCodeIsExist(String userCode) {
        log.info("请求 ID：{}, 检查用户编码是否存在: {}", RequestContextHolder.getContext().getRequestId(), userCode);
        return this.lambdaQuery().eq(UserInfoExtend::getUserCode, userCode).count() != 0L;
    }

    //用户注册
    @Override
    public Response userRegister(UserInfoExtend userInfoExtend) {
        if (this.checkUserCodeIsExist(userInfoExtend.getUserCode())) {
            return Response.fail(500, "用户编码已存在");
        }
        this.save(userInfoExtend);
        return Response.success(userInfoExtend);
    }

    //用户登录
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