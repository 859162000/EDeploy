/*
Navicat MySQL Data Transfer

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2017-03-13 02:11:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for deploy_config
-- ----------------------------
DROP TABLE IF EXISTS `deploy_config`;
CREATE TABLE `deploy_config` (
  `config_id` bigint(20) NOT NULL,
  `config_name` varchar(100) DEFAULT NULL,
  `project_id` bigint(20) NOT NULL,
  `ip_hash` char(1) DEFAULT 'Y' COMMENT '是否启用IP_HASH',
  `status` tinyint(1) DEFAULT '1',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目部署定义，每个定义包含至少一个detail，如果有多个相当于是负载均衡';

-- ----------------------------
-- Table structure for deploy_config_detail_relat
-- ----------------------------
DROP TABLE IF EXISTS `deploy_config_detail_relat`;
CREATE TABLE `deploy_config_detail_relat` (
  `relat_id` bigint(20) NOT NULL,
  `config_id` bigint(20) NOT NULL,
  `detail_id` bigint(20) NOT NULL,
  `status` tinyint(1) DEFAULT '1',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`relat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for deploy_detail
-- ----------------------------
DROP TABLE IF EXISTS `deploy_detail`;
CREATE TABLE `deploy_detail` (
  `detail_id` bigint(20) NOT NULL,
  `public_ip` varchar(30) NOT NULL,
  `local_ip` varchar(30) NOT NULL,
  `port` int(11) DEFAULT NULL,
  `weight` tinyint(4) DEFAULT '0' COMMENT '负载均衡的权重',
  `max_fails` tinyint(4) DEFAULT '2' COMMENT '失败重试次数',
  `fail_timeout` tinyint(4) DEFAULT '5' COMMENT '单位：秒',
  `status` tinyint(1) DEFAULT '1',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for deploy_project
-- ----------------------------
DROP TABLE IF EXISTS `deploy_project`;
CREATE TABLE `deploy_project` (
  `project_id` bigint(20) NOT NULL,
  `project_name` varchar(100) NOT NULL,
  `upstream` varchar(20) NOT NULL,
  `status` tinyint(1) DEFAULT '1',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部署项目定义，每个项目有多个config定义，用来满足不同的发布需求，每次有且仅有一个config是有效的';
