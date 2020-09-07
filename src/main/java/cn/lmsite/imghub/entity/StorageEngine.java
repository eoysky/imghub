package cn.lmsite.imghub.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class StorageEngine implements Serializable {
    private static final long    serialVersionUID = 1L;
    private              Long    id;
    /**
     * 存储引擎的名称
     */
    private              String  engineName;
    /**
     * 存储引擎，localhost：本地存储，
     */
    private              String  engineType;
    /**
     * 存储引擎绑定的域名
     */
    private              String  engineDomains;
    /**
     * 存储引擎扩展信息
     */
    private              String  engineExtInfo;
    /**
     * 存储引擎开关，ON：打开，OFF：关闭
     */
    private              String  engineStatue;
    /**
     * 存储引擎的权重，0-99，权重越高，越靠前
     */
    private              Byte    engineWeight;
    /**
     * 是否允许游客上传，true:允许,false:拒绝
     */
    private              Boolean allowVisitor;
    /**
     * 其它信息
     */
    private              String  otherInfo;
    /**
     * 创建时间
     */
    private              Date    gmtCreate;
}