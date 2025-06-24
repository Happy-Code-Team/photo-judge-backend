package com.photo.judge.common.exception;

import com.photo.judge.common.response.Response;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 处理系统自定义异常 CommonException 异常
    @ExceptionHandler(CommonException.class)
    public Response handleCommonException(CommonException ex) {
        return Response.fail(500, ex.getMessage());
    }

    // 处理 jakarta 的校验异常, 比如实体上的 @NotNull, @Size 等注解校验失败时抛出的异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(FieldError::getDefaultMessage)
            .collect(Collectors.toList());
        // 这里返回自定义的 Response，code 可自定义
        return Response.fail(500, String.join("; ", errors));
    }
} 