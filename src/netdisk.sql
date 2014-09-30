/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50619
Source Host           : localhost:3306
Source Database       : netdisk

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2014-09-11 16:09:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuidName` varchar(255) DEFAULT NULL,
  `realName` varchar(255) DEFAULT NULL,
  `savePath` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `uploadTimes` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of resource
-- ----------------------------
