package com.baimi.init.common.handler;

import com.baimi.init.common.annotation.Idempotent;
import com.baimi.init.common.aspect.IdempotentAspect;
import com.baimi.init.common.context.ContextHolder;
import com.baimi.init.common.exception.IdempotentException;
import com.baimi.init.common.idempotent.IdempotentParam;
import com.baimi.init.common.idempotent.IdempotentSpELService;
import com.baimi.init.common.idempotent.IdempotentTemplate;
import com.baimi.init.common.utils.SpELUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import javax.annotation.Resource;

/**
 * @author zhang
 * @description
 * @since 2024/8/7 下午4:09
 */
@Slf4j
@RequiredArgsConstructor
public final class IdempotentMQHandler extends IdempotentTemplate implements IdempotentSpELService {

    @Resource
    private RedissonClient redissonClient;


    @SneakyThrows
    @Override
    protected IdempotentParam buildWrapper(ProceedingJoinPoint joinPoint) {
        Idempotent idempotent = IdempotentAspect.getIdempotent(joinPoint);
        String key = (String) SpELUtil.parseKey(idempotent.key(), ((MethodSignature) joinPoint.getSignature()).getMethod(), joinPoint.getArgs());
        return IdempotentParam.builder().lockKey(key).build();
    }

    @Override
    public void handler(IdempotentParam param) {
        String lockKey = param.getIdempotent().uniqueKeyPrefix() + param.getLockKey();
        RLock lock = redissonClient.getLock(lockKey);
        if (!lock.tryLock()) {
            String errMsg = param.getIdempotent().message();
            throw new IdempotentException(errMsg);
        }
        ContextHolder.put(lockKey, lock);
    }

    @Override
    public void postProcess(IdempotentParam param) {
        RLock lock = null;
        String lockKey = param.getIdempotent().uniqueKeyPrefix() + param.getLockKey();
        try {
            lock = (RLock) ContextHolder.getKey(lockKey);
        } finally {
            if (lock != null) {
                lock.unlock();
            }
        }
    }
}
