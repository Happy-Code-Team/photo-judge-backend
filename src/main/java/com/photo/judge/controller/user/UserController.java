package com.photo.judge.controller.user;

import com.photo.judge.common.response.Response;
import com.photo.judge.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户controller
 */
@Slf4j
@RestController
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	/** 判断用户编码是否存在 **/
	@GetMapping("/user/checkUserCode")
	public Response checkUsername(@RequestParam("userCode")  String userCode) {
		if (userService.checkUserCodeExist(userCode)) {
			return Response.fail(400, "用户名已存在");
		}
		return Response.success("用户名可用");
	}

	/** 测试 **/
	@GetMapping("/user/test")
	public Response test(@RequestParam("id")  String id) {
		userService.test(id);
		return Response.success("");
	}
}
