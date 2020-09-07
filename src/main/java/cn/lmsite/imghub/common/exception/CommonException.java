package cn.lmsite.imghub.common.exception;

import cn.lmsite.imghub.common.enums.SystemCodeEnum;
import lombok.Getter;

public class CommonException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    @Getter
    private final int            code;
    @Getter
    private final SystemCodeEnum systemCode;

    public CommonException(int code, String msg) {
        super(msg);
        this.code = code;
        this.systemCode = SystemCodeEnum.findByCode(code);
    }

    public CommonException(SystemCodeEnum systemCode) {
        super(systemCode.getConent());
        this.code = systemCode.getCode();
        this.systemCode = systemCode;
    }

    public CommonException(SystemCodeEnum systemCode, String msg) {
        super(msg);
        this.code = systemCode.getCode();
        this.systemCode = systemCode;
    }

    public CommonException(String msg) {
        this(SystemCodeEnum.COMMON.getCode(), msg);
    }

    public static CommonException getInstance(String msg, Object... args) {
        for (Object object : args) {
            msg = msg.replaceFirst("\\{\\}", object.toString());
        }
        return new CommonException(msg);
    }

    public static CommonException getInstance(String msg) {
        return new CommonException(msg);
    }
}
