package com.photo.judge.common.exception;

import com.photo.judge.common.response.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CommonException.class)
    public Response handleCommonException(CommonException ex) {
        return Response.fail(500, ex.getMessage());
    }
} 