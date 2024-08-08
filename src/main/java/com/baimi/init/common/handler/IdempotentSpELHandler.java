package com.baimi.init.common.handler;

import com.baimi.init.common.annotation.Idempotent;
import com.baimi.init.common.aspect.IdempotentAspect;
import com.baimi.init.common.exception.IdempotentException;
import com.baimi.init.common.idempotent.IdempotentParam;
import com.baimi.init.common.idempotent.IdempotentSpELService;
import com.baimi.init.common.idempotent.IdempotentTemplate;
import com.baimi.init.common.utils.RedisUtil;
import com.baimi.init.common.utils.SpELUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author zhang
 * @description
 * @since 2024/8/7 下午3:32
 */
@Service
@RequiredArgsConstructor
public final class IdempotentSpELHandler extends IdempotentTemplate implements IdempotentSpELService {

    @Resource
    private RedisUtil redisUtil;

    private final static int TIMEOUT = 180;

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
        Boolean lock = redisUtil.setIfAbsent(lockKey, "0", TIMEOUT, TimeUnit.SECONDS);
        if (!lock) {
            throw new IdempotentException(wrapper.getIdempotent().message());
        }
    }
}
