package com.baimi.init.common.rate;

public interface RateLimit {
    boolean tryAquire();
}
