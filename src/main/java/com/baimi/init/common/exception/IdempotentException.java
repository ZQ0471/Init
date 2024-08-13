package com.baimi.init.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author zhang
 * @description
 * @since 2024/8/7 下午3:27
 */
@Getter
@RequiredArgsConstructor
public class IdempotentException extends RuntimeException {


    public IdempotentException(final String message) {
        super(message);
    }
}
