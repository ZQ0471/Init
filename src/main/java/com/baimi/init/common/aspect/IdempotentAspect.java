package com.baimi.init.common.aspect;

import com.baimi.init.common.annotation.Idempotent;
import com.baimi.init.common.context.IdempotentContext;
import com.baimi.init.common.exception.RepeatConsumptionException;
import com.baimi.init.common.handler.IdempotentHandler;
import com.baimi.init.common.idempotent.IdempotentHandlerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author zhang
 * @description
 * @since 2024/8/7 下午3:23
 */
@Aspect
public final class IdempotentAspect {

    /**
     * 增强方法标记 {@link Idempotent} 注解逻辑
     */
    @Around("@annotation(com.baimi.init.common.annotation.Idempotent)")
    public Object idempotentHandler(ProceedingJoinPoint joinPoint) throws Throwable {
        Idempotent idempotent = getIdempotent(joinPoint);
        IdempotentHandler instance = IdempotentHandlerFactory.getInstance(idempotent.scene(), idempotent.type());
        try {
            instance.execute(joinPoint, idempotent);
            return joinPoint.proceed();
        } catch (RepeatConsumptionException ex) {
            if (!ex.getError()) {
                return null;
            }
            throw ex;
        } finally {
            instance.postProcessing();
            IdempotentContext.clean();
        }
    }

    public static Idempotent getIdempotent(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method targetMethod = joinPoint.getTarget().getClass().getDeclaredMethod(methodSignature.getName(), methodSignature.getMethod().getParameterTypes());
        return targetMethod.getAnnotation(Idempotent.class);
    }
}

