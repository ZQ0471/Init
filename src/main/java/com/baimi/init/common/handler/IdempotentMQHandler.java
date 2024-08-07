package com.baimi.init.common.handler;

import com.baimi.init.common.idempotent.IdempotentParam;
import com.baimi.init.common.annotation.Idempotent;
import com.baimi.init.common.aspect.IdempotentAspect;
import com.baimi.init.common.idempotent.IdempotentSpELService;
import com.baimi.init.common.idempotent.IdempotentTemplate;
import com.baimi.init.common.context.IdempotentContext;
import com.baimi.init.common.enums.MQStatusEnum;
import com.baimi.init.common.exception.RepeatConsumptionException;
import com.baimi.init.common.utils.LogUtil;
import com.baimi.init.common.utils.RedisUtil;
import com.baimi.init.common.utils.SpELUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.concurrent.TimeUnit;

/**
 * @author zhang
 * @description
 * @since 2024/8/7 下午4:09
 */
@RequiredArgsConstructor
public final class IdempotentMQHandler extends IdempotentTemplate implements IdempotentSpELService {



    private final RedisUtil redisUtil;

    private final static int TIMEOUT = 600;
    private final static String WRAPPER = "wrapper:spEL:MQ";

    @SneakyThrows
    @Override
    protected IdempotentParam buildWrapper(ProceedingJoinPoint joinPoint) {
        Idempotent idempotent = IdempotentAspect.getIdempotent(joinPoint);
        String key = (String) SpELUtil.parseKey(idempotent.key(), ((MethodSignature) joinPoint.getSignature()).getMethod(), joinPoint.getArgs());
        return IdempotentParam.builder().lockKey(key).build();
    }

    @Override
    public void handler(IdempotentParam wrapper) {
        String uniqueKey = wrapper.getIdempotent().uniqueKeyPrefix() + wrapper.getLockKey();
        Boolean setIfAbsent = redisUtil.setIfAbsent(uniqueKey, MQStatusEnum.CONSUMING.getCode(), TIMEOUT, TimeUnit.SECONDS);
        if (setIfAbsent != null && !setIfAbsent) {
            String consumeStatus = (String) redisUtil.get(uniqueKey);
            boolean error = MQStatusEnum.isError(consumeStatus);
            LogUtil.getLog(wrapper.getJoinPoint()).warn("[{}] MQ repeated consumption, {}.", uniqueKey, error ? "Wait for the client to delay consumption" : "");
            throw new RepeatConsumptionException(error);
        }
        IdempotentContext.put(WRAPPER, wrapper);
    }

    @Override
    public void exceptionProcessing() {
        IdempotentParam wrapper = (IdempotentParam) IdempotentContext.getKey(WRAPPER);
        if (wrapper != null) {
            Idempotent idempotent = wrapper.getIdempotent();
            String uniqueKey = idempotent.uniqueKeyPrefix() + wrapper.getLockKey();
            try {
                redisUtil.del(uniqueKey);
            } catch (Throwable ex) {
                LogUtil.getLog(wrapper.getJoinPoint()).error("[{}] Failed to del MQ anti-heavy token.", uniqueKey);
            }
        }
    }

    @Override
    public void postProcessing() {
        IdempotentParam wrapper = (IdempotentParam) IdempotentContext.getKey(WRAPPER);
        if (wrapper != null) {
            Idempotent idempotent = wrapper.getIdempotent();
            String uniqueKey = idempotent.uniqueKeyPrefix() + wrapper.getLockKey();
            try {
                redisUtil.put(uniqueKey, MQStatusEnum.CONSUMED.getCode(), idempotent.keyTimeout(), TimeUnit.SECONDS);
            } catch (Throwable ex) {
                LogUtil.getLog(wrapper.getJoinPoint()).error("[{}] Failed to set MQ anti-heavy token.", uniqueKey);
            }
        }
    }
}
