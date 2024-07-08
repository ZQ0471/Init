/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80037 (8.0.37)
 Source Host           : localhost:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 80037 (8.0.37)
 File Encoding         : 65001

 Date: 08/07/2024 13:39:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for operation
-- ----------------------------
DROP TABLE IF EXISTS `operation`;
CREATE TABLE `operation`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `user_id` int NULL DEFAULT NULL COMMENT '操作者',
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作类型 1：信息管理 2：业务流程',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2135367707 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of operation
-- ----------------------------
INSERT INTO `operation` VALUES (2135367683, '2024-07-01 17:35:24', '2024-07-01 17:35:24', 1, 'LIST', '查询用户信息');
INSERT INTO `operation` VALUES (2135367684, '2024-07-01 17:44:15', '2024-07-01 17:44:15', 1, 'INSERT', '新增权限');
INSERT INTO `operation` VALUES (2135367685, '2024-07-01 17:44:42', '2024-07-01 17:44:42', 1, 'INSERT', '新增权限');
INSERT INTO `operation` VALUES (2135367686, '2024-07-01 17:44:55', '2024-07-01 17:44:55', 1, 'INSERT', '新增权限');
INSERT INTO `operation` VALUES (2135367687, '2024-07-01 17:44:57', '2024-07-01 17:44:57', 1, 'INSERT', '新增权限');
INSERT INTO `operation` VALUES (2135367688, '2024-07-01 17:46:05', '2024-07-01 17:46:05', 1, 'INSERT', '新增权限');
INSERT INTO `operation` VALUES (2135367689, '2024-07-01 17:46:25', '2024-07-01 17:46:25', 1, 'INSERT', '新增权限');
INSERT INTO `operation` VALUES (2135367690, '2024-07-01 18:00:45', '2024-07-01 18:00:45', 1, 'INSERT', '新增店铺');
INSERT INTO `operation` VALUES (2135367691, '2024-07-01 18:00:52', '2024-07-01 18:00:52', 1, 'INSERT', '新增店铺');
INSERT INTO `operation` VALUES (2135367692, '2024-07-01 18:00:54', '2024-07-01 18:00:54', 1, 'INSERT', '新增店铺');
INSERT INTO `operation` VALUES (2135367693, '2024-07-01 18:00:55', '2024-07-01 18:00:55', 1, 'INSERT', '新增店铺');
INSERT INTO `operation` VALUES (2135367694, '2024-07-01 18:01:32', '2024-07-01 18:01:32', 1, 'INSERT', '新增店铺');
INSERT INTO `operation` VALUES (2135367695, '2024-07-01 18:01:38', '2024-07-01 18:01:38', 1, 'INSERT', '新增店铺');
INSERT INTO `operation` VALUES (2135367696, '2024-07-01 18:01:42', '2024-07-01 18:01:42', 1, 'LIST', '查询店铺列表');
INSERT INTO `operation` VALUES (2135367697, '2024-07-03 09:45:33', '2024-07-03 09:45:33', 1, 'LIST', '查询用户信息');
INSERT INTO `operation` VALUES (2135367698, '2024-07-03 09:50:58', '2024-07-03 09:50:58', 1, 'INSERT', '新增店铺');
INSERT INTO `operation` VALUES (2135367699, '2024-07-03 09:51:18', '2024-07-03 09:51:18', 1, 'LIST', '获取角色列表');
INSERT INTO `operation` VALUES (2135367700, '2024-07-03 09:51:28', '2024-07-03 09:51:28', 1, 'LIST', '获取权限列表');
INSERT INTO `operation` VALUES (2135367701, '2024-07-03 09:53:02', '2024-07-03 09:53:02', 1, 'INSERT', '新增权限');
INSERT INTO `operation` VALUES (2135367702, '2024-07-03 09:53:38', '2024-07-03 09:53:38', 1, 'INSERT', '新增权限');
INSERT INTO `operation` VALUES (2135367703, '2024-07-03 09:53:42', '2024-07-03 09:53:42', 1, 'LIST', '获取权限列表');
INSERT INTO `operation` VALUES (2135367704, '2024-07-03 09:53:51', '2024-07-03 09:53:51', 1, 'LIST', '获取权限列表');
INSERT INTO `operation` VALUES (2135367705, '2024-07-03 09:55:16', '2024-07-03 09:55:16', 1, 'INSERT', '新增权限');
INSERT INTO `operation` VALUES (2135367706, '2024-07-03 09:55:20', '2024-07-03 09:55:20', 1, 'LIST', '获取权限列表');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限名',
  `remark` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限说明',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, 'user.list', '获取用户列表', '2024-06-18 17:29:32', '2024-06-18 17:29:32');
INSERT INTO `permission` VALUES (2, 'role.add', '增加角色', '2024-06-18 17:29:51', '2024-06-18 17:29:51');
INSERT INTO `permission` VALUES (3, 'shop.add', '增加店铺', '2024-06-18 17:30:22', '2024-06-18 17:30:22');
INSERT INTO `permission` VALUES (4, 'permission.*', '权限方面', '2024-07-01 17:44:15', '2024-07-01 17:44:15');
INSERT INTO `permission` VALUES (5, 'role.*', '角色方面', '2024-07-01 17:44:42', '2024-07-01 17:44:42');
INSERT INTO `permission` VALUES (6, 'shop.*', '店铺方面', '2024-07-01 17:44:55', '2024-07-01 17:44:55');
INSERT INTO `permission` VALUES (7, 'employee.add', NULL, '2024-07-03 09:53:02', '2024-07-03 09:53:02');
INSERT INTO `permission` VALUES (8, 'employee.list', NULL, '2024-07-03 09:53:38', '2024-07-03 09:53:38');
INSERT INTO `permission` VALUES (9, 'shop.update', '暂无说明', '2024-07-03 09:55:16', '2024-07-03 09:55:16');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名',
  `remark` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin', '超级管理员', '2024-06-18 15:01:11', '2024-06-18 15:01:11');
INSERT INTO `role` VALUES (2, 'manager', '管理员', '2024-06-18 15:01:11', '2024-06-18 15:01:11');
INSERT INTO `role` VALUES (3, 'employee', '员工', '2024-06-18 15:01:11', '2024-06-18 15:01:11');
INSERT INTO `role` VALUES (4, 'user', '用户', '2024-06-18 15:01:11', '2024-06-18 15:01:11');
INSERT INTO `role` VALUES (5, 'visitor', '什么都不能做', '2024-06-19 10:52:17', '2024-06-19 10:52:17');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `role_id` int NOT NULL COMMENT '角色id',
  `permission` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1, '2024-06-18 17:31:28', '2024-07-01 17:45:56', 1, 'permission.*');
INSERT INTO `role_permission` VALUES (2, '2024-06-18 17:31:38', '2024-07-01 17:36:20', 1, 'shop.*');
INSERT INTO `role_permission` VALUES (3, '2024-06-18 17:31:47', '2024-07-01 17:36:26', 1, 'role.*');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账户限定长度8位',
  `password` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码建议10位，最少6位',
  `phone` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话号码',
  `username` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `role_id` int NOT NULL COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123456', '15012345678', '张三', '2024-06-18 15:02:33', '2024-07-01 16:41:33', 1);
INSERT INTO `user` VALUES (2, 'employee', '123456', '15014741478', '李四', '2024-06-18 15:02:33', '2024-07-01 16:41:35', 2);
INSERT INTO `user` VALUES (3, 'manager', '123456', '15036985896', '王五', '2024-06-18 15:02:33', '2024-07-01 16:41:37', 3);
INSERT INTO `user` VALUES (4, 'user', '123456', '15056971468', '赵六', '2024-06-18 15:02:33', '2024-07-01 16:41:40', 4);

SET FOREIGN_KEY_CHECKS = 1;
