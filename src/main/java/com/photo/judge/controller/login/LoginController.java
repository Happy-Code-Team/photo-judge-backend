package com.photo.judge.controller.login;

import com.photo.judge.common.response.Response;
import com.photo.judge.model.dto.login.LoginDto;
import com.photo.judge.model.entity.user.UserExtend;
import com.photo.judge.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
public class LoginController {
	private final UserService userService;

	public LoginController(UserService userService) {
		this.userService = userService;
	}

	/** 登录 **/
	@PostMapping("/login")
	public Response login(@RequestBody LoginDto loginDto) {
		UserExtend userExtend = userService.selectByUserCode(loginDto.getUserCode());
		if (Objects.isNull(userExtend)) {
			return Response.fail(401, "无效的用户名或密码");
		}
		return Response.success(userExtend);
	}
}
