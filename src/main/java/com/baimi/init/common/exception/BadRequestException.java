package com.baimi.init.common.exception;


/**
 * Created At 2020/11/2.
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String msg) {
        super(msg);
    }
}
