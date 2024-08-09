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
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhang
 * @description
 * @since 2024/8/7 下午3:32
 */
@Service
@RequiredArgsConstructor
public final class IdempotentSpELHandler extends IdempotentTemplate implements IdempotentSpELService {

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
        String lockKey = param.getLockKey();
        RLock lock = redissonClient.getLock(lockKey);
        if (!lock.tryLock()) {
            throw new IdempotentException(param.getIdempotent().message());
        }
        ContextHolder.put(lockKey, lock);
    }

    @Override
    public void postProcess(IdempotentParam param) {
        RLock lock = null;
        String lockKey = param.getLockKey();
        try {
            lock = (RLock) ContextHolder.getKey(lockKey);
        } finally {
            if (lock != null) {
                lock.unlock();
            }
        }
    }
}
