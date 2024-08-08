package com.baimi.init.common.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author zhang
 * @description
 * @since 2024/8/7 下午3:26
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if ( ApplicationContextHolder.CONTEXT == null) {
            ApplicationContextHolder.CONTEXT = applicationContext;
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

