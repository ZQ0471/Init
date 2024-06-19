package com.baimi.init.common;

import com.baimi.init.exception.BadRequestException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

public class Asserts {
    /**
     * 断言结果正确
     *
     * @param expect  期盼的结果
     * @param message 如果期盼结果不满足时，返回的错误信息
     */
    public static void isTrue(boolean expect, String message) {
        if (!expect) {
            throw new BadRequestException(message);
        }
    }

    /**
     * Date 上午9:25 2024/6/19
     * Param [object, message]
     **/

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new BadRequestException(message);
        }
    }

    public static void hasText(String text, String message) {
        if (!StringUtils.hasText(text)) {
            throw new BadRequestException(message);
        }
    }

    public static void hasSize(Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BadRequestException(message);
        }
    }
}
