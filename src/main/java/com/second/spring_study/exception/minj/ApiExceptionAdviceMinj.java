package com.second.spring_study.exception.minj;

import com.second.spring_study.dto.response.minj.ErrorResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class ApiExceptionAdviceMinj {
    //    @ExceptionHandler({ApiException.class})
//    public ResponseEntity<ErrorResponse> exceptionHandler(final ApiException e){
//        log.error(e.getCause().getMessage());
//        return ResponseEntity
//                .status(e.getErrorCodeEnum().getStatus())
//                .body(ErrorResponse.builder()
//                        .status(e.getErrorCodeEnum().getStatus())
//                        .message(e.getErrorCodeEnum().getMessage())
//                        .build());
//    }
    @ExceptionHandler({ApiException.class})
    protected ResponseEntity<ErrorResponse> exceptionHandler(final ApiException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getErrorCodeEnum().getStatus(), e.getErrorCodeEnum().getMessage()), HttpStatus.valueOf(e.getErrorCodeEnum().getStatus()));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity<ErrorResponse> MethodArgumentNotValidExceptionHandler(final MethodArgumentNotValidException e) {
        log.error(e.getCause().getMessage());
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ErrorResponse> ExceptionHandler(final Exception e) {
        log.error(e.getCause().getMessage());
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    protected ResponseEntity<ErrorResponse> DataIntegrityViolationExceptionHandler(final DataIntegrityViolationException e) {
        log.error(e.getCause().getMessage());
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.CONFLICT.value(), e.getLocalizedMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    protected ResponseEntity<ErrorResponse> MethodArgumentTypeMismatchExceptionHandler(final MethodArgumentTypeMismatchException e) {
        log.error(e.getCause().getMessage());
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    protected ResponseEntity<ErrorResponse> HttpRequestMethodNotSupportedExceptionHandler(final HttpRequestMethodNotSupportedException e) {
        log.error(e.getCause().getMessage());
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), e.getLocalizedMessage()), HttpStatus.METHOD_NOT_ALLOWED);
    }

}
