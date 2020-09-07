package cn.lmsite.imghub.common.result;

import java.io.Serializable;

import cn.lmsite.imghub.common.result.enums.CommonResultEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 基本结果类
 *
 * @author Jonny.Chang  ( https://jonnyhub.com )  @jonny6015
 */
@Getter
@Setter
public class BaseResult<T> implements Serializable {
    private static final long serialVersionUID = 501206838199376258L;

    /** 操作结果 默认false */
    private boolean success;
    /** 操作结果码 */
    private String  resultCode;
    /** 提示信息 */
    private String  resultMsg;
    /** 对象 */
    private T       data;

    /**
     * Constructor.
     */
    public BaseResult() {
        this.success = false;
    }

    /**
     * Constructor.
     *
     * @param success    成功
     * @param resultCode 结果代码
     * @param data       数据
     * @param resultMsg  结果味精
     */
    public BaseResult(boolean success, String resultCode, T data, String resultMsg) {
        this.success = success;
        this.resultCode = resultCode;
        this.data = data;
        this.resultMsg = resultMsg;
    }

    /**
     * Constructor.
     *
     * @param codeEnum the code enum
     */
    public BaseResult(CommonResultEnum codeEnum) {
        this.success = codeEnum == CommonResultEnum.SUCCESS;
        this.resultCode = codeEnum.getCode();
        this.resultMsg = codeEnum.getMsg();
    }

    /**
     * Constructor.
     *
     * @param codeEnum the code enum
     * @param data     the data
     */
    public BaseResult(CommonResultEnum codeEnum, T data) {
        this.data = data;
        this.success = codeEnum == CommonResultEnum.SUCCESS;
        this.resultCode = codeEnum.getCode();
        this.resultMsg = codeEnum.getMsg();
    }

}
