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

-- --------------------------------------------资源-------------------------------------------------------------------------------------------------------------
INSERT INTO `system_resource` VALUES
  ('1', 'initialize', '2019-07-04 11:00:31', '', '', null, '0', 'root', null, 'root', '根节点', '根节点', '0', '3', null);
INSERT INTO `system_resource` VALUES
  ('2', 'initialize', '2019-07-04 11:06:41', '', '', null, '0', 'rbac', 'quanxianyuechi-xianxing', 'root-rbac', '权限管理',
   '资源管理角色管理', '1', '0', '1');
INSERT INTO `system_resource` VALUES
  ('3', 'initialize', '2019-07-04 11:08:05', '', '', null, '0', 'resource', 'ziyuan-xianxing', 'root-rbac-resource',
        '资源管理', '资源管理', '2', '0', '2');
INSERT INTO `system_resource` VALUES
  ('4', 'initialize', '2019-07-04 11:09:00', '', '', null, '0', 'role', 'jiaosequnti', 'root-rbac-role', '角色管理', '角色管理',
   '3', '0', '2');
INSERT INTO `system_resource` VALUES
  ('5', 'initialize', '2019-07-04 11:09:50', '', '', null, '0', 'system', 'shezhi-xianxing', 'root-system', '系统管理',
   '系统管理', '4', '0', '1');
INSERT INTO `system_resource` VALUES
  ('6', 'initialize', '2019-07-04 11:11:08', '', '', null, '0', 'user', 'yonghu-xianxing', 'root-system-user', '用户管理',
   '用户管理', '5', '0', '5');
INSERT INTO `system_resource` VALUES
  ('7', 'initialize', '2019-07-04 11:11:08', '', '', null, '0', 'dept', 'zuzhijiagoujiekou', 'root-system-dept', '组织管理',
   '组织管理', '0', '0', '5');
INSERT INTO `system_resource` VALUES
  ('8', 'Sysadmin', '2019-08-07 09:09:44', '', 'Sysadmin', '2019-08-07 09:09:44', '0', 'generator',
        'ziliaoshouce-xianxing', 'root-system-null', '代码生成', '代码生成', '6', '0', '5');
-- --------------------------------------------角色资源关联-------------------------------------------------------------------------------------------------------------
INSERT INTO `system_roles_resources` VALUES ('1', '1');
INSERT INTO `system_roles_resources` VALUES ('1', '2');
INSERT INTO `system_roles_resources` VALUES ('1', '3');
INSERT INTO `system_roles_resources` VALUES ('1', '4');
INSERT INTO `system_roles_resources` VALUES ('1', '5');
INSERT INTO `system_roles_resources` VALUES ('1', '6');
INSERT INTO `system_roles_resources` VALUES ('1', '7');
INSERT INTO `system_roles_resources` VALUES ('1', '8');
-- --------------------------------------------角色-------------------------------------------------------------------------------------------------------------
INSERT INTO `system_role` VALUES
  ('1', 'initialize', '2019-07-19 17:19:03', '', 'Sysadmin', '2019-08-07 09:09:45', '1', 'cjgly', 'cjgly', '超级管理员',
        null, '超级管理员');
-- --------------------------------------------用户角色关联-------------------------------------------------------------------------------------------------------------
INSERT INTO `system_users_roles` VALUES ('1', '1');
-- --------------------------------------------用户-------------------------------------------------------------------------------------------------------------
INSERT INTO `system_user` VALUES
  ('1', 'initialize', '2019-07-19 17:19:44', '', null, null, '0', '', null, '949247328@qq.com', '2020-05-29 17:19:59',
   '', '超级管理员', '$2a$10$26JEaJaI2MrcbtuleznwjeHptvMDjqNiRRpk/D78YRAd8SQYaKLQy', '13997956178', '0', 'Sysadmin', null,
   null);
-- -------------------------------------------------------------------------------------------------------------------------------------------------------------