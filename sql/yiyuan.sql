/*
 Navicat MySQL Data Transfer

 Source Server         : ssm
 Source Server Type    : MySQL
 Source Server Version : 50540
 Source Host           : localhost:3306
 Source Schema         : yiyuan

 Target Server Type    : MySQL
 Target Server Version : 50540
 File Encoding         : 65001

 Date: 01/02/2021 18:40:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账号ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `open_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录账号,如手机号等',
  `category` tinyint(1) NULL DEFAULT NULL COMMENT '账号类别',
  `createtime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `editetime` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `editor` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `deleted` double(1, 0) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '逻辑删除:0=未删除,1=已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_member_id`(`user_id`) USING BTREE COMMENT '普通索引',
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '账号' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of account
-- ----------------------------

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `parent_id` bigint(100) UNSIGNED NOT NULL DEFAULT 0 COMMENT '所属父级权限ID',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限唯一CODE代码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限介绍',
  `category` int(3) NULL DEFAULT NULL COMMENT '权限类别',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'URL规则',
  `createtime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `editetime` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `editor` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '逻辑删除:0=未删除,1=已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parent_id`(`parent_id`) USING BTREE COMMENT '父级权限ID',
  INDEX `code`(`code`) USING BTREE COMMENT '权限CODE代码'
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, 0, 'system', '系统管理', '系统介绍', 1, NULL, '2021-01-28 15:58:22', 'admin', '2021-01-28 16:05:14', 'admin', 0);
INSERT INTO `permission` VALUES (2, 1, 'system.menu', '菜单管理', '菜单', 2, '/permission/menu', '2021-01-28 16:20:25', 'admin', '2021-01-28 16:49:36', 'admin', 0);
INSERT INTO `permission` VALUES (3, 2, 'system.menu.save', '增加', '增加', 3, '', '2021-01-28 16:29:06', 'admin', NULL, NULL, 0);
INSERT INTO `permission` VALUES (4, 2, 'system.menu.delete', '删除', '删除', 3, '', '2021-01-28 16:33:27', 'admin', NULL, NULL, 0);
INSERT INTO `permission` VALUES (5, 2, 'system.menu.update', '更新', '更新', 1, '', '2021-01-28 16:38:28', 'admin', NULL, NULL, 0);
INSERT INTO `permission` VALUES (6, 1, 'system.role', '角色管理', '角色', 2, '/role/form', '2021-01-28 16:50:51', 'admin', NULL, NULL, 0);
INSERT INTO `permission` VALUES (7, 6, 'system.role.save', '增加', '增加', 3, '', '2021-01-28 16:52:55', 'admin', NULL, NULL, 0);
INSERT INTO `permission` VALUES (8, 6, 'system.role.delete', '删除', '删除', 3, '', '2021-01-28 16:53:59', 'admin', NULL, NULL, 0);
INSERT INTO `permission` VALUES (9, 6, 'system.role.update', '更新', '更新', 3, '', '2021-01-28 16:54:29', 'admin', NULL, NULL, 0);
INSERT INTO `permission` VALUES (10, 1, 'system.user', '用户管理', '用户', 2, '/user/form', '2021-01-28 16:58:49', 'admin', NULL, NULL, 0);
INSERT INTO `permission` VALUES (11, 10, 'system.user.save', '增加', '增加', 3, '', '2021-01-28 17:00:54', 'admin', NULL, NULL, 0);
INSERT INTO `permission` VALUES (12, 10, 'system.user.delete', '删除', '删除', 3, '', '2021-01-28 17:01:31', 'admin', NULL, NULL, 0);
INSERT INTO `permission` VALUES (13, 10, 'system.user.update', '更新', '更新', 3, '', '2021-01-28 17:01:49', 'admin', NULL, NULL, 0);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '所属父级角色ID',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色唯一CODE代码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色介绍',
  `createtime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `editetime` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `editor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '逻辑删除:0=未删除,1=已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parent_id`(`parent_id`) USING BTREE COMMENT '父级权限ID',
  INDEX `code`(`code`) USING BTREE COMMENT '权限CODE代码'
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, NULL, 'PRIN_CODE', '院长', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `role` VALUES (2, NULL, 'Department_CODE', '科长', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `role` VALUES (3, NULL, 'Dep_CODE', '教务长', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `role` VALUES (7, NULL, 'Doctor_CODE', '医生', NULL, '2021-01-20 00:00:00', '', NULL, NULL, 0);
INSERT INTO `role` VALUES (16, NULL, 'ADMIN_CODE', '系统管理员', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `role` VALUES (17, NULL, 'People_CODE', '普通人', NULL, '2021-01-21 00:00:00', '', NULL, NULL, 0);

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NULL DEFAULT NULL COMMENT '权限ID',
  `createtime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `editetime` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `editor` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '逻辑删除:0=未删除,1=已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE COMMENT '角色ID',
  INDEX `permission_id`(`permission_id`) USING BTREE COMMENT '权限ID',
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1, 16, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `role_permission` VALUES (2, 16, 2, NULL, NULL, NULL, NULL, 0);
INSERT INTO `role_permission` VALUES (3, 16, 3, NULL, NULL, NULL, NULL, 0);
INSERT INTO `role_permission` VALUES (4, 16, 4, NULL, NULL, NULL, NULL, 0);
INSERT INTO `role_permission` VALUES (5, 16, 5, NULL, NULL, NULL, NULL, 0);
INSERT INTO `role_permission` VALUES (6, 16, 6, NULL, NULL, NULL, NULL, 0);
INSERT INTO `role_permission` VALUES (7, 16, 7, NULL, NULL, NULL, NULL, 0);
INSERT INTO `role_permission` VALUES (8, 16, 8, NULL, NULL, NULL, NULL, 0);
INSERT INTO `role_permission` VALUES (9, 16, 9, NULL, NULL, NULL, NULL, 0);
INSERT INTO `role_permission` VALUES (10, 16, 10, NULL, NULL, NULL, NULL, 0);
INSERT INTO `role_permission` VALUES (11, 16, 11, NULL, NULL, NULL, NULL, 0);
INSERT INTO `role_permission` VALUES (12, 16, 12, NULL, NULL, NULL, NULL, 0);
INSERT INTO `role_permission` VALUES (13, 16, 13, NULL, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for token
-- ----------------------------
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token`  (
  `id` bigint(80) NOT NULL DEFAULT 0,
  `userId` bigint(80) NULL DEFAULT NULL,
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of token
-- ----------------------------
INSERT INTO `token` VALUES (18801596623, 1, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MTIxNzU1MDMsImV4cCI6MTYxMjE3OTEwMywidXNlcklkIjoiMSIsImlzcyI6Imx3cyJ9.Ak9T2Ots7qHVfISzf1vhAs91zvxz-6jNNEhanxBzE2o');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `state` tinyint(1) NULL DEFAULT NULL COMMENT '用户状态:0=正常,1=禁用',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `head_img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像图片地址',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `salt` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码加盐',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `createtime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `editetime` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `editor` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '逻辑删除:0=未删除,1=已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 0, 'admin', '', '18801596623', '', '$2a$10$BZ.l2Cg5TCu9EoQpjSZSp..Q2XJsPicfLkNyFnY/bKFCQrSK73tpG', '2021-01-11 16:05:35', '', '2021-01-11 16:05:44', '', 0);
INSERT INTO `user` VALUES (2, 0, 'user', NULL, '13162308906', 'a96a85dd1c8a439eb1c247acb9e0bab8', '13162308906', '2021-01-20 00:00:00', NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (3, 0, 'user1', NULL, '13162308906', '3ccb36bda98e47bc8d30786172eb9919', '13162308906', '2021-01-20 00:00:00', NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `createtime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `editetime` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `editor` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '逻辑删除:0=未删除,1=已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `member_id`(`user_id`) USING BTREE COMMENT '用户ID',
  INDEX `role_id`(`role_id`) USING BTREE COMMENT '角色ID',
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 16, NULL, NULL, NULL, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
