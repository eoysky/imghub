package cn.lmsite.imghub.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ImageInfo implements Serializable {
    private static final long   serialVersionUID = 1L;
    /**
     * 图片信息id，自增
     */
    private              Long   id;
    /**
     * 图片uid，唯一，和images.imgid关联
     */
    private              String imageUid;
    /**
     * 图片MIME类型
     */
    private              String mimeType;
    /**
     * 图片宽
     */
    private              Double width;
    /**
     * 图片高
     */
    private              Double height;
    /**
     * 图片浏览次数
     */
    private              Long   viewTimes;
    /**
     * 图片扩展名
     */
    private              String extName;
    /**
     * 图片原始文件名
     */
    private              String clientName;
    /**
     * 图片标签
     */
    private              String tags;
    /**
     * 图片描述
     */
    private              String description;
    /**
     * 图片大小，单位kb
     */
    private              Double imagesSize;
    /**
     * 创建时间
     */
    private              Date   gmtCreate;
}