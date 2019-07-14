/*
Navicat MySQL Data Transfer

Source Server         : mysql-local
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : springboot-web

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2019-07-14 18:44:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for gw_dict
-- ----------------------------
DROP TABLE IF EXISTS `gw_dict`;
CREATE TABLE `gw_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '字典名称',
  `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '字典类型',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '字典码',
  `value` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '字典值',
  `order_num` int(11) DEFAULT '0' COMMENT '排序',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标记  0：正常  1：已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`,`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='数据字典表';

-- ----------------------------
-- Records of gw_dict
-- ----------------------------

-- ----------------------------
-- Table structure for gw_element_permission
-- ----------------------------
DROP TABLE IF EXISTS `gw_element_permission`;
CREATE TABLE `gw_element_permission` (
  `ep_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '元素名称',
  `permission_name` varchar(50) DEFAULT NULL COMMENT '元素权限名称',
  `icon` varchar(50) DEFAULT NULL COMMENT '元素图标',
  `url` varchar(1200) DEFAULT NULL COMMENT '元素对应的url',
  `type` varchar(2) DEFAULT '0' COMMENT '元素类型 0:菜单 1:按钮  2:图片  3:文件',
  `own_page` varchar(200) DEFAULT NULL COMMENT '所属页面',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父节点',
  `status` varchar(2) DEFAULT '0' COMMENT '元素状态 0:正常  1:禁用',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ep_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='页面元素权限表';

-- ----------------------------
-- Records of gw_element_permission
-- ----------------------------
INSERT INTO `gw_element_permission` VALUES ('1', '测试', 'home:button:test', null, null, '1', 'home.html', null, '0', '测试shiro权限控制');

-- ----------------------------
-- Table structure for gw_log
-- ----------------------------
DROP TABLE IF EXISTS `gw_log`;
CREATE TABLE `gw_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户id',
  `operation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户操作',
  `clazz` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '请求的类',
  `method` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '请求参数',
  `ex_msg` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '异常信息',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `test_time` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `clazz` (`clazz`,`method`),
  KEY `idx_userid` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统日志';

-- ----------------------------
-- Records of gw_log
-- ----------------------------
INSERT INTO `gw_log` VALUES ('1', 'fsdf1', '新增ds', 'ere', 'get', 'fsfd', null, '13165463123', '0.0.0.1', '2019-01-22 13:18:04', '2019-01-23 10:31:53', '2019-01-09');
INSERT INTO `gw_log` VALUES ('5', 'fsdf2', '大是大非', 'GKHKK', 'post', 'fsfd', null, '13165463123', '0.0.0.1', '2019-01-15 11:00:04', '2019-01-22 18:38:32', '2019-01-22');
INSERT INTO `gw_log` VALUES ('6', 'fsdf3', '新增', 'ere', 'get', 'fsfd', null, '13165463123', '0.0.0.1', '2019-01-22 10:11:04', '2019-01-22 18:38:38', '2019-01-07');
INSERT INTO `gw_log` VALUES ('7', 'fsdf4', '而我认为', 'ere', 'post', 'fsfd', null, '13165463123', '0.0.0.1', '2019-01-02 11:36:04', '2019-01-22 18:38:43', null);
INSERT INTO `gw_log` VALUES ('8', 'fsdf5', '百分点', 'ere', 'post', 'fsfd', null, '13165463123', '0.0.0.1', '2019-01-22 11:47:04', '2019-01-22 18:38:50', null);
INSERT INTO `gw_log` VALUES ('9', 'fsdf6', '新增', 'ere', 'get', 'fsfd', null, '13165463123', '0.0.0.1', '2019-01-17 11:18:04', '2019-01-22 17:11:47', null);
INSERT INTO `gw_log` VALUES ('10', 'fsdf7', '搜房网而', 'ere', 'post', 'fsfd', null, '13165463123', '0.0.0.1', '2019-01-22 08:18:04', '2019-01-22 17:11:47', null);
INSERT INTO `gw_log` VALUES ('11', 'fsdf8', '时所发生的', 'ere', 'get', 'fsfd', null, '13165463123', '0.0.0.1', '2019-01-29 11:18:04', '2019-01-22 17:11:47', null);
INSERT INTO `gw_log` VALUES ('12', 'fsdf9', '我认为若若', 'ere', 'post', 'fsfd', null, '13165463123', '0.0.0.1', '2019-01-22 11:18:04', '2019-01-22 17:11:47', null);
INSERT INTO `gw_log` VALUES ('13', 'fsdf10', 'fsd', 'ere', 'get', 'fsfd', null, '13165463123', '0.0.0.1', '2019-01-13 11:18:04', '2019-01-22 17:11:47', null);
INSERT INTO `gw_log` VALUES ('14', 'fsdf11', '风的额', 'ere', 'post', 'fsfd', null, '13165463123', '0.0.0.1', '2019-01-25 11:18:04', '2019-01-22 17:11:47', null);
INSERT INTO `gw_log` VALUES ('15', 'fsdf12', 'fsd', 'ere', 'get', 'fsfd', null, '13165463123', '0.0.0.1', '2019-01-22 11:18:04', '2019-01-22 17:11:47', null);
INSERT INTO `gw_log` VALUES ('16', 'fsdf13', '舒服舒服', 'ere', 'post', 'fsfd', null, '13165463123', '0.0.0.1', '2019-01-19 11:18:04', '2019-01-22 17:11:47', null);
INSERT INTO `gw_log` VALUES ('17', 'fsdf14', '那挺好他说的方式', 'ere', 'post', 'fsfd', null, '13165463123', '0.0.0.1', '2019-02-01 11:18:04', '2019-01-22 17:11:47', null);

-- ----------------------------
-- Table structure for gw_menu
-- ----------------------------
DROP TABLE IF EXISTS `gw_menu`;
CREATE TABLE `gw_menu` (
  `menu_id` int(10) NOT NULL COMMENT '菜单id',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `url` varchar(250) DEFAULT NULL COMMENT '菜单链接',
  `parent_id` int(10) DEFAULT NULL COMMENT '父菜单id',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(10) DEFAULT NULL COMMENT '菜单排序序号',
  `menu_desc` varchar(100) DEFAULT NULL COMMENT '菜单描述',
  `status` varchar(2) DEFAULT '0' COMMENT '菜单状态 0:正常 1:禁用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统菜单表';

-- ----------------------------
-- Records of gw_menu
-- ----------------------------
INSERT INTO `gw_menu` VALUES ('100', '系统管理', ' ', '0', 'fa fa-windows', '1', '系统管理 - 父级目录', '0', '2019-02-27 14:31:17', '2019-02-27 14:31:17');
INSERT INTO `gw_menu` VALUES ('101', '编程分类', ' ', '0', 'fa fa-table', '2', '编程分类 - 父级目录', '0', '2019-02-27 14:31:17', '2019-02-27 14:31:17');
INSERT INTO `gw_menu` VALUES ('102', '随笔', ' ', '0', 'fa fa-language', '3', '自己想写些什么东西，记录一下 - 父级目录', '0', '2019-02-27 14:31:17', '2019-02-27 14:31:17');
INSERT INTO `gw_menu` VALUES ('1001', '用户管理', 'sysMng/userMng', '100', 'fa fa-user', '1', '管理系统用户，系统管理子项', '0', '2019-02-27 14:31:17', '2019-04-07 22:20:19');
INSERT INTO `gw_menu` VALUES ('1002', '角色管理', 'sysMng/roleMng', '100', 'fa fa-user-md', '2', '管理系统角色，系统管理子项', '0', '2019-02-27 14:31:17', '2019-04-07 22:20:16');
INSERT INTO `gw_menu` VALUES ('1003', '菜单管理', 'sysMng/menuMng', '100', 'fa fa-th-list', '3', '管理系统菜单，系统管理子项', '0', '2019-02-27 14:31:17', '2019-04-07 22:20:14');
INSERT INTO `gw_menu` VALUES ('1004', '页面元素权限管理', 'sysMng/elementPromissionMng', '100', 'fa fa-th', '4', '管理系统中页面元素权限，系统管理子项', '0', '2019-02-27 14:31:17', '2019-04-07 22:20:12');
INSERT INTO `gw_menu` VALUES ('1005', '数据字典管理', 'sysMng/dictMng', '100', 'fa fa-th-large', '5', '管理系统中数据字典，系统管理子项', '0', '2019-02-27 14:31:17', '2019-04-07 22:20:03');
INSERT INTO `gw_menu` VALUES ('1006', '系统日志管理', 'sysMng/logMng', '100', 'fa fa-list', '6', '管理系统中日志，系统管理子项', '0', '2019-02-27 14:31:17', '2019-04-07 22:20:02');
INSERT INTO `gw_menu` VALUES ('1011', 'java', 'pgSort/java', '101', 'fa fa-glass', '1', 'java - 编程分类子项', '0', '2019-02-27 14:31:17', '2019-04-07 22:18:41');
INSERT INTO `gw_menu` VALUES ('1012', '前端', 'pgSort/javaScript ', '101', 'fa fa-html5', '2', '前端 - 编程分类子项', '0', '2019-02-27 14:31:17', '2019-04-07 22:18:49');
INSERT INTO `gw_menu` VALUES ('1013', 'linux', 'pgSort/linux', '101', 'fa fa-linux', '3', 'linux - 编程分类子项', '0', '2019-02-27 14:31:17', '2019-04-07 22:20:21');
INSERT INTO `gw_menu` VALUES ('1014', '数据库', 'pgSort/datasource', '101', 'fa fa-database', '4', '数据库 - 编程分类子项', '0', '2019-02-27 14:31:17', '2019-04-07 22:20:23');
INSERT INTO `gw_menu` VALUES ('1021', '技术博客', 'essay/blog', '102', 'fa fa-tencent-weibo', '1', '技术博客 - 随笔子项', '0', '2019-02-27 14:31:17', '2019-04-07 22:20:25');
INSERT INTO `gw_menu` VALUES ('1022', '生活随笔', 'essay/essayEdit', '102', 'fa fa-pencil', '2', '生活随笔 - 随笔子项', '0', '2019-02-27 14:31:17', '2019-04-07 22:20:30');
INSERT INTO `gw_menu` VALUES ('100101', '用户管理22', 'sysMng/userMng22', '1001', 'fa fa-user', '1', '管理系统用户22，系统管理子项22', '0', '2019-02-27 14:31:17', '2019-04-07 22:20:19');
INSERT INTO `gw_menu` VALUES ('100201', '用户管理33', 'sysMng/userMng33', '1002', 'fa fa-user', '1', '管理系统用户33，系统管理子项33', '0', '2019-02-27 14:31:17', '2019-04-07 22:20:19');
INSERT INTO `gw_menu` VALUES ('10010101', '用户管理44', 'sysMng/userMng44', '100101', 'fa fa-user', '1', '管理系统用户44，系统管理子项44', '0', '2019-02-27 14:31:17', '2019-04-07 22:20:19');
INSERT INTO `gw_menu` VALUES ('10020101', '用户管理55', 'sysMng/userMng55', '100201', 'fa fa-user', '1', '管理系统用户55，系统管理子项55', '0', '2019-02-27 14:31:17', '2019-04-07 22:20:19');

-- ----------------------------
-- Table structure for gw_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `gw_menu_role`;
CREATE TABLE `gw_menu_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_id` int(10) NOT NULL COMMENT '菜单id',
  `role_id` int(10) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单与角色关联表';

-- ----------------------------
-- Records of gw_menu_role
-- ----------------------------
INSERT INTO `gw_menu_role` VALUES ('1', '100', '100');
INSERT INTO `gw_menu_role` VALUES ('2', '101', '100');
INSERT INTO `gw_menu_role` VALUES ('3', '102', '100');
INSERT INTO `gw_menu_role` VALUES ('4', '1001', '100');
INSERT INTO `gw_menu_role` VALUES ('5', '1002', '100');
INSERT INTO `gw_menu_role` VALUES ('6', '1003', '100');
INSERT INTO `gw_menu_role` VALUES ('7', '1004', '100');
INSERT INTO `gw_menu_role` VALUES ('8', '1005', '100');
INSERT INTO `gw_menu_role` VALUES ('9', '1006', '100');
INSERT INTO `gw_menu_role` VALUES ('10', '1011', '100');
INSERT INTO `gw_menu_role` VALUES ('11', '1012', '100');
INSERT INTO `gw_menu_role` VALUES ('12', '1013', '100');
INSERT INTO `gw_menu_role` VALUES ('13', '1014', '100');
INSERT INTO `gw_menu_role` VALUES ('14', '1021', '100');
INSERT INTO `gw_menu_role` VALUES ('15', '1022', '100');
INSERT INTO `gw_menu_role` VALUES ('16', '1022', '101');
INSERT INTO `gw_menu_role` VALUES ('17', '100101', '100');
INSERT INTO `gw_menu_role` VALUES ('18', '100201', '1001');
INSERT INTO `gw_menu_role` VALUES ('19', '10010101', '1001');
INSERT INTO `gw_menu_role` VALUES ('20', '10020101', '1001');

-- ----------------------------
-- Table structure for gw_role
-- ----------------------------
DROP TABLE IF EXISTS `gw_role`;
CREATE TABLE `gw_role` (
  `role_id` int(10) NOT NULL COMMENT '角色id',
  `role_name` varchar(20) NOT NULL COMMENT '角色名称',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '角色状态 0:正常  1:禁用',
  `remark` varchar(250) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统角色表';

-- ----------------------------
-- Records of gw_role
-- ----------------------------
INSERT INTO `gw_role` VALUES ('100', '超级管理员', '0', '拥有所有权限', '2019-02-27 14:18:01');
INSERT INTO `gw_role` VALUES ('101', '普通用户', '0', null, '2019-02-27 14:19:05');

-- ----------------------------
-- Table structure for gw_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `gw_role_permission`;
CREATE TABLE `gw_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ep_id` bigint(20) NOT NULL COMMENT '元素权限id',
  `role_id` int(10) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='元素权限与角色表';

-- ----------------------------
-- Records of gw_role_permission
-- ----------------------------
INSERT INTO `gw_role_permission` VALUES ('1', '1', '100');

-- ----------------------------
-- Table structure for gw_sys_param
-- ----------------------------
DROP TABLE IF EXISTS `gw_sys_param`;
CREATE TABLE `gw_sys_param` (
  `param_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `module_id` int(10) DEFAULT NULL COMMENT '模块id',
  `param_name` varchar(50) DEFAULT NULL COMMENT '配置项名称',
  `param_value` varchar(200) DEFAULT NULL COMMENT '参数值',
  `param_desc` varchar(1000) DEFAULT NULL COMMENT '配置项描述',
  PRIMARY KEY (`param_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统参数表';

-- ----------------------------
-- Records of gw_sys_param
-- ----------------------------
INSERT INTO `gw_sys_param` VALUES ('1000', null, '登录失败次数', '5', '登录失败超出限制次数时锁定账号');
INSERT INTO `gw_sys_param` VALUES ('1001', null, '登录失败，账号锁定时长', '30', '登录失败锁定账号后，等待30分钟后可解锁继续登录');

-- ----------------------------
-- Table structure for gw_user
-- ----------------------------
DROP TABLE IF EXISTS `gw_user`;
CREATE TABLE `gw_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `salt` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '盐',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '0' COMMENT '状态  0：正常  1：已锁定  2：禁用',
  `login_fail_num` int(11) DEFAULT '0' COMMENT '登录失败次数',
  `lock_stime` datetime DEFAULT NULL COMMENT '开始锁定时间',
  `lock_duration` int(11) DEFAULT '0' COMMENT '锁定时长(单位分钟)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  KEY `idx_mobile` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统用户';

-- ----------------------------
-- Records of gw_user
-- ----------------------------
INSERT INTO `gw_user` VALUES ('1', 'admin', 'c0c8033f3db154e03a3017bc265a0a268ee7f4c0b7a39c29ffaeee978d4c8a50', '9b3b36056db10f7a290b9d5c34ed89dc', '', '', '0', '0', '2019-02-26 10:11:32', '0', '2019-02-25 21:42:58', '2019-02-26 14:05:47');
INSERT INTO `gw_user` VALUES ('2', 'gordon', 'c0c8033f3db154e03a3017bc265a0a268ee7f4c0b7a39c29ffaeee978d4c8a50', '9b3b36056db10f7a290b9d5c34ed89dc', null, null, '0', '3', '2019-02-26 10:11:32', '0', '2019-02-25 21:42:58', '2019-02-27 23:13:13');

-- ----------------------------
-- Table structure for gw_user_role
-- ----------------------------
DROP TABLE IF EXISTS `gw_user_role`;
CREATE TABLE `gw_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` int(10) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户与角色关联表';

-- ----------------------------
-- Records of gw_user_role
-- ----------------------------
INSERT INTO `gw_user_role` VALUES ('1', '1', '100');
INSERT INTO `gw_user_role` VALUES ('2', '2', '101');
