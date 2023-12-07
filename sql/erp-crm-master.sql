/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : erp-crm-master

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2023-03-24 16:13:49
*/

SET
FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `master_tenant`
-- ----------------------------
DROP TABLE IF EXISTS `master_tenant`;
CREATE TABLE `master_tenant`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT,
    `tenant`          varchar(32)  DEFAULT NULL COMMENT '租户ID',
    `url`             varchar(255) DEFAULT NULL COMMENT '数据库连接URL',
    `username`        varchar(255) DEFAULT NULL COMMENT '用户名',
    `password`        varchar(255) DEFAULT NULL COMMENT '密码',
    `database_name`   varchar(64)  DEFAULT NULL COMMENT '数据库名',
    `host_name`       varchar(64)  DEFAULT NULL COMMENT '数据库主机名',
    `create_time`     datetime     DEFAULT NULL COMMENT '创建时间',
    `status`          char(1)      DEFAULT '1' COMMENT '状态(1正常 2停止)',
    `expiration_date` datetime     DEFAULT NULL COMMENT '到期日期',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `idx_tenant` (`tenant`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of master_tenant
-- ----------------------------
INSERT INTO `master_tenant`
VALUES ('33', 'tenant1',
        'jdbc:mysql://localhost:3306/ec_KWEbSrWI?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8',
        'ec_KWEbSrWI', '8CB7go3k', 'ec_KWEbSrWI', null, '2023-03-24 12:22:03', '1', '2028-03-31 12:22:03');

-- ----------------------------
-- Table structure for `qrtz_blob_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`
(
    `sched_name`    varchar(120) NOT NULL COMMENT '调度名称',
    `trigger_name`  varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
    `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
    `blob_data`     blob COMMENT '存放持久化Trigger对象',
    PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`),
    CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Blob类型的触发器表';

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_calendars`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`
(
    `sched_name`    varchar(120) NOT NULL COMMENT '调度名称',
    `calendar_name` varchar(200) NOT NULL COMMENT '日历名称',
    `calendar`      blob         NOT NULL COMMENT '存放持久化calendar对象',
    PRIMARY KEY (`sched_name`, `calendar_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日历信息表';

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_cron_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`
(
    `sched_name`      varchar(120) NOT NULL COMMENT '调度名称',
    `trigger_name`    varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
    `trigger_group`   varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
    `cron_expression` varchar(200) NOT NULL COMMENT 'cron表达式',
    `time_zone_id`    varchar(80) DEFAULT NULL COMMENT '时区',
    PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`),
    CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Cron类型的触发器表';

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_fired_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`
(
    `sched_name`        varchar(120) NOT NULL COMMENT '调度名称',
    `entry_id`          varchar(95)  NOT NULL COMMENT '调度器实例id',
    `trigger_name`      varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
    `trigger_group`     varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
    `instance_name`     varchar(200) NOT NULL COMMENT '调度器实例名',
    `fired_time`        bigint(13) NOT NULL COMMENT '触发的时间',
    `sched_time`        bigint(13) NOT NULL COMMENT '定时器制定的时间',
    `priority`          int(11) NOT NULL COMMENT '优先级',
    `state`             varchar(16)  NOT NULL COMMENT '状态',
    `job_name`          varchar(200) DEFAULT NULL COMMENT '任务名称',
    `job_group`         varchar(200) DEFAULT NULL COMMENT '任务组名',
    `is_nonconcurrent`  varchar(1)   DEFAULT NULL COMMENT '是否并发',
    `requests_recovery` varchar(1)   DEFAULT NULL COMMENT '是否接受恢复执行',
    PRIMARY KEY (`sched_name`, `entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='已触发的触发器表';

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_job_details`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`
(
    `sched_name`        varchar(120) NOT NULL COMMENT '调度名称',
    `job_name`          varchar(200) NOT NULL COMMENT '任务名称',
    `job_group`         varchar(200) NOT NULL COMMENT '任务组名',
    `description`       varchar(250) DEFAULT NULL COMMENT '相关介绍',
    `job_class_name`    varchar(250) NOT NULL COMMENT '执行任务类名称',
    `is_durable`        varchar(1)   NOT NULL COMMENT '是否持久化',
    `is_nonconcurrent`  varchar(1)   NOT NULL COMMENT '是否并发',
    `is_update_data`    varchar(1)   NOT NULL COMMENT '是否更新数据',
    `requests_recovery` varchar(1)   NOT NULL COMMENT '是否接受恢复执行',
    `job_data`          blob COMMENT '存放持久化job对象',
    PRIMARY KEY (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务详细信息表';

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_locks`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`
(
    `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
    `lock_name`  varchar(40)  NOT NULL COMMENT '悲观锁名称',
    PRIMARY KEY (`sched_name`, `lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储的悲观锁信息表';

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_paused_trigger_grps`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`
(
    `sched_name`    varchar(120) NOT NULL COMMENT '调度名称',
    `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
    PRIMARY KEY (`sched_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='暂停的触发器表';

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_scheduler_state`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`
(
    `sched_name`        varchar(120) NOT NULL COMMENT '调度名称',
    `instance_name`     varchar(200) NOT NULL COMMENT '实例名称',
    `last_checkin_time` bigint(13) NOT NULL COMMENT '上次检查时间',
    `checkin_interval`  bigint(13) NOT NULL COMMENT '检查间隔时间',
    PRIMARY KEY (`sched_name`, `instance_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='调度器状态表';

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_simple_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`
(
    `sched_name`      varchar(120) NOT NULL COMMENT '调度名称',
    `trigger_name`    varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
    `trigger_group`   varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
    `repeat_count`    bigint(7) NOT NULL COMMENT '重复的次数统计',
    `repeat_interval` bigint(12) NOT NULL COMMENT '重复的间隔时间',
    `times_triggered` bigint(10) NOT NULL COMMENT '已经触发的次数',
    PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`),
    CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='简单触发器的信息表';

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_simprop_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`
(
    `sched_name`    varchar(120) NOT NULL COMMENT '调度名称',
    `trigger_name`  varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
    `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
    `str_prop_1`    varchar(512)   DEFAULT NULL COMMENT 'String类型的trigger的第一个参数',
    `str_prop_2`    varchar(512)   DEFAULT NULL COMMENT 'String类型的trigger的第二个参数',
    `str_prop_3`    varchar(512)   DEFAULT NULL COMMENT 'String类型的trigger的第三个参数',
    `int_prop_1`    int(11) DEFAULT NULL COMMENT 'int类型的trigger的第一个参数',
    `int_prop_2`    int(11) DEFAULT NULL COMMENT 'int类型的trigger的第二个参数',
    `long_prop_1`   bigint(20) DEFAULT NULL COMMENT 'long类型的trigger的第一个参数',
    `long_prop_2`   bigint(20) DEFAULT NULL COMMENT 'long类型的trigger的第二个参数',
    `dec_prop_1`    decimal(13, 4) DEFAULT NULL COMMENT 'decimal类型的trigger的第一个参数',
    `dec_prop_2`    decimal(13, 4) DEFAULT NULL COMMENT 'decimal类型的trigger的第二个参数',
    `bool_prop_1`   varchar(1)     DEFAULT NULL COMMENT 'Boolean类型的trigger的第一个参数',
    `bool_prop_2`   varchar(1)     DEFAULT NULL COMMENT 'Boolean类型的trigger的第二个参数',
    PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`),
    CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='同步机制的行锁表';

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`
(
    `sched_name`     varchar(120) NOT NULL COMMENT '调度名称',
    `trigger_name`   varchar(200) NOT NULL COMMENT '触发器的名字',
    `trigger_group`  varchar(200) NOT NULL COMMENT '触发器所属组的名字',
    `job_name`       varchar(200) NOT NULL COMMENT 'qrtz_job_details表job_name的外键',
    `job_group`      varchar(200) NOT NULL COMMENT 'qrtz_job_details表job_group的外键',
    `description`    varchar(250) DEFAULT NULL COMMENT '相关介绍',
    `next_fire_time` bigint(13) DEFAULT NULL COMMENT '上一次触发时间（毫秒）',
    `prev_fire_time` bigint(13) DEFAULT NULL COMMENT '下一次触发时间（默认为-1表示不触发）',
    `priority`       int(11) DEFAULT NULL COMMENT '优先级',
    `trigger_state`  varchar(16)  NOT NULL COMMENT '触发器状态',
    `trigger_type`   varchar(8)   NOT NULL COMMENT '触发器的类型',
    `start_time`     bigint(13) NOT NULL COMMENT '开始时间',
    `end_time`       bigint(13) DEFAULT NULL COMMENT '结束时间',
    `calendar_name`  varchar(200) DEFAULT NULL COMMENT '日程表名称',
    `misfire_instr`  smallint(2) DEFAULT NULL COMMENT '补偿执行的策略',
    `job_data`       blob COMMENT '存放持久化job对象',
    PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`),
    KEY              `sched_name` (`sched_name`,`job_name`,`job_group`),
    CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='触发器详细信息表';

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_logininfor`
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`
(
    `info_id`        bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
    `user_name`      varchar(50)  DEFAULT '' COMMENT '用户账号',
    `ipaddr`         varchar(128) DEFAULT '' COMMENT '登录IP地址',
    `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
    `browser`        varchar(50)  DEFAULT '' COMMENT '浏览器类型',
    `os`             varchar(50)  DEFAULT '' COMMENT '操作系统',
    `status`         char(1)      DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
    `msg`            varchar(255) DEFAULT '' COMMENT '提示消息',
    `login_time`     datetime     DEFAULT NULL COMMENT '访问时间',
    PRIMARY KEY (`info_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=312 DEFAULT CHARSET=utf8mb4 COMMENT='系统访问记录';

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor`
VALUES ('291', 'superAdmin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码已失效', '2023-02-26 19:58:26');
INSERT INTO `sys_logininfor`
VALUES ('292', 'superAdmin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码已失效', '2023-02-26 19:58:28');
INSERT INTO `sys_logininfor`
VALUES ('293', 'superAdmin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码已失效', '2023-02-26 20:06:56');
INSERT INTO `sys_logininfor`
VALUES ('294', 'superAdmin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2023-02-26 20:07:05');
INSERT INTO `sys_logininfor`
VALUES ('295', 'superAdmin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2023-02-26 20:07:11');
INSERT INTO `sys_logininfor`
VALUES ('296', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2023-02-26 20:11:33');
INSERT INTO `sys_logininfor`
VALUES ('297', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码已失效', '2023-02-26 20:48:31');
INSERT INTO `sys_logininfor`
VALUES ('298', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2023-02-26 20:51:29');
INSERT INTO `sys_logininfor`
VALUES ('299', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码已失效', '2023-02-27 11:07:57');
INSERT INTO `sys_logininfor`
VALUES ('300', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2023-02-27 17:55:50');
INSERT INTO `sys_logininfor`
VALUES ('301', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2023-03-03 11:20:00');
INSERT INTO `sys_logininfor`
VALUES ('302', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2023-03-03 11:20:55');
INSERT INTO `sys_logininfor`
VALUES ('303', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2023-03-03 14:47:25');
INSERT INTO `sys_logininfor`
VALUES ('304', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码已失效', '2023-03-06 10:21:03');
INSERT INTO `sys_logininfor`
VALUES ('305', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2023-03-07 11:39:35');
INSERT INTO `sys_logininfor`
VALUES ('306', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2023-03-07 19:04:26');
INSERT INTO `sys_logininfor`
VALUES ('307', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码已失效', '2023-03-14 15:56:36');
INSERT INTO `sys_logininfor`
VALUES ('308', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码已失效', '2023-03-24 14:39:05');
INSERT INTO `sys_logininfor`
VALUES ('309', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2023-03-24 14:41:05');
INSERT INTO `sys_logininfor`
VALUES ('310', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码已失效', '2023-03-24 14:44:18');
INSERT INTO `sys_logininfor`
VALUES ('311', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码已失效', '2023-03-24 14:55:03');

-- ----------------------------
-- Table structure for `sys_oper_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`
(
    `oper_id`        bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
    `title`          varchar(50)   DEFAULT '' COMMENT '模块标题',
    `business_type`  int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
    `method`         varchar(100)  DEFAULT '' COMMENT '方法名称',
    `request_method` varchar(10)   DEFAULT '' COMMENT '请求方式',
    `operator_type`  int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
    `oper_name`      varchar(50)   DEFAULT '' COMMENT '操作人员',
    `dept_name`      varchar(50)   DEFAULT '' COMMENT '部门名称',
    `oper_url`       varchar(255)  DEFAULT '' COMMENT '请求URL',
    `oper_ip`        varchar(128)  DEFAULT '' COMMENT '主机地址',
    `oper_location`  varchar(255)  DEFAULT '' COMMENT '操作地点',
    `oper_param`     varchar(2000) DEFAULT '' COMMENT '请求参数',
    `json_result`    varchar(2000) DEFAULT '' COMMENT '返回参数',
    `status`         int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
    `error_msg`      varchar(2000) DEFAULT '' COMMENT '错误消息',
    `oper_time`      datetime      DEFAULT NULL COMMENT '操作时间',
    PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作日志记录';

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
