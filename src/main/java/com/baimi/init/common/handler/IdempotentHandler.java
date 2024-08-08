package com.baimi.init.common.handler;

import com.baimi.init.common.annotation.Idempotent;
import com.baimi.init.common.idempotent.IdempotentParam;
import org.aspectj.lang.JoinPoint;

public interface IdempotentHandler {

    /**
     * 幂等处理逻辑
     *
     * @param wrapper 幂等参数包装器
     */
    void handler(IdempotentParam wrapper);

    /**
     * 执行幂等处理逻辑
     *
     * @param joinPoint  AOP 方法处理
     * @param idempotent 幂等注解
     */
    void execute(JoinPoint joinPoint, Idempotent idempotent);

}
