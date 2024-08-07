package com.baimi.init.common.annotation;

import com.baimi.init.common.enums.SceneEnum;
import com.baimi.init.common.enums.TypeEnum;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Idempotent {

    /**
     * 幂等Key，只有在 {@link Idempotent#type()} 为 {@link TypeEnum#SPEL} 时生效
     */
    String key() default "";

    /**
     * 触发幂等失败逻辑时，返回的错误提示信息
     */
    String message() default "您操作太快，请稍后再试";

    /**
     * 验证幂等类型，支持多种幂等方式
     * RestAPI 建议使用 {@link TypeEnum#TOKEN} 或 {@link TypeEnum#PARAM}
     * 其它类型幂等验证，使用 {@link TypeEnum#SPEL}
     */
    TypeEnum type() default TypeEnum.PARAM;

    /**
     * 验证幂等场景，支持多种 {@link SceneEnum}
     */
    SceneEnum scene() default SceneEnum.RESTAPI;

    /**
     * 设置防重令牌 Key 前缀，MQ 幂等去重可选设置
     * {@link SceneEnum#MQ} and {@link TypeEnum#SPEL} 时生效
     */
    String uniqueKeyPrefix() default "";

    /**
     * 设置防重令牌 Key 过期时间，单位秒，默认 1 小时，MQ 幂等去重可选设置
     * {@link SceneEnum#MQ} and {@link TypeEnum#SPEL} 时生效
     */
    long keyTimeout() default 3600L;
}
