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

 Date: 21/06/2024 18:02:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `shop_id` int NOT NULL COMMENT '所属店铺',
  `user_id` int NOT NULL COMMENT '用户id---wd_user.id',
  `status` tinyint NOT NULL COMMENT '授权状态 1 已授权 2 已禁用',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除:0.未删除，1.已删除’,—默认为0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '员工表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, '2024-06-03 14:39:05', '2024-06-03 14:41:58', 1, 2, 1, 0);
INSERT INTO `employee` VALUES (2, '2024-06-03 15:08:32', '2024-06-03 15:08:32', 1, 3, 1, 0);

-- ----------------------------
-- Table structure for good
-- ----------------------------
DROP TABLE IF EXISTS `good`;
CREATE TABLE `good`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `good_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名',
  `detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品描述',
  `price` int NOT NULL COMMENT '价格',
  `count` int NULL DEFAULT NULL COMMENT '库存',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `shop_id` int NULL DEFAULT NULL COMMENT 'shop.id',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态 0未上架，1上架，2已下架',
  `is_deleted` tinyint NULL DEFAULT 0 COMMENT '0未删除，1已删除',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of good
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 2135367683 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of operation
-- ----------------------------
INSERT INTO `operation` VALUES (-859328511, '2024-06-21 18:00:57', '2024-06-21 18:00:57', 1, 'LIST', '查询用户信息');
INSERT INTO `operation` VALUES (1, '2024-06-21 14:57:30', '2024-06-21 16:29:01', 2, 'LIST', '查询用户信息');
INSERT INTO `operation` VALUES (2, '2024-06-21 14:58:33', '2024-06-21 15:38:03', 1, 'LIST', '查询用户信息');
INSERT INTO `operation` VALUES (4, '2024-06-21 15:24:12', '2024-06-21 15:38:18', 1, 'INSERT', '新增店铺');
INSERT INTO `operation` VALUES (5, '2024-06-21 15:23:16', '2024-06-21 15:38:21', 1, 'OTHER', '用户测试');
INSERT INTO `operation` VALUES (1573367809, '2024-06-21 18:01:32', '2024-06-21 18:01:32', 1, 'OTHER', '用户测试');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `shop_id` int NOT NULL COMMENT '店铺id---wd_shop.id',
  `goods_id` int NOT NULL COMMENT '商品id---wd_goods.id',
  `num` int NOT NULL COMMENT '购买数量',
  `amount` int NOT NULL DEFAULT 0 COMMENT '金额(单位：分)',
  `real_amount` int NOT NULL DEFAULT 0 COMMENT '实付金额(单位：分)',
  `user_id` int NOT NULL COMMENT '用户id',
  `remark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '备注',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '订单状态( 1:待支付  2:待揽收(已支付)  3:收发中心已揽收  4:收发中心已发出  5:干洗中心清洗中 6:干洗中心已发出 7:收发中心待配送 8:收发中心已送达 9:已完成 10:已取消 11:已关闭)',
  `refund_status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '退款状态 0未退款 1.申请中 2.已退款 3退款失败',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否已删除(0:未删除  1:已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限名',
  `remark` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限说明',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, 'user.list', '获取用户列表', '2024-06-18 17:29:32', '2024-06-18 17:29:32');
INSERT INTO `permission` VALUES (2, 'role.add', '增加角色', '2024-06-18 17:29:51', '2024-06-18 17:29:51');
INSERT INTO `permission` VALUES (3, 'shop.add', '增加店铺', '2024-06-18 17:30:22', '2024-06-18 17:30:22');

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
  `permission_id` int NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1, '2024-06-18 17:31:28', '2024-06-18 17:31:28', 1, 1);
INSERT INTO `role_permission` VALUES (2, '2024-06-18 17:31:38', '2024-06-18 17:31:38', 1, 2);
INSERT INTO `role_permission` VALUES (3, '2024-06-18 17:31:47', '2024-06-18 17:31:47', 1, 3);

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '店铺名称',
  `descrip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '店铺描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `phone` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '客服电话',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '营业状态(0:休息 1:营业)',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否已删除(0:未删除  1:已删除)',
  `cate` int NOT NULL DEFAULT 1 COMMENT '店铺类型(1：普通店铺 2：收发中心 3：干洗中心)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1824989186 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES (-2078724094, '杭州下沙店', '专门洗衣服的', '2024-06-03 16:36:18', '2024-06-03 16:36:18', '', 1, 0, 1);
INSERT INTO `shop` VALUES (-1067896831, '杭电专营店', '专门洗衣服的', '2024-06-03 16:55:25', '2024-06-03 16:55:25', '', 1, 0, 1);
INSERT INTO `shop` VALUES (1, '测试店铺', '测试', '2024-06-03 14:39:57', '2024-06-03 14:39:57', '55847', 1, 0, 0);
INSERT INTO `shop` VALUES (932786177, '全国连锁店', '专门洗衣服的', '2024-06-03 17:16:50', '2024-06-03 17:16:50', '', 1, 0, 1);
INSERT INTO `shop` VALUES (1824989185, '精品电竞', '鼠标键盘', '2024-06-21 15:24:12', '2024-06-21 15:24:12', '', 1, 0, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账户限定长度8位',
  `password` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码建议10位，最少6位',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像 暂定链接，七牛云上传',
  `phone` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话号码',
  `status` tinyint NOT NULL COMMENT '授权状态 1 已授权 2 已禁用',
  `username` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123456', NULL, '15012345678', 1, '张三', '2024-06-18 15:02:33', '2024-06-18 15:02:33');
INSERT INTO `user` VALUES (2, 'employee', '123456', NULL, '15014741478', 1, '李四', '2024-06-18 15:02:33', '2024-06-18 15:02:33');
INSERT INTO `user` VALUES (3, 'manager', '123456', NULL, '15036985896', 1, '王五', '2024-06-18 15:02:33', '2024-06-18 15:02:33');
INSERT INTO `user` VALUES (4, 'user', '123456', NULL, '15056971468', 1, '赵六', '2024-06-18 15:02:33', '2024-06-18 15:02:33');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `user_id` int NOT NULL COMMENT '用户id',
  `role_id` int NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, '2024-06-18 17:18:06', '2024-06-18 17:18:06', 1, 1);
INSERT INTO `user_role` VALUES (2, '2024-06-18 17:18:18', '2024-06-18 17:18:18', 2, 2);
INSERT INTO `user_role` VALUES (3, '2024-06-18 17:18:23', '2024-06-18 17:18:23', 3, 3);
INSERT INTO `user_role` VALUES (4, '2024-06-18 17:18:33', '2024-06-18 17:18:33', 4, 4);

SET FOREIGN_KEY_CHECKS = 1;
