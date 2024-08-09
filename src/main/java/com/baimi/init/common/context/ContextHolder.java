package com.baimi.init.common.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhang
 * @description
 * @since 2024/8/7 下午3:26
 */
@Component
public class ContextHolder implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;
    private static ThreadLocal<Map<String, Object>> LOCAL = new ThreadLocal<>();

    public static Map<String, Object> get() {
        return LOCAL.get();
    }
    public static Object getKey(String key) {
        Map<String, Object> context = get();
        if (context.isEmpty()) return null;
        return context.get(key);
    }
    public static void put(String key, Object val) {
        Map<String, Object> context = get();
        if (context==null) {
            context = new HashMap<>();
        }
        context.put(key, val);
        putContext(context);
    }
    public static void putContext(Map<String, Object> context) {
        Map<String, Object> threadContext = LOCAL.get();
        if (threadContext!=null) {
            threadContext.putAll(context);
            return;
        }
        LOCAL.set(context);
    }
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if ( ContextHolder.CONTEXT == null) {
            ContextHolder.CONTEXT = applicationContext;
        }
    }

    public static <T> T getBean(Class<T> clazz) {
        return CONTEXT.getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return CONTEXT.getBean(name, clazz);
    }


    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return CONTEXT.getBeansOfType(clazz);
    }

    public static <A extends Annotation> A findAnnotationOnBean(String beanName, Class<A> annotationType) {
        return CONTEXT.findAnnotationOnBean(beanName, annotationType);
    }


    public static ApplicationContext getInstance() {
        return CONTEXT;
    }
}

