package org.stop.eop.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.stop.eop.entity.resp.Result;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
@SuppressWarnings("rawtypes")
public class ErrorHandler {


    @ExceptionHandler(RuntimeException.class)
    public Result handleRuntimeException(RuntimeException e) {
        String message = e.getMessage();
        return Result.error(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return Result.error(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(DataAccessException.class)
    public Result handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        return Result.error("重复 请检查后再添加");
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception exception) {
        return Result.error("服务器未知错误 请联系管理员");
    }


}
