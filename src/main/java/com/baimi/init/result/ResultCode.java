package com.baimi.init.result;

public enum ResultCode {
    SUCCESS(200, "成功"),

    ERROR(400, "失败");

    private final Integer code;
    private final String description;

    ResultCode(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
