package com.baimi.init.common.aspect;

import com.baimi.init.common.annotation.Idempotent;
import com.baimi.init.common.handler.IdempotentHandler;
import com.baimi.init.common.idempotent.IdempotentHandlerFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author zhang
 * @description
 * @since 2024/8/7 下午3:23
 */
@Aspect
@Component
public final class IdempotentAspect {

    /**
     * 增强方法标记 {@link Idempotent} 注解逻辑
     */
    @Pointcut("@annotation(com.baimi.init.common.annotation.Idempotent)")
    private void LogPointCut() {}
    @Before(value = "LogPointCut()")
    public void idempotentHandler(JoinPoint joinPoint) throws Throwable {
        Idempotent idempotent = getIdempotent(joinPoint);
        IdempotentHandler instance = IdempotentHandlerFactory.getInstance(idempotent.scene(), idempotent.type());
        instance.execute(joinPoint, idempotent);
    }

    public static Idempotent getIdempotent(JoinPoint joinPoint) throws NoSuchMethodException {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        return method.getAnnotation(Idempotent.class);
    }
}

