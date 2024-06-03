package com.baimi.init.exception;

import com.baimi.init.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error(e.getClass().getName(), e);
        return Result.error().message(e.getMessage());
    }

}
