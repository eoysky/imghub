package cn.lmsite.imghub.utils;

import com.alibaba.fastjson.JSONObject;

public class JsonUtils {

    public static <T> JSONObject beanToJsonObject(T bean) {
        if (bean == null) {
            return null;
        }
        return JSONObject.parseObject(JSONObject.toJSONString(bean));
    }
}
