package com.baimi.init.common.rate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhang
 * @description
 * @since 2024/8/13 下午5:50
 */
@Slf4j
public class TokenBucket implements HandlerInterceptor {
    // 令牌产生速率(单位ms)
    private static int RATE;
    // 桶容量
    private static int CAPACITY;
    // 当前桶容量
    private volatile static int curCapacity;
    private final AtomicInteger successCount = new AtomicInteger(0);
    private final AtomicInteger failCount = new AtomicInteger(0);
    private final AtomicInteger requestCount = new AtomicInteger(0);
    // 时间戳
    private volatile long timeStamp = System.currentTimeMillis();
    public TokenBucket(int rate, int capacity){
        RATE = rate;
        CAPACITY = capacity;
        curCapacity = CAPACITY;
    }
    @Override
    public synchronized boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        requestCount.incrementAndGet();
        // 如果当前桶有剩余，直接返回
        if(curCapacity > 0){
            curCapacity--;
            log.error("通过{}", successCount.incrementAndGet());
            return true;
        }
        // 如果桶无剩余
        long currentTime = System.currentTimeMillis();
        long diff = (currentTime - timeStamp)/1000;
        // 如果距离上一次的请求的时间大于RATE的时间
        if(diff >= RATE){
            //计算这段时间间隔中生成的令牌，如果>2,桶容量加上（计算的令牌-1）
            //如果==1，就不做操作（因为这一次操作要消耗一个令牌）
            if((diff) / RATE >= 2){
                curCapacity += (int) (diff) / RATE - 1;
            }
            //保持桶内令牌容量<=CAPACITY
            if(curCapacity > CAPACITY){
                curCapacity = CAPACITY;
            }
            //刷新时间戳为本次请求
            timeStamp = currentTime;
            log.error("接受{}", successCount.incrementAndGet());
            return true;
        }
        log.error("拒绝{}", failCount.incrementAndGet());
        return false;
    }

}