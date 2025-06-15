package com.photo.judge.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhotoUser {
	private String userId;

	private String userName;

	private String password;

	private String email;

}
