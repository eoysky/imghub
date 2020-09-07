package cn.lmsite.imghub.common.result;

import cn.lmsite.imghub.common.result.enums.ResultCodeEnum;
import org.springframework.util.StringUtils;

/**
 * 服务结果
 *
 * @author Jonny.Chang  ( https://jonnyhub.com )  @jonny6015
 */
public class ServiceResult<T> {

    /**
     * 处理结果
     */
    private T data;

    /**
     * 结果码
     */
    private ResultCodeEnum resultCode;

    /**
     * 结果码扩展描述
     */
    private String resultMsg;

    /**
     * 默认构造函数
     */
    public ServiceResult() {
    }

    /**
     * 构造函数. 默认构建成功数据
     *
     * @param data the data
     */
    public ServiceResult(T data) {
        this.data = data;
        this.resultCode = ResultCodeEnum.SUCCESS;
    }

    /**
     * 构造函数. 多用于构建失败结果
     *
     * @param resultCode the result code
     */
    public ServiceResult(ResultCodeEnum resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * 构造函数.
     *
     * @param data       the data
     * @param resultCode the result code
     */
    public ServiceResult(T data, ResultCodeEnum resultCode) {
        this.data = data;
        this.resultCode = resultCode;
    }

    public boolean isSuccess() {
        return ResultCodeEnum.SUCCESS.equals(this.resultCode);
    }

    /**
     * Gets get data.
     *
     * @return the get data
     */
    public T getData() {
        return data;
    }

    /**
     * Sets set data.
     *
     * @param data the data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Gets get result code.
     *
     * @return the get result code
     */
    public ResultCodeEnum getResultCode() {
        return resultCode;
    }

    /**
     * Sets set result code.
     *
     * @param resultCode the result code
     */
    public void setResultCode(ResultCodeEnum resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        if (null != this.resultCode && StringUtils.isEmpty(resultMsg)) {
            return this.resultCode.getMsg() + ":" + resultMsg;
        }
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

}