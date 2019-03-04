
-- 系统参数表
drop table if exists `gw_sys_param`;
create table `gw_sys_param`(
  `param_id` bigint not null auto_increment,
  `module_id` int(10) default null comment '模块id',
  `param_name` varchar(50) default null comment '配置项名称',
  `param_value` varchar(200) default null comment '参数值',
  `param_desc` varchar(1000) default null comment '配置项描述',
  primary key (`param_id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT='系统参数表';

-- 系统用户
DROP TABLE IF EXISTS `gw_user`;
CREATE TABLE `gw_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4  collate utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) not null COMMENT '密码',
  `salt` varchar(20) COMMENT '盐',
  `email` varchar(100) CHARACTER SET utf8mb4  collate utf8mb4_general_ci COMMENT '邮箱',
  `mobile` varchar(100) COMMENT '手机号',
  `status` varchar(2) default '0' COMMENT '状态  0：正常  1：已锁定  2：禁用',
  `login_fail_num` int default 0 comment '登录失败次数',
  `lock_stime` datetime comment '开始锁定时间',
  `lock_duration` int default 30 comment '锁定时长(单位分钟)',
  `create_time` datetime default current_timestamp COMMENT '创建时间',
  `update_time` datetime default current_timestamp on update current_timestamp comment '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX (`username`),
  KEY `idx_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT='系统用户';


-- 系统日志
DROP TABLE IF EXISTS `gw_log`;
CREATE TABLE `gw_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userid` varchar(50) COMMENT '用户id',
  `operation` varchar(50) COMMENT '用户操作',
  `clazz` varchar(50) comment '请求的类',
  `method` varchar(200) COMMENT '请求方法',
  `params` varchar(5000) COMMENT '请求参数',
	`ex_msg` longtext COMMENT '异常信息',
  `time` bigint NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) COMMENT 'IP地址',
  `create_date` datetime default current_timestamp COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY (`clazz`,`method`),
  index `idx_userid` (`userid`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT='系统日志';

-- 数据字典
DROP TABLE IF EXISTS `gw_dict`;
CREATE TABLE `gw_dict` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名称',
  `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典类型',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典码',
  `value` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典值',
  `order_num` int DEFAULT 0 COMMENT '排序',
  `remark` varchar(255) COMMENT '备注',
  `status` varchar(2) DEFAULT '0' COMMENT '字典状态 0：正常  1：禁用',
  PRIMARY KEY (`id`),
  UNIQUE KEY(`type`,`code`)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT='数据字典表';

-- 系统菜单
drop table if exists `gw_menu`;
create table `gw_menu`(
  `menu_id` int(10) not null comment '菜单id',
  `menu_name` varchar(50) character  set utf8mb4 collate utf8mb4_general_ci not null comment '菜单名称',
  `url` varchar(250) default null comment '菜单链接',
  `parent_id` int(10) default null comment '父菜单id',
  `icon` varchar(50) default null comment '菜单图标',
  `order_num` int(10) default null comment '菜单排序序号',
  `menu_desc` varchar(100) default null comment '菜单描述',
  `status` varchar(2) default '0' comment '菜单状态 0:正常 1:禁用',
  `create_time` datetime default current_timestamp COMMENT '创建时间',
  `update_time` datetime default current_timestamp on update current_timestamp comment '更新时间',
  primary key (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT='系统菜单表';

-- 系统角色
drop table if exists `gw_role`;
create table `gw_role`(
  `role_id` int(10) not null comment '角色id',
  `role_name` varchar(20) not null comment '角色名称',
  `status` varchar(2) default '0' not null comment '角色状态 0:正常  1:禁用',
  `remark` varchar(250) default null comment '备注',
  `create_time` datetime default current_timestamp comment '创建时间',
  primary key (`role_id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT='系统角色表';

-- 用户与角色
drop table if exists `gw_user_role`;
create table `gw_user_role`(
  `id` bigint not null AUTO_INCREMENT,
  `user_id` bigint not null comment '用户id',
  `role_id` int(10) not null comment '角色id',
  primary key (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT='用户与角色关联表';

-- 角色与菜单
drop table if exists `gw_menu_role`;
create table `gw_menu_role`(
  `id` bigint not null AUTO_INCREMENT,
  `menu_id` int(10) not null comment '菜单id',
  `role_id` int(10) not null comment '角色id',
  primary key (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT='菜单与角色关联表';

-- 页面元素权限表
drop table if exists `gw_element_permission`;
create table `gw_element_permission`(
  `ep_id` bigint not null AUTO_INCREMENT,
  `name` varchar(50) default null comment '元素名称',
  `permission_name` varchar(50) default null comment '元素权限名称',
  `icon` varchar(50) default null comment '元素图标',
  `url` varchar(1200) default null comment '元素对应的url',
  `type` varchar(2) default '0' comment '元素类型 0:菜单 1:按钮  2:图片  3:文件',
  `own_page` varchar(200) default null comment '所属页面',
  `parent_id` bigint default null comment '父节点',
  `status` varchar(2) default '0' comment '元素状态 0:正常  1:禁用',
  `remark` varchar(200) default null comment '备注',
  primary key (`ep_id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT='页面元素权限表';

-- 元素权限与角色表
drop table if exists `gw_role_permission`;
create table `gw_role_permission`(
  `id` bigint not null AUTO_INCREMENT,
  `ep_id` bigint not null comment '元素权限id',
  `role_id` int(10) not null comment '角色id',
  primary key (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT='元素权限与角色表';



























