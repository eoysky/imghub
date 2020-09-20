package cn.lmsite.imghub.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class OperationConfig implements Serializable {
    private static final long   serialVersionUID = 1L;
    private              Long   id;
    /**
     * 选项名称
     */
    private              String name;
    /**
     * 选项值，多项可存储json
     */
    private              String values;
    /**
     * 选项开关，ON:打开;OFF:关闭（默认）
     */
    private              String engineStatue;
    /**
     * 创建时间
     */
    private              Date   gmtCreate;
}