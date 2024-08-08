package com.baimi.init.common.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.baimi.init.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
    public Result handleBadRequestException(BadRequestException e) {
        log.error(e.getClass().getName(), e);
        return Result.error().message(e.getMessage());
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(NotLoginException.class)
    public Result handleNotLoginException(NotLoginException e) {
        log.error(e.getClass().getName(), e);
        return Result.error().message("用户未登录");
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(NotPermissionException.class)
    public Result handleNotPermissionException(NotPermissionException e) {
        log.error(e.getClass().getName(), e);
        return Result.error().message("对不起，您无此权限:"+e.getPermission());
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(NotRoleException.class)
    public Result handleNotRoleException(NotRoleException e) {
        log.error(e.getClass().getName(), e);
        return Result.error().message("对不起，需要角色:"+e.getRole());
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(ClientException.class)
    public Result handleClientException(ClientException e) {
        log.error(e.getClass().getName(), e);
        return Result.error().message("系统错误:"+e.getMessage());
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(IdempotentException.class)
    public Result handleIdempotentException(IdempotentException e) {
        log.error(e.getClass().getName(), e);
        return Result.error().message(e.getMessage());
    }
}
