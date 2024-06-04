package com.baimi.init.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import com.baimi.init.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {
    @ExceptionHandler(BadRequestException.class)
    public Result handleBadRequestException(Exception e) {
        log.error(e.getClass().getName(), e);
        return Result.error().message(e.getMessage());
    }

    @ExceptionHandler(NotLoginException.class)
    public Result handleNotLoginException(Exception e) {
        log.error(e.getClass().getName(), e);
        return Result.error().message("用户未登录");
    }
    @ExceptionHandler(NotPermissionException.class)
    public Result handleNotPermissionException(Exception e) {
        log.error(e.getClass().getName(), e);
        return Result.error().message("对不起，您无此权限");
    }

}
