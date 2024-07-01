package com.baimi.init.common.enums;

import lombok.Getter;

/**
 * @author zhang
 * @since 2024/7/1 下午5:01
 */
@Getter
public enum OrderStatus {
    UNPAID(1, "待支付"),
    COMPLETED(2,"已完成"),
    CANCELED(3,"已取消"),
    CLOSED(4,"已关闭");

    private final int value;
    private final String description;

    OrderStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }
}
