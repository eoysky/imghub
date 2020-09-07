package cn.lmsite.imghub.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Images implements Serializable {
    private static final long    serialVersionUID = 1L;
    /**
     * 图片数字ID，自增
     */
    private              Long    id;
    /**
     * 图片uid，唯一
     */
    private              String  imageUid;
    /**
     * 图片路径
     */
    private              String  imagePath;
    /**
     * 缩略图路径
     */
    private              String  thumbPath;
    /**
     * 存储引擎
     */
    private              String  storageEngine;
    /**
     * 用户IP
     */
    private              String  storageIp;
    /**
     * 用户UA
     */
    private              String  storageUa;
    /**
     * 图片上传日期
     */
    private              Date    uploadTime;
    /**
     * 上传者（游客：visitor， 其他：用户名 userName）
     */
    private              String  owner;
    /**
     * 图片是否压缩（true: 已压缩,  false: 未压缩）
     */
    private              Boolean compression;
    /**
     * 图片水印
     */
    private              Byte    watermark;
    /**
     * 图片等级（unknown: 未识别，normal: 正常, porn: 色情）
     */
    private              String  level;
    /**
     * 其它信息
     */
    private              String  others;
    /**
     * token，用于删除图片
     */
    private              String  token;
    /**
     * 创建时间
     */
    private              Date    gmtCreate;
}