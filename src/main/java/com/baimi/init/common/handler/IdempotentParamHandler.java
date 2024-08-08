package com.baimi.init.common.handler;

import com.baimi.init.common.context.IdempotentContext;
import com.baimi.init.common.exception.ClientException;
import com.baimi.init.common.idempotent.IdempotentParam;
import com.baimi.init.common.idempotent.IdempotentParamService;
import com.baimi.init.common.idempotent.IdempotentTemplate;
import com.baimi.init.common.utils.Md5Util;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

/**
 * @author zhang
 * @description
 * @since 2024/8/8 上午11:21
 */
@Service
@RequiredArgsConstructor
public final class IdempotentParamHandler extends IdempotentTemplate implements IdempotentParamService {

    private final RedissonClient redissonClient;

    private final static String LOCK = "lock:param:restAPI";

    @Override
    protected IdempotentParam buildWrapper(JoinPoint joinPoint) {
        String lockKey = String.format("idempotent:path:%s:currentUserId:%s:md5:%s", getServletPath(), getCurrentUserId(), calcArgsMD5(joinPoint));
        return IdempotentParam.builder().lockKey(lockKey).build();
    }

    /**
     * @return 获取当前线程上下文 ServletPath
     */
    private String getServletPath() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return sra.getRequest().getServletPath();
    }

    /**
     * @return 当前操作用户 ID
     */
    private String getCurrentUserId() {
        return null;
    }

    /**
     * @return joinPoint md5
     */
    private String calcArgsMD5(JoinPoint joinPoint) {
        return Md5Util.getCalcArgsMD5(Arrays.toString(joinPoint.getArgs()));
    }

    @Override
    public void handler(IdempotentParam wrapper) {
        String lockKey = wrapper.getLockKey();
        RLock lock = redissonClient.getLock(lockKey);
        if (!lock.tryLock()) {
            throw new ClientException(wrapper.getIdempotent().message());
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

