/*
Navicat MySQL Data Transfer

Source Server         : production-bossLocker
Source Server Version : 50617
Source Host           : 10.10.11.80:3306
Source Database       : black_list

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2018-02-26 10:19:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for list_black_cause
-- ----------------------------
DROP TABLE IF EXISTS `list_black_cause`;
CREATE TABLE `list_black_cause` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `blacklist_id` varchar(128) DEFAULT NULL,
  `cause` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `list_id` (`blacklist_id`),
  CONSTRAINT `forien_id` FOREIGN KEY (`blacklist_id`) REFERENCES `list_black` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=813 DEFAULT CHARSET=utf8;
