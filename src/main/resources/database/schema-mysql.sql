/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : fota-jpa

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2018-11-23 15:10:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `auth_brand`
-- ----------------------------
DROP TABLE IF EXISTS `auth_brand`;
CREATE TABLE `auth_brand` (
  `sid` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `del_flg` int(11) DEFAULT NULL,
  `update_by` varchar(36) DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_brand
-- ----------------------------

-- ----------------------------
-- Table structure for `auth_data_dic`
-- ----------------------------
DROP TABLE IF EXISTS `auth_data_dic`;
CREATE TABLE `auth_data_dic` (
  `sid` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `del_flg` int(11) DEFAULT NULL,
  `update_by` varchar(36) DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `data_key` varchar(255) DEFAULT NULL,
  `data_sort` int(11) DEFAULT NULL,
  `data_value` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `service` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_data_dic
-- ----------------------------

-- ----------------------------
-- Table structure for `auth_dict`
-- ----------------------------
DROP TABLE IF EXISTS `auth_dict`;
CREATE TABLE `auth_dict` (
  `sid` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `del_flg` int(11) DEFAULT NULL,
  `update_by` varchar(36) DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `level_code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `value` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`sid`),
  UNIQUE KEY `UK_hobr1xqtikrnstx0kfoik81y7` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_dict
-- ----------------------------

-- ----------------------------
-- Table structure for `auth_log`
-- ----------------------------
DROP TABLE IF EXISTS `auth_log`;
CREATE TABLE `auth_log` (
  `sid` varchar(36) NOT NULL,
  `exception` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `operate_date` datetime DEFAULT NULL,
  `params` varchar(255) DEFAULT NULL,
  `remote_addr` varchar(255) DEFAULT NULL,
  `request_uri` varchar(255) DEFAULT NULL,
  `timeout` bigint(20) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` bit(1) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_log
-- ----------------------------

-- ----------------------------
-- Table structure for `auth_org`
-- ----------------------------
DROP TABLE IF EXISTS `auth_org`;
CREATE TABLE `auth_org` (
  `sid` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `del_flg` int(11) DEFAULT NULL,
  `update_by` varchar(36) DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `level_code` varchar(255) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `org_type` int(11) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`sid`),
  UNIQUE KEY `UK_51kds73bvyanyedfml8ssmybn` (`code`) USING BTREE,
  UNIQUE KEY `UK_e5opvas5tr1kwqondjf5xwllw` (`level_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_org
-- ----------------------------

-- ----------------------------
-- Table structure for `auth_org_brand`
-- ----------------------------
DROP TABLE IF EXISTS `auth_org_brand`;
CREATE TABLE `auth_org_brand` (
  `id` varchar(255) NOT NULL,
  `brand_sid` varchar(36) DEFAULT NULL,
  `org_sid` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnebi9jp02dsxgh728et41peun` (`brand_sid`) USING BTREE,
  KEY `FK85xs3od553kgjonytnh9nn7lg` (`org_sid`) USING BTREE,
  CONSTRAINT `auth_org_brand_ibfk_1` FOREIGN KEY (`org_sid`) REFERENCES `auth_org` (`sid`),
  CONSTRAINT `auth_org_brand_ibfk_2` FOREIGN KEY (`brand_sid`) REFERENCES `auth_brand` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_org_brand
-- ----------------------------

-- ----------------------------
-- Table structure for `auth_project`
-- ----------------------------
DROP TABLE IF EXISTS `auth_project`;
CREATE TABLE `auth_project` (
  `sid` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `del_flg` int(11) DEFAULT NULL,
  `update_by` varchar(36) DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `brand_sid` varchar(36) DEFAULT NULL,
  `car_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`sid`),
  UNIQUE KEY `UK_skt8judhgncfg4pj8wvoeagnq` (`code`) USING BTREE,
  KEY `FK32nubnuns07up499mdnmufkjg` (`brand_sid`) USING BTREE,
  CONSTRAINT `auth_project_ibfk_1` FOREIGN KEY (`brand_sid`) REFERENCES `auth_brand` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_project
-- ----------------------------

-- ----------------------------
-- Table structure for `auth_resource`
-- ----------------------------
DROP TABLE IF EXISTS `auth_resource`;
DROP TABLE IF EXISTS `auth_resource`;
CREATE TABLE `auth_resource` (
  `sid` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `del_flg` int(11) DEFAULT NULL,
  `update_by` varchar(36) DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `code` varchar(10) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `level_code` varchar(255) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `sort` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `parent_sid` varchar(36) DEFAULT NULL,
  `levelCode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sid`),
  UNIQUE KEY `UK_8b1jeukpbivcccy8tfkl4a1ih` (`code`),
  UNIQUE KEY `UK_jte7ti4eh5ps0ymjiinjfbnvy` (`level_code`),
  UNIQUE KEY `UK_bijq6if0v1yhvtn5f09v4oa71` (`levelCode`),
  KEY `FKe313i07lyjwbfryemfufy9s1u` (`parent_sid`),
  CONSTRAINT `FKe313i07lyjwbfryemfufy9s1u` FOREIGN KEY (`parent_sid`) REFERENCES `auth_resource` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_resource
-- ----------------------------

-- ----------------------------
-- Table structure for `auth_role`
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role` (
  `sid` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `del_flg` int(11) DEFAULT NULL,
  `update_by` varchar(36) DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `level_code` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sid`),
  UNIQUE KEY `UK_k8dhmc0q3l9h59gv9gjeokib0` (`code`),
  UNIQUE KEY `UK_j7khp412jjls60uopbl1aj4k4` (`level_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_role
-- ----------------------------

-- ----------------------------
-- Table structure for `auth_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_resource`;
CREATE TABLE `auth_role_resource` (
  `id` varchar(255) NOT NULL,
  `resource_sid` varchar(36) DEFAULT NULL,
  `role_sid` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8j93139l661mcmqhvr9tdfd4g` (`resource_sid`) USING BTREE,
  KEY `FKnr1f11awx7wob7ve5wycx2e10` (`role_sid`) USING BTREE,
  CONSTRAINT `auth_role_resource_ibfk_1` FOREIGN KEY (`resource_sid`) REFERENCES `auth_resource` (`sid`),
  CONSTRAINT `auth_role_resource_ibfk_2` FOREIGN KEY (`role_sid`) REFERENCES `auth_role` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for `auth_role_user`
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_user`;
CREATE TABLE `auth_role_user` (
  `id` varchar(255) NOT NULL,
  `role_sid` varchar(36) DEFAULT NULL,
  `user_sid` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbwjldr5818bhg97l82iv5ji4m` (`role_sid`) USING BTREE,
  KEY `FKqg2j7jgjsrd81kyac3jktmh29` (`user_sid`) USING BTREE,
  CONSTRAINT `auth_role_user_ibfk_1` FOREIGN KEY (`role_sid`) REFERENCES `auth_role` (`sid`),
  CONSTRAINT `auth_role_user_ibfk_2` FOREIGN KEY (`user_sid`) REFERENCES `auth_user` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_role_user
-- ----------------------------

-- ----------------------------
-- Table structure for `auth_user`
-- ----------------------------
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user` (
  `sid` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `del_flg` int(11) DEFAULT NULL,
  `update_by` varchar(36) DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `account_lock_count` int(11) NOT NULL,
  `active` bit(1) NOT NULL,
  `expire_time` datetime DEFAULT NULL,
  `lead_in` bit(1) NOT NULL,
  `locked` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `org_sid` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`sid`),
  UNIQUE KEY `UK_t1iph3dfc25ukwcl9xemtnojn` (`username`) USING BTREE,
  KEY `FK4cvl9whw130oc6s45k0f43lm5` (`org_sid`) USING BTREE,
  CONSTRAINT `auth_user_ibfk_1` FOREIGN KEY (`org_sid`) REFERENCES `auth_org` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_user
-- ----------------------------
-- 2019-1-18


