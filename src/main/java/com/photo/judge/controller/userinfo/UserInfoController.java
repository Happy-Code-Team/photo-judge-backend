package com.photo.judge.controller.userinfo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.photo.judge.common.annotation.Desc;
import com.photo.judge.common.model.page.PageResult;
import com.photo.judge.common.response.Response;
import com.photo.judge.model.entity.userinfo.UserInfoExtend;
import com.photo.judge.model.vo.userinfo.UserInfoVO;
import com.photo.judge.service.userinfo.UserInfoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/**
 * 用户信息表controller
 */
@Slf4j
@RestController
public class UserInfoController {

	private final UserInfoService userInfoService;

	public UserInfoController(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	@GetMapping("/user/checkUserCodeIsExist")
	@Desc("检查用户编码是否存在")
	public Response checkUserCodeIsExist(String userCode) {
		if (userInfoService.checkUserCodeIsExist(userCode)) {
			return Response.fail(500, "用户编码已存在");
		} else {
			return Response.success(null);
		}
	}

	@PostMapping("/user/register")
	@Desc("用户注册")
	public Response register(@RequestBody @Valid UserInfoExtend userInfoExtend) {
		return userInfoService.userRegister(userInfoExtend);
	}

	@PostMapping("/user/login")
	@Desc("用户登录")
	public Response login(@RequestBody UserInfoExtend userInfoExtend) {
		log.info("User login attempt: {}", userInfoExtend);
		return userInfoService.userLogin(userInfoExtend);
	}

    /** 分页查询 **/
    @PostMapping("/userinfo/selectPage")
    public Response selectPage(@RequestBody UserInfoExtend userInfoExtend) {
        Page<UserInfoExtend> page = userInfoService.lambdaQuery().eq(UserInfoExtend::getUserPassword, "123456").page(userInfoExtend.toPage());
        return Response.success(PageResult.fromClone(page, UserInfoVO.class));

    }
}