package com.baimi.init.common.handler;

import com.baimi.init.common.annotation.Idempotent;
import com.baimi.init.common.aspect.IdempotentAspect;
import com.baimi.init.common.context.IdempotentContext;
import com.baimi.init.common.idempotent.IdempotentParam;
import com.baimi.init.common.idempotent.IdempotentSpELService;
import com.baimi.init.common.idempotent.IdempotentTemplate;
import com.baimi.init.common.utils.SpELUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

/**
 * @author zhang
 * @description
 * @since 2024/8/7 下午3:32
 */
@Service
@RequiredArgsConstructor
public final class IdempotentSpELHandler extends IdempotentTemplate implements IdempotentSpELService {

    private final RedissonClient redissonClient;

    private final static String LOCK = "lock:spEL:restAPI";

    @SneakyThrows
    @Override
    protected IdempotentParam buildWrapper(JoinPoint joinPoint) {
        Idempotent idempotent = IdempotentAspect.getIdempotent(joinPoint);
        String key = (String) SpELUtil.parseKey(idempotent.key(), ((MethodSignature) joinPoint.getSignature()).getMethod(), joinPoint.getArgs());
        return IdempotentParam.builder().lockKey(key).build();
    }

    @Override
    public void handler(IdempotentParam wrapper) {
        String lockKey = wrapper.getLockKey();
        RLock lock = redissonClient.getLock(lockKey);
        if (!lock.tryLock()) {
            return;
        }
        IdempotentContext.put(LOCK, lock);
    }

    @Override
    public void postProcessing() {
        RLock lock = null;
        try {
            lock = (RLock) IdempotentContext.getKey(LOCK);
        } finally {
            if (lock != null) {
                lock.unlock();
            }
        }
    }
}
