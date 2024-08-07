package com.baimi.init.common.idempotent;

import com.baimi.init.common.annotation.Idempotent;
import com.baimi.init.common.enums.TypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author zhang
 * @description
 * @since 2024/8/7 下午3:21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public final class IdempotentParam {

    /**
     * 幂等注解
     */
    private Idempotent idempotent;

    /**
     * AOP 处理连接点
     */
    private ProceedingJoinPoint joinPoint;

    /**
     * 锁标识，{@link TypeEnum#PARAM}
     */
    private String lockKey;
}
