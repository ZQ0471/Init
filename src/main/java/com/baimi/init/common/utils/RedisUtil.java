package com.baimi.init.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author zhang
 * DATE 2024/6/21 上午9:33
 */

@Slf4j
@Component
public final class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 取值
     * @param key 键
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     * @param key   键
     * @param value 值
     */
    public void set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
    public void setExpire(String key, Object value, long time) {
        try {
            redisTemplate.opsForValue().set(key, value);
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 指定缓存失效时间
     * @param key  键
     * @param time 时间(秒)
     */
    public void expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回-1代表为永久有效 返回-2代表查询的键不存在
     */
    public long getExpire(String key) {
        if (key != null) {
            return redisTemplate.getExpire(key, TimeUnit.SECONDS);
        } else {
            return 0; //不存在
        }
    }
    /*
     * 删除键
     */
    public void del(String key) {
        try {
            if (key != null) {
                redisTemplate.delete(key);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public Boolean setIfAbsent(String uniqueKey, String code, int timeout, TimeUnit timeUnit) {
        return redisTemplate.opsForValue().setIfAbsent(uniqueKey, code, timeout, timeUnit);
    }

    public void put(String uniqueKey, String code, long l, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(uniqueKey, code, l, timeUnit);
    }
}
