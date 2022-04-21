SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for `system_dept`
-- ----------------------------
DROP TABLE IF EXISTS `system_dept`;
CREATE TABLE `system_dept` (
  `id`           bigint(20)   NOT NULL AUTO_INCREMENT,
  `create_by`    varchar(255)          DEFAULT NULL,
  `created_date` datetime              DEFAULT NULL,
  `del_flg`      bit(1)                DEFAULT NULL,
  `update_by`    varchar(36)           DEFAULT NULL,
  `updated_time` datetime              DEFAULT NULL,
  `version`      int(11)               DEFAULT NULL,
  `create_time`  datetime              DEFAULT NULL,
  `enabled`      bit(1)       NOT NULL,
  `name`         varchar(255) NOT NULL,
  `pid`          bigint(20)   NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = MyISAM
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of system_dept
-- ----------------------------

-- ----------------------------
-- Table structure for `system_job`
-- ----------------------------
DROP TABLE IF EXISTS `system_job`;
CREATE TABLE `system_job` (
  `id`           bigint(20)   NOT NULL AUTO_INCREMENT,
  `create_by`    varchar(255)          DEFAULT NULL,
  `created_date` datetime              DEFAULT NULL,
  `del_flg`      bit(1)                DEFAULT NULL,
  `update_by`    varchar(36)           DEFAULT NULL,
  `updated_time` datetime              DEFAULT NULL,
  `version`      int(11)               DEFAULT NULL,
  `enabled`      bit(1)       NOT NULL,
  `name`         varchar(255) NOT NULL,
  `sort`         bigint(20)   NOT NULL,
  `dept_id`      bigint(20)            DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bc7f9qlvn39yjdgjlsan45yo8` (`sort`),
  KEY `FKgce8d3yyvjfnydnyvs5v8rdc7` (`dept_id`)
)
  ENGINE = MyISAM
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of system_job
-- ----------------------------

-- ----------------------------
-- Table structure for `system_log`
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log` (
  `id`           bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by`    varchar(255)        DEFAULT NULL,
  `created_date` datetime            DEFAULT NULL,
  `del_flg`      bit(1)              DEFAULT NULL,
  `update_by`    varchar(36)         DEFAULT NULL,
  `updated_time` datetime            DEFAULT NULL,
  `version`      int(11)             DEFAULT NULL,
  `exception`    varchar(255)        DEFAULT NULL,
  `method`       varchar(255)        DEFAULT NULL,
  `operate_date` datetime            DEFAULT NULL,
  `params`       varchar(255)        DEFAULT NULL,
  `remote_addr`  varchar(255)        DEFAULT NULL,
  `request_uri`  varchar(255)        DEFAULT NULL,
  `timeout`      bigint(20) NOT NULL,
  `title`        varchar(255)        DEFAULT NULL,
  `type`         bit(1)              DEFAULT NULL,
  `username`     varchar(255)        DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = MyISAM
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of system_log
-- ----------------------------

-- ----------------------------
-- Table structure for `system_resource`
-- ----------------------------
DROP TABLE IF EXISTS `system_resource`;
CREATE TABLE `system_resource` (
  `id`           bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime            DEFAULT NULL,
  `create_by`    varchar(255)        DEFAULT NULL,
  `update_by`    varchar(36)         DEFAULT NULL,
  `updated_time` datetime            DEFAULT NULL,
  `del_flg`      bit(1)              DEFAULT NULL,
  `version`      int(11)             DEFAULT NULL,
  `code`         varchar(10)         DEFAULT NULL,
  `icon`         varchar(255)        DEFAULT NULL,
  `level_code`   varchar(255)        DEFAULT NULL,
  `name`         varchar(50)         DEFAULT NULL,
  `remark`       varchar(255)        DEFAULT NULL,
  `sort`         int(11)    NOT NULL,
  `type`         int(11)             DEFAULT NULL,
  `parent_id`    bigint(20)          DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_xpix7q8tsh4k8vfbv6dgqssa` (`code`),
  UNIQUE KEY `UK_hurpj8xcu7m8arngsw55rojg3` (`level_code`),
  KEY `FKlhg4ohleaipfhd6n5uoiy2kll` (`parent_id`)
)
  ENGINE = MyISAM
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for `system_role`
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `id`           bigint(20)  NOT NULL AUTO_INCREMENT,
  `create_by`    varchar(255)         DEFAULT NULL,
  `created_date` datetime             DEFAULT NULL,
  `del_flg`      bit(1)               DEFAULT NULL,
  `update_by`    varchar(36)          DEFAULT NULL,
  `updated_time` datetime             DEFAULT NULL,
  `version`      int(11)              DEFAULT NULL,
  `code`         varchar(20)          DEFAULT NULL,
  `level_code`   varchar(255)         DEFAULT NULL,
  `name`         varchar(20) NOT NULL,
  `parent_id`    bigint(20)           DEFAULT NULL,
  `remark`       varchar(255)         DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jmmybdc65ty7ml01jaxad1oek` (`code`),
  UNIQUE KEY `UK_ph8g81jmthll1d65eaub45pkb` (`level_code`)
)
  ENGINE = MyISAM
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for `system_roles_depts`
-- ----------------------------
DROP TABLE IF EXISTS `system_roles_depts`;
CREATE TABLE `system_roles_depts` (
  `role_id` bigint(20) NOT NULL,
  `dept_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`, `dept_id`),
  KEY `FKm9333qinj1nr155x2moljbh7g` (`dept_id`)
)
  ENGINE = MyISAM
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of system_roles_depts
-- ----------------------------

-- ----------------------------
-- Table structure for `system_roles_resources`
-- ----------------------------
DROP TABLE IF EXISTS `system_roles_resources`;
CREATE TABLE `system_roles_resources` (
  `role_id`     bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`, `resource_id`),
  KEY `FKedf8kit1quj5mkpr8il44ogco` (`resource_id`)
)
  ENGINE = MyISAM
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for `system_user`
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id`           bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by`    varchar(255)        DEFAULT NULL,
  `created_date` datetime            DEFAULT NULL,
  `del_flg`      bit(1)              DEFAULT NULL,
  `update_by`    varchar(36)         DEFAULT NULL,
  `updated_time` datetime            DEFAULT NULL,
  `version`      int(11)             DEFAULT NULL,
  `active`       bit(1)     NOT NULL,
  `avatar`       varchar(255)        DEFAULT NULL,
  `email`        varchar(255)        DEFAULT NULL,
  `expire_time`  datetime            DEFAULT NULL,
  `locked`       bit(1)     NOT NULL,
  `password`     varchar(255)        DEFAULT NULL,
  `phone`        varchar(255)        DEFAULT NULL,
  `sex`          int(11)             DEFAULT NULL,
  `username`     varchar(255)        DEFAULT NULL,
   nickname     varchar(64)  null,
  `dept_id`      bigint(20)          DEFAULT NULL,
  `job_id`       bigint(20)          DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo6atw1l7p3975fps9bk5yk7qa` (`dept_id`),
  KEY `FK2in29wg3wsweyjkw2n4rh1coj` (`job_id`)
)
  ENGINE = MyISAM
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;
-- ----------------------------
-- Table structure for `system_users_roles`
-- ----------------------------
DROP TABLE IF EXISTS `system_users_roles`;
CREATE TABLE `system_users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  KEY `FK3wmkgw4el8sj5cx3n9d0qnt8o` (`role_id`)
)
  ENGINE = MyISAM
  DEFAULT CHARSET = utf8;

