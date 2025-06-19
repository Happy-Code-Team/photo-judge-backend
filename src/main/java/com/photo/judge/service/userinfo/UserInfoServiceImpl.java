package com.photo.judge.service.userinfo;

import com.photo.judge.dao.userinfo.UserInfoDao;
import com.photo.judge.model.entity.userinfo.UserInfoExtend;
import com.photo.judge.service.myservice.MyServiceImpl;
import org.springframework.stereotype.Service;
/**
 * 用户信息表service实现类
 */
@Service
public class UserInfoServiceImpl extends MyServiceImpl<UserInfoDao, UserInfoExtend> implements UserInfoService {

}