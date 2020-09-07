package cn.lmsite.imghub.common.enums;

import lombok.Getter;

public enum SystemCodeEnum {

    SUCC(2000, "SUCC"),

    ERROR_USER_NOT_EXIST(4001008, "用户不存在"),

    ERROR_TOKEN_TIMEOUT(4001010, "token失效"),

    ERROR_TOKEN(4001011, "token不正确"),

    COMMON(0, "错误");

    @Getter
    private final int    code;
    @Getter
    private final String conent;

    SystemCodeEnum(int code, String content) {
        this.code = code;
        this.conent = content;
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
