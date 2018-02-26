/*
Navicat MySQL Data Transfer

Source Server         : production-bossLocker
Source Server Version : 50617
Source Host           : 10.10.11.80:3306
Source Database       : black_list

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2018-02-26 10:19:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for list_black
-- ----------------------------
DROP TABLE IF EXISTS `list_black`;
CREATE TABLE `list_black` (
  `id` varchar(128) NOT NULL,
  `type_id` int(11) NOT NULL,
  `app_id` varchar(64) NOT NULL,
  `expire_date` datetime DEFAULT NULL,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`,`type_id`,`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
