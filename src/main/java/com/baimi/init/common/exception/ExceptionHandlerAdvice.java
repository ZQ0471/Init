package com.baimi.init.common.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.baimi.init.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {
    @ExceptionHandler(BadRequestException.class)
    public Result handleBadRequestException(BadRequestException e) {
        log.error(e.getClass().getName(), e);
        return Result.error().message(e.getMessage());
    }
    @ExceptionHandler(NotLoginException.class)
    public Result handleNotLoginException(NotLoginException e) {
        log.error(e.getClass().getName(), e);
        return Result.error().message("用户未登录");
    }
    @ExceptionHandler(NotPermissionException.class)
    public Result handleNotPermissionException(NotPermissionException e) {
        log.error(e.getClass().getName(), e);
        return Result.error().message("对不起，您无此权限:"+e.getPermission());
    }
    @ExceptionHandler(NotRoleException.class)
    public Result handleNotRoleException(NotRoleException e) {
        log.error(e.getClass().getName(), e);
        return Result.error().message("对不起，需要角色:"+e.getRole());
    }
}
