package com.baimi.init.common.aspect;

import com.baimi.init.common.annotation.LogAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class LoginAspect {


    @Pointcut("@annotation(com.baimi.init.common.annotation.LogAnnotation)")
    private void LoginPointCut() {
    }
    @Around("LoginPointCut()")
    public Object LoginAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        if (logAnnotation != null) {
            System.out.println("方法： " + name + "  调用值：" + logAnnotation.value() + "  返回值： " + joinPoint.proceed());
        }
        return joinPoint.proceed();
    }


}
