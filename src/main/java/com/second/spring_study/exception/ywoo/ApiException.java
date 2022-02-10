package com.second.spring_study.exception.ywoo;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{
    private final ErrorCodeEnum errorCodeEnum;

    public ApiException(ErrorCodeEnum e) {
        super(e.getMessage());
        this.errorCodeEnum=e;
    }
}
