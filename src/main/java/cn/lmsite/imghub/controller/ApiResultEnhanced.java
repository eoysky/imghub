package cn.lmsite.imghub.controller;

import cn.lmsite.imghub.common.enums.ResultCodeEnum;
import cn.lmsite.imghub.common.result.PageResult;
import cn.lmsite.imghub.common.result.BaseResult;
import cn.lmsite.imghub.common.result.ServicePageResult;
import cn.lmsite.imghub.common.result.ServiceResult;

/**
 * Api 结果增强基础类
 *
 * @author Jonny.Chang
 * @date 2020/09/18
 */
public class ApiResultEnhanced {

    /**
     * 通过service结果构建api返回结果
     *
     * @param serviceResult 服务结果
     * @return {@link BaseResult<T>}
     */
    protected static <T> BaseResult<T> buildResultByServiceRes(ServiceResult<T> serviceResult) {
        ResultCodeEnum resultCode = serviceResult.getResultCode();
        return new BaseResult<>(ResultCodeEnum.SUCCESS == resultCode, resultCode.getCode(),
                serviceResult.getData(), resultCode.getMsg());
    }

    /**
     * 通过 servicePage 结果构建api返回结果
     *
     * @param serviceResult 服务结果
     * @return {@link PageResult<T>}
     */
    protected static <T> PageResult<T> buildPageResByServiceRes(ServicePageResult<T> serviceResult) {
        ResultCodeEnum resultCode = serviceResult.getResultCode();
        return new PageResult<>(ResultCodeEnum.SUCCESS == resultCode, resultCode.getCode(),
                serviceResult.getData(), resultCode.getMsg(), serviceResult.getPageNo(), serviceResult.getPageSize(),
                serviceResult.getTotalCount());
    }
}
