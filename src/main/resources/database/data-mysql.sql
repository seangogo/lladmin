/*
Navicat MySQL Data Transfer

Source Server         : fota-sit
Source Server Version : 50720
Source Host           : 172.16.5.18:3306
Source Database       : fota

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-11-23 14:27:21
*/

-- --------------------------------------------品牌-------------------------------------------------------------------------------------------------------------
INSERT INTO `auth_brand` VALUES ('35d19538-999c-4b8f-9e5b-01b00488ce41', 'Sysadmin', '2018-08-06 16:30:21', '1', 'Sysadmin', '2018-09-17 16:34:03', '1', 'BJEV', null, '北新', '北京新能源');
-- --------------------------------------------组织-------------------------------------------------------------------------------------------------------------
INSERT INTO `auth_org` VALUES ('9eeeb2e0-07e3-40b4-9d90-46a7ee2faa45', '', '2018-08-03 17:53:08', '0', '', '2018-08-03 17:53:08', '0', 'pateo', 'pateo', '博泰', '0', '0', '根组织');
-- --------------------------------------------品牌组织关联-------------------------------------------------------------------------------------------------------------
INSERT INTO `auth_org_brand` VALUES ('0f7dd06e-f4a6-4c78-9570-72731f83a2c6', '35d19538-999c-4b8f-9e5b-01b00488ce41', '9eeeb2e0-07e3-40b4-9d90-46a7ee2faa45');
-- --------------------------------------------项目-------------------------------------------------------------------------------------------------------------
INSERT INTO `auth_project` VALUES ('5e6658c8-c5a8-4a10-ab00-ff64fce627c6', '', '2018-08-03 17:53:10', '0', 'Sysadmin', '2018-09-25 10:35:04', '3', 'bxbe21', '项目名称Be21', '北新项目Be21', '9eeeb2e0-07e3-40b4-9d90-46a7ee2faa45', '0');
-- --------------------------------------------资源-------------------------------------------------------------------------------------------------------------
INSERT INTO `auth_resource` VALUES ('03c0a25a-bb1b-4561-972a-aa5bf3dd583a', 'initialize', '2018-10-11 15:29:53', '0', '', '2018-10-11 15:29:53', '0', 'role', 'jiaosequnti', 'root-rbac-role', '角色管理', '角色管理角色管理', '3', '0', '9a593daf-ce60-434f-bdbc-87bb9c958bfd', null);
INSERT INTO `auth_resource` VALUES ('06df1a8d-171c-42ba-83bc-1dae12cdb565', 'initialize', '2018-10-11 15:29:53', '0', 'Sysadmin', '2018-10-18 10:35:51', '3', 'dataDic', 'shujuzidianguanli', 'root-system-dataDic', '数据字典', '角色管理角色管理', '9', '0', 'c683ff84-76eb-48bf-8155-b9d0c97f88e7', null);
INSERT INTO `auth_resource` VALUES ('0eb3c198-8b83-48cc-ad2c-583c05b5d576', 'initialize', '2018-10-11 15:29:53', '0', '', '2018-10-11 15:29:53', '0', 'resource', 'ziyuan-xianxing', 'root-rbac-resource', '资源管理', '角色管理角色管理', '2', '0', '9a593daf-ce60-434f-bdbc-87bb9c958bfd', null);
INSERT INTO `auth_resource` VALUES ('111c7a72-1cbb-4c3e-bb70-be8c3310954a', 'initialize', '2018-10-11 15:29:53', '0', '', '2018-10-11 15:29:53', '0', 'user', 'yonghu-xianxing', 'root-system-user', '用户管理', '角色管理角色管理', '5', '0', '5df5c7f5-d645-4ff8-b958-8c0b10cd5f07', null);
INSERT INTO `auth_resource` VALUES ('23581f1f-3b91-4ffe-863a-dd92720be45f', 'initialize', '2018-10-11 15:29:53', '0', 'Sysadmin', '2018-10-18 10:36:03', '3', 'brand', 'pinpai1', 'root-system-brand', '品牌管理', '角色管理角色管理', '8', '0', 'c683ff84-76eb-48bf-8155-b9d0c97f88e7', null);
INSERT INTO `auth_resource` VALUES ('5df5c7f5-d645-4ff8-b958-8c0b10cd5f07', 'initialize', '2018-10-11 15:29:53', '0', 'Sysadmin', '2018-11-09 16:10:37', '1', 'system', 'shezhi-xianxing', 'root-system', '系统管理', '角色管理角色管理', '1', '0', 'b4a245a6-ce70-408a-9fbc-f9d03670c810', null);
INSERT INTO `auth_resource` VALUES ('5ee477c8-bcde-441c-8995-7f55b79f2229', 'Sysadmin', '2018-10-15 11:11:00', '0', 'Sysadmin', '2018-10-17 13:58:28', '3', 'role_opt', null, 'root-rbac-role-role_opt', '角色操作', '角色操作权限', '11', '1', '03c0a25a-bb1b-4561-972a-aa5bf3dd583a', null);
INSERT INTO `auth_resource` VALUES ('698d0ac1-fb12-4a28-acac-a6b63f219da1', 'initialize', '2018-10-11 15:29:53', '0', 'Sysadmin', '2018-10-18 10:36:11', '1', 'project', 'xiangmuguanli', 'root-system-project', '项目管理', '角色管理角色管理', '7', '0', 'c683ff84-76eb-48bf-8155-b9d0c97f88e7', null);
INSERT INTO `auth_resource` VALUES ('7be7d26f-9b6b-41e2-a796-caab3c4778ad', 'initialize', '2018-10-11 15:29:53', '0', '', '2018-10-11 15:29:53', '0', 'org', 'zuzhijiagoujiekou', 'root-system-org', '组织管理', '角色管理角色管理', '6', '0', '5df5c7f5-d645-4ff8-b958-8c0b10cd5f07', null);
INSERT INTO `auth_resource` VALUES ('9a593daf-ce60-434f-bdbc-87bb9c958bfd', 'initialize', '2018-10-11 15:29:53', '0', 'Sysadmin', '2018-11-09 16:10:37', '1', 'rbac', 'quanxianyuechi-xianxing', 'root-rbac', '权限管理', '角色管理角色管理', '4', '0', 'b4a245a6-ce70-408a-9fbc-f9d03670c810', null);
INSERT INTO `auth_resource` VALUES ('9fbe52b0-811e-40bf-9e2c-8ac700aabd6f', 'initialize', '2018-10-11 15:29:53', '0', 'Sysadmin', '2018-10-12 16:44:01', '4', 'log', 'xitongrizhiguanli', 'root-system-log', '日志查询', '角色管理角色管理', '10', '0', '5df5c7f5-d645-4ff8-b958-8c0b10cd5f07', null);
INSERT INTO `auth_resource` VALUES ('b4a245a6-ce70-408a-9fbc-f9d03670c810', 'initialize', '2018-10-11 15:29:53', '0', '', '2018-10-11 15:29:53', '0', 'root', null, 'root', '根节点', '角色管理角色管理', '0', '0', null, null);
INSERT INTO `auth_resource` VALUES ('c683ff84-76eb-48bf-8155-b9d0c97f88e7', 'Sysadmin', '2018-10-18 10:35:28', '0', 'Sysadmin', '2018-10-18 10:35:28', '0', 'base', 'jichushujuguanli', 'root-base', '基础数据管理', '基础数据管理', '13', '0', 'b4a245a6-ce70-408a-9fbc-f9d03670c810', null);
INSERT INTO `auth_resource` VALUES ('cd5521b8-e538-414c-b7d4-cc032b46bf3a', 'Sysadmin', '2018-10-15 11:37:43', '0', 'Sysadmin', '2018-10-15 11:37:43', '0', 'role_look', null, 'root-rbac-role-role_look', '角色查看', '角色查看', '12', '1', '03c0a25a-bb1b-4561-972a-aa5bf3dd583a', null);

-- --------------------------------------------角色-------------------------------------------------------------------------------------------------------------
INSERT INTO `auth_role` VALUES ('10d24bbb-1eec-4a46-9126-8755cdf41697', '', '2018-10-11 15:29:53', '0', '', '2018-10-11 15:29:53', '0', 'cjgly', '超级管理员', '系统超级管理员', 'cjgly', null);
-- --------------------------------------------角色资源关联-------------------------------------------------------------------------------------------------------------
INSERT INTO `auth_role_resource` VALUES ('0c2e2e07-9f21-4949-af00-7aa3d727e36b', '7be7d26f-9b6b-41e2-a796-caab3c4778ad', '10d24bbb-1eec-4a46-9126-8755cdf41697');
INSERT INTO `auth_role_resource` VALUES ('1c5ee781-e779-4312-a3a8-d6ea930fdebf', 'cd5521b8-e538-414c-b7d4-cc032b46bf3a', '10d24bbb-1eec-4a46-9126-8755cdf41697');
INSERT INTO `auth_role_resource` VALUES ('238fd0b4-4302-41c8-9c4d-2ffa76ee9156', '5ee477c8-bcde-441c-8995-7f55b79f2229', '10d24bbb-1eec-4a46-9126-8755cdf41697');
INSERT INTO `auth_role_resource` VALUES ('3a1727d2-52ce-4e0f-a583-58567d8f5da3', '5df5c7f5-d645-4ff8-b958-8c0b10cd5f07', '10d24bbb-1eec-4a46-9126-8755cdf41697');
INSERT INTO `auth_role_resource` VALUES ('47de65ad-f014-4d49-98d2-50fb5410678f', '23581f1f-3b91-4ffe-863a-dd92720be45f', '10d24bbb-1eec-4a46-9126-8755cdf41697');
INSERT INTO `auth_role_resource` VALUES ('602aeafc-b53d-448f-9208-2eb926043314', '9fbe52b0-811e-40bf-9e2c-8ac700aabd6f', '10d24bbb-1eec-4a46-9126-8755cdf41697');
INSERT INTO `auth_role_resource` VALUES ('8298fd6a-4ed7-4fb0-966a-55520aaf5d3b', '698d0ac1-fb12-4a28-acac-a6b63f219da1', '10d24bbb-1eec-4a46-9126-8755cdf41697');
INSERT INTO `auth_role_resource` VALUES ('83028ced-de3e-4106-ad83-7a542dbf5908', 'c683ff84-76eb-48bf-8155-b9d0c97f88e7', '10d24bbb-1eec-4a46-9126-8755cdf41697');
INSERT INTO `auth_role_resource` VALUES ('9e2a6853-3646-444b-a883-039dc568e79a', '111c7a72-1cbb-4c3e-bb70-be8c3310954a', '10d24bbb-1eec-4a46-9126-8755cdf41697');
INSERT INTO `auth_role_resource` VALUES ('b0b7c266-ac2b-4aef-ad68-1bf43e2e053f', '06df1a8d-171c-42ba-83bc-1dae12cdb565', '10d24bbb-1eec-4a46-9126-8755cdf41697');
INSERT INTO `auth_role_resource` VALUES ('c57bc3ad-fe1a-4f2c-9fbb-9c27b29f4473', '0eb3c198-8b83-48cc-ad2c-583c05b5d576', '10d24bbb-1eec-4a46-9126-8755cdf41697');
INSERT INTO `auth_role_resource` VALUES ('d0f4e073-ec05-4154-b4ad-98d8c6a2bb75', '9a593daf-ce60-434f-bdbc-87bb9c958bfd', '10d24bbb-1eec-4a46-9126-8755cdf41697');
INSERT INTO `auth_role_resource` VALUES ('f0fcad9e-ed99-4426-82e3-8791117f7ff6', '03c0a25a-bb1b-4561-972a-aa5bf3dd583a', '10d24bbb-1eec-4a46-9126-8755cdf41697');
-- --------------------------------------------用户角色关联-------------------------------------------------------------------------------------------------------------
INSERT INTO `auth_role_user` VALUES ('54b75a15-b141-4327-b99b-7e65aa0b0568', '10d24bbb-1eec-4a46-9126-8755cdf41697', '501f5abf-5f69-4d13-af91-54c782f2c57e');
-- --------------------------------------------用户-------------------------------------------------------------------------------------------------------------
INSERT INTO `auth_user` VALUES ('501f5abf-5f69-4d13-af91-54c782f2c57e', '', '2018-08-03 17:53:09', '0', '', '2018-08-03 17:53:09', '0', '0', '', '2021-04-29 17:53:09', '', '', '$2a$10$26JEaJaI2MrcbtuleznwjeHptvMDjqNiRRpk/D78YRAd8SQYaKLQy', 'Sysadmin', null, null, '13997956178', '超级管理员', '0', '9eeeb2e0-07e3-40b4-9d90-46a7ee2faa45');
-- -------------------------------------------------------------------------------------------------------------------------------------------------------------