-- 系统用户
DROP TABLE IF EXISTS `gw_user`;
CREATE TABLE `gw_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4  collate utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(100) not null COMMENT '密码',
  `salt` varchar(20) COMMENT '盐',
  `email` varchar(100) CHARACTER SET utf8mb4  collate utf8mb4_bin COMMENT '邮箱',
  `mobile` varchar(100) COMMENT '手机号',
  `status` varchar(2) default '0' COMMENT '状态  0：正常  1：已锁定  2：禁用',
  `login_fail_num` int default 0 comment '登录失败次数',
  `lock_stime` datetime comment '开始锁定时间',
  `lock_duration` int default 0 comment '锁定时长(单位分钟)',
  `create_time` datetime default current_timestamp COMMENT '创建时间',
  `update_time` datetime default current_timestamp on update current_timestamp comment '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX (`username`),
  KEY `idx_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT='系统用户';


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
  UNIQUE KEY (`class`,`method`),
  index `idx_userid` (`userid`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT='系统日志';

-- 数据字典
DROP TABLE IF EXISTS `gw_dict`;
CREATE TABLE `gw_dict` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '字典名称',
  `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '字典类型',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '字典码',
  `value` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '字典值',
  `order_num` int DEFAULT 0 COMMENT '排序',
  `remark` varchar(255) COMMENT '备注',
  `del_flag` tinyint DEFAULT 0 COMMENT '删除标记  0：正常  1：已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY(`type`,`code`)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT='数据字典表';










