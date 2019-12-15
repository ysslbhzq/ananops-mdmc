/*
 Navicat Premium Data Transfer

 Source Server         : mysql5.7
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : ananops_imc

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 11/12/2019 19:37:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for an_imc_device_order
-- ----------------------------
DROP TABLE IF EXISTS `an_imc_device_order`;
CREATE TABLE `an_imc_device_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `creator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `created_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `last_operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最近操作人',
  `last_operator_id` bigint(20) DEFAULT NULL COMMENT '最后操作人ID',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `inspection_item_id` bigint(20) DEFAULT NULL COMMENT '对应的巡检子项目的ID',
  `inspection_task_id` bigint(20) DEFAULT NULL COMMENT '对应的巡检任务的ID',
  `device_id` bigint(20) DEFAULT NULL COMMENT '设备的ID',
  `cost` decimal(10, 3) DEFAULT NULL COMMENT '当前订单花费',
  `device_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备类型',
  `manufacture` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设别生产商',
  `device_model` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备型号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 778265953178752001 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for an_imc_exception_log
-- ----------------------------
DROP TABLE IF EXISTS `an_imc_exception_log`;
CREATE TABLE `an_imc_exception_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '系统应用名',
  `exception_simple_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '异常类型',
  `exception_message` varchar(4500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '异常信息(通过exception.getMessage()获取到的内容)',
  `exception_cause` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '异常原因(通过exception.getCause()获取到的内容)',
  `exception_stack` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '异常堆栈信息',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '操作者姓名',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '操作者id',
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for an_imc_inspection_item
-- ----------------------------
DROP TABLE IF EXISTS `an_imc_inspection_item`;
CREATE TABLE `an_imc_inspection_item`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `creator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `created_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `last_operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最近操作人',
  `last_operator_id` bigint(20) DEFAULT NULL COMMENT '最后操作人ID',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `device_id` bigint(20) DEFAULT NULL COMMENT '被巡检的设备ID',
  `inspection_task_id` bigint(20) DEFAULT NULL COMMENT '从属的巡检任务的ID',
  `device_status` int(2) DEFAULT NULL COMMENT '被巡检的设备的状态（0.故障，1.正常）',
  `exception_description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '故障描述',
  `exception_level` int(5) DEFAULT NULL COMMENT '故障等级',
  `scheduled_start_time` datetime(0) DEFAULT NULL COMMENT '计划开始时间',
  `actual_start_time` datetime(0) DEFAULT NULL COMMENT '实际开始时间',
  `scheduled_finish_time` datetime(0) DEFAULT NULL COMMENT '计划完成时间',
  `actual_finish_time` datetime(0) DEFAULT NULL COMMENT '实际完成时间',
  `deadline` datetime(0) DEFAULT NULL COMMENT '最迟完成时间',
  `maintenance_task_id` bigint(20) DEFAULT NULL COMMENT '巡检产生的维修维护工单对应的id',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '巡检结果描述',
  `status` int(10) DEFAULT NULL COMMENT '巡检状态',
  `device_latitude` decimal(10, 6) DEFAULT NULL COMMENT '被巡检设备的位置，纬度',
  `device_longitude` decimal(10, 6) DEFAULT NULL COMMENT '被巡检设备的位置，经度',
  `device_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '被巡检设备的类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 778255304503596033 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for an_imc_inspection_item_info
-- ----------------------------
DROP TABLE IF EXISTS `an_imc_inspection_item_info`;
CREATE TABLE `an_imc_inspection_item_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `creator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `created_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `last_operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最近操作人',
  `last_operator_id` bigint(20) DEFAULT NULL COMMENT '最后操作人ID',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `project_id` bigint(20) DEFAULT NULL COMMENT '对应的项目ID',
  `device_id` bigint(20) DEFAULT NULL COMMENT '设备ID',
  `device_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设别类型',
  `device_latitude` decimal(10, 6) DEFAULT NULL COMMENT '设备位置，纬度',
  `device_longitude` decimal(10, 6) DEFAULT NULL COMMENT '设别位置，经度',
  `inspect_frequency` int(5) DEFAULT NULL COMMENT '合同上规定的巡检频率',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for an_imc_inspection_item_log
-- ----------------------------
DROP TABLE IF EXISTS `an_imc_inspection_item_log`;
CREATE TABLE `an_imc_inspection_item_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `creator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `created_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `last_operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最近操作人',
  `last_operator_id` bigint(20) DEFAULT NULL COMMENT '最后操作人ID',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `task_id` bigint(20) DEFAULT NULL COMMENT '对应的巡检任务ID',
  `status` int(10) DEFAULT NULL COMMENT '对应的状态类型',
  `status_timestamp` datetime(0) DEFAULT NULL COMMENT '对应的时间戳',
  `movement` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '当前操作的描述（有可能不存在）',
  `item_id` bigint(20) DEFAULT NULL COMMENT '对应的巡检任务子项ID',
  `os` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ip_address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 778265954730644481 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for an_imc_inspection_review
-- ----------------------------
DROP TABLE IF EXISTS `an_imc_inspection_review`;
CREATE TABLE `an_imc_inspection_review`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `creator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `created_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `last_operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最近操作人',
  `last_operator_id` bigint(20) DEFAULT NULL COMMENT '最后操作人ID',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `inspection_task_id` bigint(20) DEFAULT NULL COMMENT '对应的巡检任务ID',
  `principal_id` bigint(20) DEFAULT NULL COMMENT '项目负责人ID',
  `score` int(5) DEFAULT NULL COMMENT '服务评级',
  `contents` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '服务评论',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 778260522058388481 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for an_imc_inspection_task
-- ----------------------------
DROP TABLE IF EXISTS `an_imc_inspection_task`;
CREATE TABLE `an_imc_inspection_task`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `creator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `created_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `last_operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最近操作人',
  `last_operator_id` bigint(20) DEFAULT NULL COMMENT '最后操作人ID',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `principal_id` bigint(20) DEFAULT NULL COMMENT '项目负责人ID',
  `facilitator_id` bigint(20) DEFAULT NULL COMMENT '服务商ID',
  `project_id` bigint(20) DEFAULT NULL COMMENT '项目ID',
  `location` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '巡检位置信息',
  `status` int(10) DEFAULT NULL COMMENT '当前巡检任务进度',
  `total_cost` decimal(10, 3) DEFAULT NULL COMMENT '本次巡检总花费',
  `maintenance_cost` decimal(10, 3) DEFAULT NULL COMMENT '巡检产生的维修维护费用',
  `scheduled_start_time` datetime(0) DEFAULT NULL COMMENT '计划起始时间',
  `actual_start_time` datetime(0) DEFAULT NULL COMMENT '实际开始时间',
  `scheduled_finish_time` datetime(0) DEFAULT NULL COMMENT '计划完成时间',
  `actual_finish_time` datetime(0) DEFAULT NULL COMMENT '实际完成时间',
  `deadline` datetime(0) DEFAULT NULL COMMENT '最迟完成时间',
  `inspection_type` int(2) DEFAULT NULL COMMENT '巡检类型（1.按合同产生的巡检，2.甲方负责人主动发出的巡检）',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 779000623063370753 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for an_imc_inspection_task_log
-- ----------------------------
DROP TABLE IF EXISTS `an_imc_inspection_task_log`;
CREATE TABLE `an_imc_inspection_task_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `creator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `created_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `last_operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最近操作人',
  `last_operator_id` bigint(20) DEFAULT NULL COMMENT '最后操作人ID',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `task_id` bigint(20) DEFAULT NULL COMMENT '对应的巡检任务ID',
  `status` int(10) DEFAULT NULL COMMENT '当前任务状态',
  `status_timestamp` datetime(0) DEFAULT NULL COMMENT '操作对应的时间戳',
  `movement` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '当前操作对应的描述（有可能不存在）',
  `os` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ip_address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 779000642113899521 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for an_imc_maintenance_task
-- ----------------------------
DROP TABLE IF EXISTS `an_imc_maintenance_task`;
CREATE TABLE `an_imc_maintenance_task`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `creator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `created_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `last_operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最近操作人',
  `last_operator_id` bigint(20) DEFAULT NULL COMMENT '最后操作人ID',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `inspection_item_id` bigint(20) DEFAULT NULL COMMENT '对应的巡检子项目ID',
  `device_id` bigint(20) DEFAULT NULL COMMENT '巡检查出的故障设备的ID',
  `level` int(5) DEFAULT NULL COMMENT '故障等级',
  `device_latitude` decimal(10, 6) DEFAULT NULL COMMENT '故障设备的位置，纬度',
  `device_longitude` decimal(10, 6) DEFAULT NULL COMMENT '故障设备的位置，经度',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '故障描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for an_imc_permits_info
-- ----------------------------
DROP TABLE IF EXISTS `an_imc_permits_info`;
CREATE TABLE `an_imc_permits_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `creator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `created_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `last_operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最近操作人',
  `last_operator_id` bigint(20) DEFAULT NULL COMMENT '最后操作人ID',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `project_id` bigint(20) DEFAULT NULL COMMENT '项目ID',
  `permits_id` bigint(20) DEFAULT NULL COMMENT '证照ID',
  `permits_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '证照名称',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '证照描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for an_mq_message_data
-- ----------------------------
DROP TABLE IF EXISTS `an_mq_message_data`;
CREATE TABLE `an_mq_message_data`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `version` int(11) DEFAULT 0 COMMENT '版本号',
  `message_key` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '消息key',
  `message_topic` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT 'topic',
  `message_tag` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT 'tag',
  `message_body` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '消息内容',
  `message_type` int(11) DEFAULT 10 COMMENT '消息类型: 10 - 生产者 ; 20 - 消费者',
  `delay_level` int(11) DEFAULT 0 COMMENT '延时级别 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h',
  `order_type` int(11) DEFAULT 0 COMMENT '顺序类型 0有序 1无序',
  `status` int(11) DEFAULT 10 COMMENT '消息状态',
  `creator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `created_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最近操作人',
  `last_operator_id` bigint(20) DEFAULT NULL COMMENT '最后操作人ID',
  `update_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `yn` int(11) DEFAULT 0 COMMENT '是否删除 -0 未删除 -1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 779000623071759361 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for databasechangelog
-- ----------------------------
DROP TABLE IF EXISTS `databasechangelog`;
CREATE TABLE `databasechangelog`  (
  `ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `AUTHOR` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FILENAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DATEEXECUTED` datetime(0) NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MD5SUM` varchar(35) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `DESCRIPTION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `COMMENTS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `TAG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `LIQUIBASE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `CONTEXTS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `LABELS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for databasechangeloglock
-- ----------------------------
DROP TABLE IF EXISTS `databasechangeloglock`;
CREATE TABLE `databasechangeloglock`  (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime(0) DEFAULT NULL,
  `LOCKEDBY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
