package cn.lmsite.imghub.controller;

import cn.lmsite.imghub.common.result.BaseResult;
import cn.lmsite.imghub.common.result.ServiceResult;
import cn.lmsite.imghub.common.result.enums.CommonResultEnum;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseApi<T> {

    private T data;

    /**
     * 默认构造函数
     */
    public BaseApi() {
    }

    /**
     * 构造函数. 默认构建成功数据
     *
     * @param data the data
     */
    public BaseApi(T data) {
        this.data = data;
    }

    public BaseResult<T> buildingBaseResult(ServiceResult<T> serviceResult) {
        BaseResult<T> baseResult = new BaseResult<T>();
        baseResult.setSuccess(serviceResult.isSuccess());
        baseResult.setData(serviceResult.getData());
        baseResult.setResultCode(serviceResult.getResultCode().getCode());
        baseResult.setResultMsg(serviceResult.getResultMsg());
        return baseResult;
    }

    public BaseResult<T> buildingBaseResult(ServiceResult<T> serviceResult, CommonResultEnum commonResultEnum) {
        BaseResult<T> baseResult = new BaseResult<T>();
        baseResult.setSuccess(serviceResult.isSuccess());
        baseResult.setData(serviceResult.getData());
        baseResult.setResultCode(commonResultEnum.getCode());
        baseResult.setResultMsg(commonResultEnum.getMsg());
        return baseResult;
    }

    public BaseResult<T> buildingBaseResult(Boolean isSuccess, T data, String resultCode, String resultMsg) {
        BaseResult<T> baseResult = new BaseResult<T>();
        baseResult.setSuccess(isSuccess);
        baseResult.setData(data);
        baseResult.setResultCode(resultCode);
        baseResult.setResultMsg(resultMsg);
        return baseResult;
    }

}
