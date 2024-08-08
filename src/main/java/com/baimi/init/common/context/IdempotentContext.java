package com.baimi.init.common.context;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhang
 * @description
 * @since 2024/8/7 下午3:27
 */
public final class IdempotentContext {

    private static final ThreadLocal<Map<String, Object>> CONTEXT = new ThreadLocal<>();

    public static Map<String, Object> get() {
        return CONTEXT.get();
    }

    public static Object getKey(String key) {
        Map<String, Object> context = get();
        if (!context.isEmpty()) {
            return context.get(key);
        }
        return null;
    }

    public static String getString(String key) {
        Object actual = getKey(key);
        if (actual != null) {
            return actual.toString();
        }
        return null;
    }

    public static void put(String key, Object val) {
        Map<String, Object> context = get();
        if (context.isEmpty()) {
            context = new HashMap<>();
        }
        context.put(key, val);
        putContext(context);
    }

    public static void putContext(Map<String, Object> context) {
        Map<String, Object> threadContext = CONTEXT.get();
        if (threadContext.isEmpty()) {
            threadContext.putAll(context);
            return;
        }
        CONTEXT.set(context);
    }

    public static void clean() {
        CONTEXT.remove();
    }
}
