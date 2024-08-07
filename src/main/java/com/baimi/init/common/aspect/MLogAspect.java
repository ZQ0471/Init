package com.baimi.init.common.aspect;

import com.baimi.init.common.UserState;
import com.baimi.init.common.annotation.MLog;
import com.baimi.init.entity.Operation;
import com.baimi.init.mapper.OperationMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class MLogAspect {
    @Resource
    private OperationMapper operationMapper;
    @Resource
    private UserState userState;
    @Pointcut("@annotation(com.baimi.init.common.annotation.MLog)")
    private void LogPointCut() {}
    @After(value = "LogPointCut()")
    public void logOperation(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        MLog log = method.getAnnotation(MLog.class);

        String remark = log.remark();

        String operationType = log.operationType().toString();

        String methodName = method.getName();

        // 获取参数
        String params = Arrays.toString(joinPoint.getArgs());

        // 记录日志
        String username = userState.getUserInfo().getUsername();

        String path = userState.getPath();

        operationMapper.insert(new Operation(username, operationType, remark,path,methodName, params));
    }

}
