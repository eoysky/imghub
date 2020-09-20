package cn.lmsite.imghub.common.result;

/**
 * 结果代码 获取结果代码接口
 *
 * @author Jonny.Chang  ( https://jonnyhub.com )  @jonny6015
 */
public interface ResultCode {

    /**
     * 获取代码 获取结果码
     *
     * @return {@link String}
     */
    public String getCode();

    /**
     * 得到味精 获取消息
     *
     * @return {@link String}
     */
    public String getMsg();
}
