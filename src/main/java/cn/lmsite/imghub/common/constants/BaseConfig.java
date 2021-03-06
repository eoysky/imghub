package cn.lmsite.imghub.common.constants;

/**
 * 项目基础配置类
 *
 * @author Jonny.Chang
 * @date 2020/09/18
 */
public class BaseConfig<T> {

    /** Token 失效时间（小时）*/
    public static final Long TOKEN_EXPIRATION_TIME = 6L;

    /** 关闭登录校验 */
    public static final boolean CLOSE_SECURITY = true;

    /** 构造函数 */
    private BaseConfig() {
    }
}
