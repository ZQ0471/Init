package com.baimi.init.common.idempotent;

import com.baimi.init.common.annotation.Idempotent;
import com.baimi.init.common.handler.IdempotentHandler;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author zhang
 * @description
 * @since 2024/8/7 下午3:19
 */
public abstract class IdempotentTemplate implements IdempotentHandler {

    /**
     * 构建幂等验证过程中所需要的参数包装器
     *
     * @param joinPoint AOP 方法处理
     * @return 幂等参数包装器
     */
    protected abstract IdempotentParam buildWrapper(ProceedingJoinPoint joinPoint);

    /**
     * 执行幂等处理逻辑
     *
     * @param joinPoint  AOP 方法处理
     * @param idempotent 幂等注解
     */
    public void execute(ProceedingJoinPoint joinPoint, Idempotent idempotent) {
        // 模板方法模式：构建幂等参数包装器
        IdempotentParam idempotentParam = buildWrapper(joinPoint).setIdempotent(idempotent);
        handler(idempotentParam);
    }
}
