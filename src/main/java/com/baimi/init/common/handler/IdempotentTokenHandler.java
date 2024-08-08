package com.baimi.init.common.handler;

import com.baimi.init.common.UserState;
import com.baimi.init.common.exception.IdempotentException;
import com.baimi.init.common.idempotent.IdempotentParam;
import com.baimi.init.common.idempotent.IdempotentTemplate;
import com.baimi.init.common.idempotent.IdempotentTokenService;
import com.baimi.init.common.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author zhang
 * @description
 * @since 2024/8/8 上午10:42
 */
@Service
@RequiredArgsConstructor
public final class IdempotentTokenHandler extends IdempotentTemplate implements IdempotentTokenService {

    @Resource
    private final RedisUtil redisUtil;
    @Resource
    private UserState userState;

    private static final String TOKEN_VALUE = "idempotent-token-value";
    private static final String TOKEN_PREFIX_KEY = "idempotent:token:";
    private static final int TOKEN_EXPIRED_TIME = 180;

    @Override
    protected IdempotentParam buildWrapper(JoinPoint joinPoint) {
        return new IdempotentParam();
    }

    @Override
    public void handler(IdempotentParam wrapper) {
        String token = userState.getCurrentToken();
        Boolean tokenFlag = redisUtil.setIfAbsent(TOKEN_PREFIX_KEY+token, TOKEN_VALUE,TOKEN_EXPIRED_TIME, TimeUnit.SECONDS);
        if (!tokenFlag) {
            String errMsg = wrapper.getIdempotent().message();
            throw new IdempotentException(errMsg);
        }
    }
}
