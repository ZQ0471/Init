package com.baimi.init.common.rate;

/**
 * @author zhang
 * @description
 * @since 2024/8/13 下午5:50
 */
public class TokenBucket implements RateLimit  {
    // 令牌产生速率(单位ms)
    private static int RATE;
    // 桶容量
    private static int CAPACITY;
    // 当前桶容量
    private volatile static int curCapacity;
    // 时间戳
    private volatile long timeStamp = System.currentTimeMillis();
    public TokenBucket(int rate, int capacity){
        RATE = rate;
        CAPACITY = capacity;
        curCapacity = CAPACITY;
    }

    @Override
    public synchronized boolean tryAquire() {
        // 如果当前桶有剩余，直接返回
        if(curCapacity > 0){
            curCapacity--;
            return true;
        }
        // 如果桶无剩余
        long currentTime = System.currentTimeMillis();
        // 如果距离上一次的请求的时间大于RATE的时间
        if(currentTime - timeStamp >= RATE){
            //计算这段时间间隔中生成的令牌，如果>2,桶容量加上（计算的令牌-1）
            //如果==1，就不做操作（因为这一次操作要消耗一个令牌）
            if((currentTime - timeStamp) / RATE >= 2){
                curCapacity += (int) (currentTime - timeStamp) / RATE - 1;
            }
            //保持桶内令牌容量<=CAPACITY
            if(curCapacity > CAPACITY){
                curCapacity = CAPACITY;
            }
            //刷新时间戳为本次请求
            timeStamp = currentTime;
            return true;
        }
        return false;
    }

}



