package com.photo.judge.controller.photouser;

import com.photo.judge.common.response.Response;
import com.photo.judge.service.photouser.PhotoUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PhotoUserController {
	private final PhotoUserService userService;

	public PhotoUserController(PhotoUserService userService) {
		this.userService = userService;
	}

	@GetMapping("/user/checkUsername")
	public Response checkUsername(@RequestParam("username")  String username) {
		if (userService.checkUserNameExist(username)) {
			return Response.fail(null, 400, "用户名已存在");
		}
		return Response.success("用户名可用");
	}
}
