package com.baimi.init.common.aspect;

import cn.dev33.satoken.stp.StpUtil;
import com.baimi.init.common.annotation.Log;
import com.baimi.init.entity.Operation;
import com.baimi.init.mapper.OperationMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@Component
@Aspect
public class LogAspect {
    @Resource
    private OperationMapper operationMapper;
    @Pointcut("@annotation(com.baimi.init.common.annotation.Log)")
    private void LogPointCut() {}
    @After(value = "LogPointCut()")
    public void logOperation(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Log log = method.getAnnotation(Log.class);
        String remark = log.remark();
        String operationType = log.operationType().toString();
        Integer userId = (Integer) StpUtil.getSession().get("userId");
        operationMapper.insert(new Operation(userId, operationType, remark));
    }
}
