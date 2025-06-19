package com.photo.judge.service.user;

import com.photo.judge.dao.user.UserDao;
import com.photo.judge.model.entity.user.UserExtend;
import com.photo.judge.service.myservice.MyServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 用户表service实现类
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

	//测试
	@Override
	public void test(String id){
		this.baseMapper.insertXml((UserExtend) new UserExtend().setId("a").setUserCode("a").setUserName("a").setPassword("a").setEmail("a"));

		this.baseMapper.insertBatchXml(Arrays.asList(
				(UserExtend) new UserExtend().setId("b").setUserCode("b").setUserName("b").setPassword("b").setEmail("b"),
				(UserExtend) new UserExtend().setId("c").setUserCode("c").setUserName("c").setPassword("c").setEmail("c"),
				(UserExtend) new UserExtend().setId("d").setUserCode("d").setUserName("d").setPassword("d").setEmail("d"),
				(UserExtend) new UserExtend().setId("e").setUserCode("e").setUserName("e").setPassword("e").setEmail("e")
		));

		this.baseMapper.delByIdXml("a");

		this.baseMapper.delByIdListXml(Arrays.asList("b","c"));

		this.baseMapper.delXml((UserExtend) new UserExtend().setId("d"));

		this.baseMapper.updateByIdXml((UserExtend) new UserExtend().setId("e").setUserCode("eeeee").setUserName("eeeee").setPassword("eeeee").setEmail("eeeee"));

		UserExtend userExtend = this.baseMapper.selectByIdXml(id);

		List<UserExtend> userExtendList = this.baseMapper.selectXml((UserExtend) new UserExtend().setId(id));

		List<UserExtend> userExtendList2 = this.baseMapper.selectXml((UserExtend) new UserExtend().setIdList(Arrays.asList("1","2")));

	}
}
