package cn.lmsite.imghub.common.enums;

import lombok.Getter;

public enum SystemCodeEnum {

    SUCCESS(2000, "SUCCESS"),

    ERROR_USER_NOT_EXIST(4001008, "用户不存在"),

    ERROR_TOKEN_TIMEOUT(4001010, "token失效"),

    ERROR_TOKEN(4001011, "token不正确"),

    SYSTEM_ERROR(0, "错误");

    @Getter
    private final int    code;
    @Getter
    private final String content;

    SystemCodeEnum(int code, String content) {
        this.code = code;
        this.content = content;
    }

    public static SystemCodeEnum findByCode(int code) {
        for (SystemCodeEnum sCode : SystemCodeEnum.values()) {
            if (sCode.getCode() == code) {
                return sCode;
            }
        }
        return null;
    }
}
