package com.baimi.init.common.handler;

import com.baimi.init.common.UserState;
import com.baimi.init.common.exception.IdempotentException;
import com.baimi.init.common.idempotent.IdempotentParam;
import com.baimi.init.common.idempotent.IdempotentParamService;
import com.baimi.init.common.idempotent.IdempotentTemplate;
import com.baimi.init.common.utils.Md5Util;
import com.baimi.init.common.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author zhang
 * @description
 * @since 2024/8/8 上午11:21
 */
@Service
@RequiredArgsConstructor
public final class IdempotentParamHandler extends IdempotentTemplate implements IdempotentParamService {

    @Resource
    private RedisUtil redisUtil;
    @Resource
    private UserState userState;
    private final static int TIMEOUT = 180;


    @Override
    protected IdempotentParam buildWrapper(JoinPoint joinPoint) {
        String lockKey = String.format("idempotent:path:%s:currentUserId:%s:md5:%s", userState.getPath(), userState.getUserInfo().getUsername(), calcArgsMD5(joinPoint));
        return IdempotentParam.builder().lockKey(lockKey).build();
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
        Boolean lock = redisUtil.setIfAbsent(lockKey, "0", TIMEOUT, TimeUnit.SECONDS);
        if (!lock) {
            throw new IdempotentException(wrapper.getIdempotent().message());
        }
    }
}

