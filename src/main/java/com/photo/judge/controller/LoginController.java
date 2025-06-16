package com.photo.judge.controller;

import com.photo.judge.api.user.IUserService;
import com.photo.judge.dto.login.LoginDto;
import com.photo.judge.entity.response.Response;
import com.photo.judge.entity.user.PhotoUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
public class LoginController {
	private final IUserService userService;

	public LoginController(IUserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	public Response login(@RequestBody LoginDto loginDto) {
		PhotoUser photoUser = userService.findUserByUserName(loginDto.getUserName());
		if (Objects.isNull(photoUser)) {
			return Response.fail("Invalid username or password", 401, "Unauthorized");
		}

		return Response.success(photoUser);
	}
}
