package com.baimi.init.common.handler;

import com.baimi.init.common.UserState;
import com.baimi.init.common.context.ContextHolder;
import com.baimi.init.common.exception.IdempotentException;
import com.baimi.init.common.idempotent.IdempotentParam;
import com.baimi.init.common.idempotent.IdempotentParamService;
import com.baimi.init.common.idempotent.IdempotentTemplate;
import com.baimi.init.common.utils.Md5Util;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author zhang
 * @description
 * @since 2024/8/8 上午11:21
 */
@Service
@RequiredArgsConstructor
public final class IdempotentParamHandler extends IdempotentTemplate implements IdempotentParamService {

    @Resource
    private RedissonClient redissonClient;
    @Resource
    private UserState userState;


    @Override
    protected IdempotentParam buildWrapper(ProceedingJoinPoint joinPoint) {
        String lockKey = String.format("idempotent:path:%s:currentUserId:%s:md5:%s", userState.getPath(), userState.getUserInfo().getAccount(), calcArgsMD5(joinPoint));
        return IdempotentParam.builder().lockKey(lockKey).build();
    }

    /**
     * @return joinPoint md5
     */
    private String calcArgsMD5(ProceedingJoinPoint joinPoint) {
        return Md5Util.getCalcArgsMD5(Arrays.toString(joinPoint.getArgs()));
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

