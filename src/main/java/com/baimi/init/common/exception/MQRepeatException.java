package com.baimi.init.common.exception;

/**
 * @author zhang
 * @description
 * @since 2024/8/8 上午11:12
 */
public class MQRepeatException extends RuntimeException{
    public MQRepeatException(String msg) {
        super(msg);
    }
}
