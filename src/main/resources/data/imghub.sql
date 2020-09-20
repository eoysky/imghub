/*
 Navicat Premium Data Transfer

 Source Server         : ecs
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 47.99.172.232:3306
 Source Schema         : imghub

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 08/09/2020 22:59:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for image_info
-- ----------------------------
DROP TABLE IF EXISTS `image_info`;
CREATE TABLE `image_info`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '图片信息id，自增',
    `image_uid`   varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '图片uid，唯一，和images.imgid关联',
    `mime_type`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '图片MIME类型',
    `width`       double                                                        NOT NULL DEFAULT '0' COMMENT '图片宽',
    `height`      double                                                        NOT NULL DEFAULT '0' COMMENT '图片高',
    `view_times`  bigint                                                                 DEFAULT '0' COMMENT '图片浏览次数',
    `ext_name`    varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NOT NULL COMMENT '图片扩展名',
    `client_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '图片原始文件名',
    `tags`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT '' COMMENT '图片标签',
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT '' COMMENT '图片描述',
    `images_size` double(8, 0)                                                           DEFAULT NULL COMMENT '图片大小，单位kb',
    `gmt_create`  timestamp                                                     NOT NULL COMMENT '创建时间',
    `gmt_modify`  timestamp                                                     NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `imginfo_id` (`id`) USING BTREE,
    UNIQUE KEY `imginfo_imgid` (`image_uid`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for images
-- ----------------------------
DROP TABLE IF EXISTS `images`;
CREATE TABLE `images`
(
    `id`             bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '图片数字ID，自增',
    `image_uid`      char(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     NOT NULL COMMENT '图片uid，唯一',
    `image_path`     char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     NOT NULL COMMENT '图片路径',
    `thumb_path`     char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci              DEFAULT NULL COMMENT '缩略图路径',
    `storage_engine` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     NOT NULL COMMENT '存储引擎',
    `storage_ip`     char(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci    NOT NULL COMMENT '用户IP',
    `storage_ua`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户UA',
    `upload_time`    timestamp                                                     NOT NULL COMMENT '图片上传日期',
    `owner`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT 'visitor' COMMENT '上传者（游客：visitor， 其他：用户名 userName）',
    `compression`    tinyint(1)                                                    NOT NULL DEFAULT '0' COMMENT '图片是否压缩（true: 已压缩,  false: 未压缩）',
    `watermark`      tinyint                                                                DEFAULT '0' COMMENT '图片水印',
    `level`          varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT 'unknown' COMMENT '图片等级（unknown: 未识别，normal: 正常, porn: 色情）',
    `others`         text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '其它信息',
    `token`          varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci           DEFAULT NULL COMMENT 'token，用于删除图片',
    `gmt_create`     timestamp                                                     NOT NULL COMMENT '创建时间',
    `gmt_modify`     timestamp                                                     NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`, `image_uid`) USING BTREE,
    UNIQUE KEY `images_id` (`id`) USING BTREE,
    UNIQUE KEY `images_imgid` (`image_uid`) USING BTREE,
    UNIQUE KEY `images_token` (`token`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for invitation_code
-- ----------------------------
DROP TABLE IF EXISTS `invitation_code`;
CREATE TABLE `invitation_code`
(
    `id`             bigint                                                       NOT NULL AUTO_INCREMENT COMMENT '主键Id，自增',
    `invite_code`    varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邀请码',
    `email`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户邮箱',
    `status`         tinyint                                                      NOT NULL COMMENT '邀请码状态（0: 可用 1: 已使用 2: 已过期）',
    `ext_info`       text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '其它信息',
    `gmt_expiration` timestamp                                                    NOT NULL COMMENT '过期时间',
    `gmt_create`     timestamp                                                    NOT NULL COMMENT '创建时间',
    `gmt_modify`     timestamp                                                    NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for operation_config
-- ----------------------------
DROP TABLE IF EXISTS `operation_config`;
CREATE TABLE `operation_config`
(
    `id`            bigint                                                  NOT NULL AUTO_INCREMENT,
    `name`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '选项名称',
    `values`        text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '选项值，多项可存储json',
    `engine_statue` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '选项开关，ON:打开;OFF:关闭（默认）',
    `gmt_create`    timestamp                                               NOT NULL COMMENT '创建时间',
    `gmt_modify`    timestamp                                               NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 19
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for storage_engine
-- ----------------------------
DROP TABLE IF EXISTS `storage_engine`;
CREATE TABLE `storage_engine`
(
    `id`              bigint                                                        NOT NULL AUTO_INCREMENT,
    `engine_name`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '存储引擎的名称',
    `engine_type`     varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '存储引擎，localhost：本地存储，',
    `engine_domains`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '存储引擎绑定的域名',
    `engine_ext_info` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '存储引擎扩展信息',
    `engine_statue`   tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     NOT NULL COMMENT '存储引擎开关，ON：打开，OFF：关闭',
    `engine_ weight`  tinyint                                                       NOT NULL DEFAULT '0' COMMENT '存储引擎的权重，0-99，权重越高，越靠前',
    `allow_visitor`   tinyint(1)                                                    NOT NULL DEFAULT '0' COMMENT '是否允许游客上传，true:允许,false:拒绝',
    `other_info`      text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '其它信息',
    `gmt_create`      timestamp                                                     NOT NULL COMMENT '创建时间',
    `gmt_modify`      timestamp                                                     NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `engine` (`engine_type`) USING BTREE COMMENT '存储引擎外键'
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`             bigint                                                        NOT NULL AUTO_INCREMENT,
    `uid`            varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户UID',
    `user_name`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户名（唯一）',
    `nick_name`      varchar(32)                                                            DEFAULT NULL COMMENT '用户昵称',
    `gender`         tinyint(1)                                                    NOT NULL DEFAULT '0' COMMENT '性别（0: 男  1: 女  2: 保密）',
    `passwd`         varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户密码',
    `phone_num`      bigint                                                        NOT NULL COMMENT '用户电话号码，唯一',
    `email`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户邮箱，唯一',
    `status`         tinyint(1)                                                    NOT NULL DEFAULT '0' COMMENT '用户状态（0: 正常 1: 冻结 2: 删除）',
    `permission`     tinyint(1)                                                    NOT NULL DEFAULT '0' COMMENT '用户权限（0: 普通用户 1: 高级用户 2: 管理员）',
    `storage_engine` bigint                                                                 DEFAULT NULL COMMENT '存储引擎Id',
    `last_login`     timestamp                                                     NOT NULL COMMENT '最后登录时间',
    `upload_limit`   int                                                           NOT NULL DEFAULT '20' COMMENT '用户上传限制',
    `upload_total`   bigint                                                        NOT NULL DEFAULT '0' COMMENT '用户总上传数量',
    `avatar_url`     varchar(255)                                                           DEFAULT NULL COMMENT '用户头像URL地址',
    `welcome_msg`    text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '用户欢迎信息',
    `reg_ua`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户注册UA',
    `reg_ip`         varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户注册IP',
    `gmt_create`     timestamp                                                     NOT NULL COMMENT '注册时间',
    `gmt_modify`     timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`, `uid`, `user_name`, `phone_num`, `email`) USING BTREE,
    UNIQUE KEY `users` (`id`, `uid`, `user_name`, `email`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
