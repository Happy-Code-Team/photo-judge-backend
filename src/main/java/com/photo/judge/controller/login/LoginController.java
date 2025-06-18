package com.photo.judge.controller.login;

import com.photo.judge.service.photouser.PhotoUserService;
import com.photo.judge.model.dto.login.LoginDto;
import com.photo.judge.common.response.Response;
import com.photo.judge.model.entity.photouser.PhotoUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
public class LoginController {
	private final PhotoUserService userService;

	public LoginController(PhotoUserService userService) {
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
