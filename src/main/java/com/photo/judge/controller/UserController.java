package com.photo.judge.controller;

import com.photo.judge.api.user.IUserService;
import com.photo.judge.entity.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
	private final  IUserService userService;

	public UserController(IUserService userService) {
		this.userService = userService;
	}

	@GetMapping("/checkUsername")
	public Response checkUsername(@RequestParam("username")  String username) {
		if (userService.checkUserNameExist(username)) {
			return Response.fail(null, 400, "用户名已存在");
		}
		return Response.success("用户名可用");
	}
}
