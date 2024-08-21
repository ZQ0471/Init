package com.baimi.init.common.handler;

import com.baimi.init.common.UserState;
import com.baimi.init.common.context.ContextHolder;
import com.baimi.init.common.exception.IdempotentException;
import com.baimi.init.common.idempotent.IdempotentParam;
import com.baimi.init.common.idempotent.IdempotentTemplate;
import com.baimi.init.common.idempotent.IdempotentTokenService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhang
 * @description
 * @since 2024/8/8 上午10:42
 */
@Service
@RequiredArgsConstructor
public final class IdempotentTokenHandler extends IdempotentTemplate implements IdempotentTokenService {

    @Resource
    private final RedissonClient redissonClient;
    @Resource
    private UserState userState;


    @Override
    protected IdempotentParam buildWrapper(ProceedingJoinPoint joinPoint) {
        String lockKey = String.format("idempotent:token:%s", userState.getCurrentToken());
        return IdempotentParam.builder().lockKey(lockKey).build();
    }

    @Override
    public void handler(IdempotentParam param) {
        String lockKey = param.getLockKey();
        RLock lock = redissonClient.getLock(lockKey);
        if (!lock.tryLock()) {
            String errMsg = param.getIdempotent().message();
            throw new IdempotentException(errMsg);
        }
        //todo:获取并修改信息
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
