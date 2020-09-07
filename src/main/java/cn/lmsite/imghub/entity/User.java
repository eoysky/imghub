package cn.lmsite.imghub.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户UID */
    private String uid;

    /** 用户名（唯一） */
    private String userName;

    /** 用户电话号码，唯一 */
    private Long phoneNum;

    /** 用户邮箱，唯一 */
    private String email;

    /** 用户昵称 */
    private String nickName;

    /** 性别（0: 男  1: 女  2: 保密） */
    private Boolean gender;

    /** 用户密码 */
    private String passwd;

    /** 用户状态（0: 正常 1: 冻结 2: 删除） */
    private Boolean status;

    /** 用户权限（0: 普通用户 1: 高级用户 2: 管理员） */
    private Boolean permission;

    /** 存储引擎Id */
    private Long storageEngine;

    /** 最后登录时间 */
    private Date lastLogin;

    /** 用户上传限制 */
    private Integer uploadLimit;

    /** 用户总上传数量 */
    private Long uploadTotal;

    /** 用户头像URL地址 */
    private String avatarUrl;

    /** 用户欢迎信息 */
    private String welcomeMsg;

    /** 用户注册UA */
    private String regUa;

    /** 用户注册IP */
    private String regIp;

    /** 注册时间 */
    private Date gmtCreate;
}