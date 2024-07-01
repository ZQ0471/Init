package com.baimi.init.common.annotation;

import com.baimi.init.common.enums.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
* 日志记录
* */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Log {
    /*
    * 操作名称*/
    String remark() default "";
    /*
    * 操作类型*/
    OperationType operationType() default OperationType.OTHER;

}
