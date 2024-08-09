package com.baimi.init.common.aspect;

import com.baimi.init.common.annotation.Idempotent;
import com.baimi.init.common.handler.IdempotentHandler;
import com.baimi.init.common.idempotent.IdempotentHandlerFactory;
import com.baimi.init.common.idempotent.IdempotentParam;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
    @Around(value = "LogPointCut()")
    public Object idempotentHandler(ProceedingJoinPoint joinPoint) throws Throwable {
        Idempotent idempotent = getIdempotent(joinPoint);
        IdempotentHandler instance = IdempotentHandlerFactory.getInstance(idempotent.scene(), idempotent.type());
        IdempotentParam param = instance.execute(joinPoint, idempotent);
        Object result = joinPoint.proceed();
        instance.postProcess(param);
        return result;
    }

    public static Idempotent getIdempotent(ProceedingJoinPoint  joinPoint) throws NoSuchMethodException {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        return method.getAnnotation(Idempotent.class);
    }
}

