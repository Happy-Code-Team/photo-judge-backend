package com.photo.judge.controller;

import com.photo.judge.entity.dto.login.LoginDto;
import com.photo.judge.entity.response.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class LoginController {

	@PostMapping("/login")
	public Response login(@RequestBody LoginDto loginDto) {
		if (Objects.equals(loginDto.getUserName(), "seaflower")) {
			return Response.success("Login successful");
		}
		return Response.fail("Invalid username or password", 401, "Unauthorized");
	}
}
