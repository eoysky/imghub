package cn.lmsite.imghub.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class InvitationCode implements Serializable {
    private static final long   serialVersionUID = 1L;
    /**
     * 主键Id，自增
     */
    private              Long   id;
    /**
     * 邀请码
     */
    private              String inviteCode;
    /**
     * 用户邮箱
     */
    private              String email;
    /**
     * 邀请码状态（0: 可用 1: 已使用 2: 已过期）
     */
    private              Byte   status;
    /**
     * 其它信息
     */
    private              String extInfo;
    /**
     * 过期时间
     */
    private              Date   gmtExpiration;
    /**
     * 创建时间
     */
    private              Date   gmtCreate;
}