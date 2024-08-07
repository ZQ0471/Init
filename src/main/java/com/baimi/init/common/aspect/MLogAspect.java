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
        Integer userId = userState.getUserId();
        operationMapper.insert(new Operation(userId, operationType, remark));
    }
    /*@Around("LogPointCut()")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MLog myLog = method.getAnnotation(MLog.class);
        String remark = myLog.remark();
        String operationType = myLog.operationType().toString();

        // 获取参数
        Object[] args = joinPoint.getArgs();
        String params = Arrays.toString(args);

        // 执行方法并获取结果
        Object result = joinPoint.proceed(); // 必须调用proceed()来执行原方法

        // 记录日志
        Integer userId = userState.getUserId();

        log.error("userId:{}, operationType:{}, remark:{}, params:{}, result:{}", userId, operationType, remark, params, result);
        //operationMapper.insert(new Operation(userId, operationType, remark, params, result));

        // 返回方法执行结果
        return result;
    }*/
}
