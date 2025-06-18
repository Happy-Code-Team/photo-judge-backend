package com.photo.judge.common.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
	private Integer code;
	private String message;
	private Object data;

	public static Response success(Object data) {
		return Response.builder().code(200).message("Success").data(data).build();
	}

	public static Response fail(Integer code, String message) {
		return Response.builder().code(code).message(message).build();
	}

	public static Response fail(Integer code, String message, Object data) {
		return Response.builder().code(code).message(message).data(data).build();
	}
}
